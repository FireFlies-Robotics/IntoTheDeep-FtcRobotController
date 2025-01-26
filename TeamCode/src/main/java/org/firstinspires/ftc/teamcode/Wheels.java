package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Wheels {

    public static final double TICKS_PER_ROTATION = 3611.2; // נמדד על ידי סיבוב הרובוט 20 פעם

    public static final double WHEEL_DIAMETER_CM = 9.6;
    public static final double WHEEL_CIRCUMFERENCE_CM = WHEEL_DIAMETER_CM * Math.PI;
    public static final double MOTOR_ENCODER_RESOLUTION = 537.7;
    public static final double TICKS_PER_CM = MOTOR_ENCODER_RESOLUTION / WHEEL_CIRCUMFERENCE_CM;
    public int weelsCurrentPosition = 0;

    private final DcMotor frontLeft;
    private final DcMotor frontRight;
    private final DcMotor backLeft;
    private final DcMotor backRight;

    private final LinearOpMode opMode; // The opmode used to get the wheels
    private final IMU imu; // Gyros used to get the robots rotation

    double maxSpeed = 1;

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Wheels(LinearOpMode opMode) {
        this.opMode = opMode;

        // Retrieve the IMU from the hardware map
        this.imu = opMode.hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);

        // Getting the wheel motors and setting them up

        frontLeft = opMode.hardwareMap.get(DcMotor.class, "leftFront");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "rightFront");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "leftBack");
        backRight = opMode.hardwareMap.get(DcMotor.class, "rightBack");
