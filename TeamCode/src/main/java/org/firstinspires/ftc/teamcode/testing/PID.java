package org.firstinspires.ftc.teamcode.testing;

// Imports.

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Elevator;


// TeleOp name.
@TeleOp(name = "Arm_PID")

public class PID extends LinearOpMode {
    private final FtcDashboard dash = FtcDashboard.getInstance();

    @Override
    public void runOpMode() {
        Elevator elevator = new Elevator(this);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        // Main Loop
        while (opModeIsActive()) {
            elevator.PID();

            telemetry.addData("pos", elevator.targetPos);
            telemetry.addData("current", elevator.elevatorRightArm.getCurrentPosition()*-1);
            telemetry.update();
        }
    }
}