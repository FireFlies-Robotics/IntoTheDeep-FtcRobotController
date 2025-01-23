package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Intake;

@TeleOp(name = "wheelIntakeTest", group = "test")
@Config
//@Disabled
public class IntakeWheels extends LinearOpMode {
    private CRServo leftIntake;
    private CRServo rightIntake;


    @Override
    public void runOpMode() throws InterruptedException {
        leftIntake = hardwareMap.get(CRServo.class, "left");
        rightIntake = hardwareMap.get(CRServo.class, "right");

        rightIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.circle){
                rightIntake.setPower(1);
                leftIntake.setPower(1);
            }
            if (gamepad1.square){
                rightIntake.setPower(-1);
                leftIntake.setPower(-1);
            }
            if (gamepad1.triangle){
                rightIntake.setPower(0);
                leftIntake.setPower(0);
            }
        }
    }
}