//        imu = opMode.hardwareMap.get(IMU.class, "imu");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public Wheels(LinearOpMode opMode, IMU imu) {
        this.opMode = opMode;
        this.imu = imu;

        // Getting the wheel motors and setting them up

        frontLeft = opMode.hardwareMap.get(DcMotor.class, "leftFront");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "rightFront");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "leftBack");
        backRight = opMode.hardwareMap.get(DcMotor.class, "rightBack");
        imu = opMode.hardwareMap.get(IMU.class, "imu");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void driveByJoystickFieldOriented(double x, double y, double rot) {
        double yaw = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS); // Get the yaw angle of the robot

        // Rotate the movement direction counter to the robot's rotation
        double rotX = x * Math.cos(-yaw) - y * Math.sin(-yaw);
        double rotY = x * Math.sin(-yaw) + y * Math.cos(-yaw);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rot), 1);
        double frontLeftPower = (rotY + rotX + rot) / denominator;
        double backLeftPower = (rotY - rotX + rot) / denominator;
        double frontRightPower = (rotY - rotX - rot) / denominator;
        double backRightPower = (rotY + rotX - rot) / denominator;

        // Applying forces to wheel motors

        frontLeft.setPower(frontLeftPower*maxSpeed);
        backLeft.setPower(backLeftPower*maxSpeed);
        frontRight.setPower(frontRightPower*maxSpeed);
        backRight.setPower(backRightPower*maxSpeed);
    }

    public void runWithEncoder() {
//            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public  void stop(){
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public  void rotateByEncoder(double degrees, double power){
        double toPosition = degrees/360 * TICKS_PER_ROTATION;
        driveWheelToPosition(frontRight,power, -toPosition);
        driveWheelToPosition(frontLeft,power, toPosition);
        driveWheelToPosition(backRight, power, -toPosition);
        driveWheelToPosition(backLeft, power, toPosition);
        while (opMode.opModeIsActive() &&  !opMode.isStopRequested() && motorsIsBussy()) {
            opMode.telemetry.addData("Front Position",  "%7d :%7d",
                    frontRight.getCurrentPosition(),
                    frontLeft.getCurrentPosition());
            opMode.telemetry.addData("Back Position",  "%7d :%7d",
                    backRight.getCurrentPosition(),
                    backLeft.getCurrentPosition());
            opMode.telemetry.update();
            opMode.idle();
        }
        stop();
    }
    public void driveForward(double distanceCM, double power){
        double toPosition = distanceCM * TICKS_PER_CM;
        driveWheelToPosition(frontRight,power, toPosition);
        driveWheelToPosition(frontLeft,power, toPosition);
        driveWheelToPosition(backRight, power, toPosition);
        driveWheelToPosition(backLeft, power, toPosition);
        while (opMode.opModeIsActive() &&  !opMode.isStopRequested() && motorsIsBussy()) {
            opMode.telemetry.addData("Front Position",  "%7d :%7d",
                    frontRight.getCurrentPosition(),
                    frontLeft.getCurrentPosition());
            opMode.telemetry.addData("Back Position",  "%7d :%7d",
                    backRight.getCurrentPosition(),
                    backLeft.getCurrentPosition());
            opMode.telemetry.update();
            if( distanceCM <= distanceCM/5){power = power/5;}
//
//            if (power == 0){stop();}
//            opMode.idle();
        }
        stop();
    }
    public void driveBackword(double distanceCM, double power){
        double toPosition = distanceCM * TICKS_PER_CM;
        driveWheelToPosition(frontRight,power, -toPosition);
        driveWheelToPosition(frontLeft,power, -toPosition);
        driveWheelToPosition(backRight, power, -toPosition);
        driveWheelToPosition(backLeft, power, -toPosition);
        while (opMode.opModeIsActive() &&  !opMode.isStopRequested() && motorsIsBussy()) {
            opMode.telemetry.addData("Front Position",  "%7d :%7d",
                    frontRight.getCurrentPosition(),
                    frontLeft.getCurrentPosition());
            opMode.telemetry.addData("Back Position",  "%7d :%7d",
                    backRight.getCurrentPosition(),
                    backLeft.getCurrentPosition());
            opMode.telemetry.update();
            opMode.idle();
        }
        stop();
    }

    public void driveLeft(double distanceCM, double power){
        double toPosition = distanceCM * TICKS_PER_CM;
        driveWheelToPosition(frontRight,power ,toPosition);
        driveWheelToPosition(frontLeft,power ,-toPosition);
        driveWheelToPosition(backRight,power ,-toPosition);
        driveWheelToPosition(backLeft,power ,toPosition);
        while (opMode.opModeIsActive() &&  !opMode.isStopRequested() && motorsIsBussy()) {
            opMode.telemetry.addData("Front Position",  "%7d :%7d",
                    frontRight.getCurrentPosition(),
                    frontLeft.getCurrentPosition());
            opMode.telemetry.addData("Back Position",  "%7d :%7d",
                    backRight.getCurrentPosition(),
                    backLeft.getCurrentPosition());

            opMode.telemetry.update();
            opMode.idle();
        }
        stop();
    }
    public void driveRight(double distanceCM, double power){
        double toPosition = distanceCM * TICKS_PER_CM;
        driveWheelToPosition(frontRight,power ,-toPosition);
        driveWheelToPosition(frontLeft,power ,toPosition);
        driveWheelToPosition(backRight,power ,toPosition);
        driveWheelToPosition(backLeft,power ,-toPosition);
        while (opMode.opModeIsActive() &&  !opMode.isStopRequested() && motorsIsBussy()) {
            opMode.telemetry.addData("Front Position",  "%7d :%7d",
                    frontRight.getCurrentPosition(),
                    frontLeft.getCurrentPosition());
            opMode.telemetry.addData("Back Position",  "%7d :%7d",
                    backRight.getCurrentPosition(),
                    backLeft.getCurrentPosition());

            opMode.telemetry.update();


//            opMode.idle();
        }
        stop();
    }
    public boolean motorsIsBussy(){
        return  frontLeft  .isBusy() ||
                frontRight .isBusy() ||
                backLeft   .isBusy() ||
                backRight  .isBusy();
    }
    public void driveByEncoder(){
    }
    private void driveWheelToPosition(DcMotor wheel, double power, double toPosition){
        wheel.setTargetPosition((int) Math.round(toPosition) + wheel.getCurrentPosition());
        wheel.setPower(power);
        wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}