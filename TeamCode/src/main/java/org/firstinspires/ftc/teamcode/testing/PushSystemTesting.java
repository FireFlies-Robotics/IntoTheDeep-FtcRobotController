package org.firstinspires.ftc.teamcode.testing;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.PushSystem;


@TeleOp(name = "PushTest", group = "test")
@Config
//@Disabled
public class PushSystemTesting extends LinearOpMode {

    PushSystem pushSystem;
    public static double pushPosition;
    @Override

    public void runOpMode() throws InterruptedException {

        pushSystem = new PushSystem(this);
        pushSystem.initPushSystem();

        waitForStart();

        while (opModeIsActive()) {
            pushSystem.moveToCustomPosition(pushPosition);
        }
    }
}

