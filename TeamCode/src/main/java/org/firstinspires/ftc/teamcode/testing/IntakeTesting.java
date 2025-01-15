package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Intake;

@TeleOp(name = "IntakeTest", group = "test")
@Config
@Disabled
public class IntakeTesting extends LinearOpMode {

    Intake intake;
    boolean intakeLiftActive;
    boolean triangle = false; // false = down
    public static double intakePos;
    @Override
    public void runOpMode() throws InterruptedException {

        intake = new Intake(this);
        intake.initIntake();

        waitForStart();

        while (opModeIsActive()) {
//            intake.intakeLift.setPosition(intakePos);
            if (gamepad2.cross){intake.intakeDown();}
            if (gamepad2.triangle){intake.intakeUp();}

            if (gamepad2.circle){
                intake.collect(1);

            }
            else if (gamepad2.square){
                intake.collect(-1);
            }
            else if (gamepad2.triangle || !triangle) {
                intake.liftIntake();
                triangle = true;
            }
            else {
                intake.collect(0);
            }
//            triangle = false;

        }
    }
}

