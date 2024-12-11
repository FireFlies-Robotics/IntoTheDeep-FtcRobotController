package org.firstinspires.ftc.teamcode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Intake;

@TeleOp(name = "IntakeTest", group = "test")
public class IntakeTesting extends LinearOpMode {

    Intake intake;
    boolean intakeLiftActive;


    @Override
    public void runOpMode() throws InterruptedException {

        intake = new Intake(this);
        intake.initIntake();

        waitForStart();

        while (opModeIsActive()) {
//            intake.intakeLift.setPosition(gamepad2.left_stick_y);
            telemetry.addData("lift position", gamepad2.left_stick_y);
            telemetry.update();
            if (gamepad2.circle){
                /*int*/intake.collect(1);



            }
            else if (gamepad2.square){
                intake.collect(-1);
            }
            else if (gamepad2.triangle) {
                intake.liftIntake();
            }
            else {
                intake.collect(0);
            }
        }
        }
    }

