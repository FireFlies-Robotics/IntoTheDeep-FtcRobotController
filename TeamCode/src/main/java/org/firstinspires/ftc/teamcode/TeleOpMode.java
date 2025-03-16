package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Main OpMode", group="Linear OpMode")
@Config
public class TeleOpMode extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    private final ElapsedTime intakeTimer = new ElapsedTime(); // Timer to debounce intake logic

    Wheels wheels;
    Elevator elevator;
    Intake intake;
    IMU imu;
    PushSystem pushSystem;
    public static double pushPosition;

    private boolean isBlueAlliance = false;
    private boolean colorDetected = false;
    private boolean intakeOverride = false;

    private final float GAIN = 23;
    private final float RED_HUE_LOW = 20;
    private final float RED_HUE_HIGH = 330;
    private final float BLUE_HUE_LOW = 180;
    private final float BLUE_HUE_HIGH = 230;
    private final float YELLOW_HUE_LOW = 21;
    private final float YELLOW_HUE_HIGH = 70;
    private final float MAX_HUE = 375;
    private final float MIN_HUE = 0;

    NormalizedColorSensor colorSensor;
    View relativeLayout;

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        elevator = new Elevator(this);
        wheels = new Wheels(this, imu);
        intake = new Intake(this);
        pushSystem = new PushSystem(this);

        pushSystem.initPushSystem();
        elevator.initElevator();
        intake.initIntake();

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight) colorSensor).enableLight(true);
        }
        colorSensor.setGain(GAIN);

        while (!isStarted() && !isStopRequested()) {
            if (gamepad1.cross) {
                isBlueAlliance = true;
                gamepad1.rumble(200);
            }
            if (gamepad1.circle) {
                isBlueAlliance = false;
                gamepad1.rumble(200);
            }
            telemetry.addData("isBlue=", isBlueAlliance);
            telemetry.update();
        }

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            if (gamepad1.options) {
                imu.resetYaw();
            }

            wheels.driveByJoystickFieldOriented(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);
            elevator.extend(-gamepad2.left_stick_y);
            if (gamepad2.share) {
                elevator.reset();
            }

            // --- Gamepad-controlled intake ---
            intakeOverride = false;
            if (gamepad2.circle) {
                intake.rotateIntakeWheels(1);
                intakeOverride = true;
            } else if (gamepad2.square) {
                intake.rotateIntakeWheels(-1);
                intakeOverride = true;
            }

            if (gamepad2.triangle) {
                intake.intakeUp();
            }

            if (gamepad2.right_bumper && elevator.elevatorExtend.getCurrentPosition() <= 1000) {
                elevator.collect();
            } else if (gamepad2.left_bumper && elevator.elevatorExtend.getCurrentPosition() <= 1000) {
                elevator.score();
                intake.intakeUp();
            } else if (gamepad2.left_trigger >= 0.2) {
                elevator.scoreSpecimen();
            }

            if (gamepad2.dpad_down) {
                elevator.rotateBackwords();
            }
            if (gamepad2.dpad_up) {
                elevator.rotateForwards();
            }

            if (gamepad1.left_trigger > 0.2) {
                wheels.setMaxSpeed(.5);
            } else if (gamepad1.right_trigger > 0.2) {
                wheels.setMaxSpeed(0.3);
            } else {
                wheels.setMaxSpeed(1);
            }

            if (gamepad1.right_bumper) {
                pushSystem.push();
            } else {
                pushSystem.close();
            }

            // --- Color Sensor Logic ---
            float[] hsvValues = new float[3];
            NormalizedRGBA colors = colorSensor.getNormalizedColors();
            Color.colorToHSV(colors.toColor(), hsvValues);

            boolean currentColorDetected = (hsvValues[0] > BLUE_HUE_LOW && hsvValues[0] < BLUE_HUE_HIGH) ||
                    ((hsvValues[0] > MIN_HUE && hsvValues[0] < RED_HUE_LOW) || (hsvValues[0] > RED_HUE_HIGH && hsvValues[0] < MAX_HUE)) ||
                    (hsvValues[0] > YELLOW_HUE_LOW && hsvValues[0] < YELLOW_HUE_HIGH);

            // If color was detected but now isn't, start the countdown
            if (colorDetected && !currentColorDetected) {
                intakeTimer.reset();
            }

            colorDetected = currentColorDetected;

            // --- Intake Delay Logic (Debounce Fix) ---
            if (!intakeOverride) {  // Only run if gamepad isn't controlling intake
                if (!colorDetected) {
                    if (intakeTimer.seconds() >= 1.2){
                        intake.rotateIntakeWheels(0);
                    }
                    else if (intakeTimer.seconds() >= 1.0) {  // Only reverse intake if the sample has been gone for at least 1 second
                        intake.rotateIntakeWheels(-1);
                    }

                } else {
                    intakeTimer.reset();  // Reset timer when color is detected
                    intake.rotateIntakeWheels(0);
                }
            }

            // --- Telemetry ---
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.addData("Detected", colorDetected ? "Yes" : "No");

            if (hsvValues[0] > BLUE_HUE_LOW && hsvValues[0] < BLUE_HUE_HIGH) {
                telemetry.addLine("Detected: Blue");
                gamepad1.setLedColor(0, 0, 255, 1);
                gamepad2.setLedColor(0, 0, 255F, 1);

                if (isBlueAlliance) {
                    gamepad1.rumbleBlips(5);
                    gamepad2.rumbleBlips(5);
                } else {
//                    intake.rotateIntakeWheels(-1);
                }
            } else if ((hsvValues[0] > MIN_HUE && hsvValues[0] < RED_HUE_LOW) || (hsvValues[0] > RED_HUE_HIGH && hsvValues[0] < MAX_HUE)) {
                telemetry.addLine("Detected: Red");
                gamepad1.setLedColor(255, 0, 0, 1);
                gamepad2.setLedColor(255, 0, 0, 1);

                if (isBlueAlliance){
                    intake.rotateIntakeWheels(-1);
                }
                if (!isBlueAlliance) {
                    gamepad1.rumbleBlips(5);
                    gamepad2.rumbleBlips(5);


            }
            } else {
                telemetry.addLine("Detected: None");
            }
            if (hsvValues[0] > YELLOW_HUE_LOW && hsvValues[0] < YELLOW_HUE_HIGH) {
                telemetry.addLine("Detected: Yellow");
                gamepad1.setLedColor(255, 255, 0, 1);
                gamepad2.setLedColor(255, 255, 0, 1);

                gamepad1.rumble(500);
                gamepad2.rumble(500);
            }
//            elevator.armLimitCheck();

            telemetry.addData("isBlue", isBlueAlliance);
            telemetry.update();

            relativeLayout.post(() -> relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues)));
        }
    }
}
