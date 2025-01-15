/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Main OpMode", group="Linear OpMode")
//@Disabled
public class TeleOpMode extends LinearOpMode {

    // Declare OpMode members.
    private final ElapsedTime runtime = new ElapsedTime();

    Wheels wheels; // Declare the wheels class to control the wheels
    Elevator elevator;
    Intake intake;
    IMU imu; // Declare class for getting robot angles

    @Override
    public void runOpMode() {
        imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);



        elevator = new Elevator(this);
        wheels = new Wheels(this, imu);
        intake = new Intake(this);

        elevator.initElevator();
        intake.initIntake();

        telemetry.addData("Status", "Initialized");
//        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            if (gamepad1.options) {
                imu.resetYaw();
            }
            if (gamepad2.cross){intake.intakeDown();}
            if (gamepad2.triangle){intake.intakeUp();}
            // Move robot by controller 1
            wheels.driveByJoystickFieldOriented(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

            telemetry.addData("arm position", elevator.elevatorLeftArm.getCurrentPosition());
            if (gamepad2.dpad_up && elevator.elevatorExtend.getCurrentPosition() <= 1000) {
                elevator.rotateForwards();
            }
            if (gamepad2.dpad_down && elevator.elevatorExtend.getCurrentPosition() <= 1000) {
                elevator.rotateBackwords();
            }
            elevator.extend(-gamepad2.left_stick_y);

            if (gamepad2.circle){
                intake.collect(1);

            }
            else if (gamepad2.square){
                intake.collect(-1);
            }
            else {intake.collect(0);}

            if (gamepad2.cross){intake.intakeDown();}
            if (gamepad2.triangle){intake.intakeUp();}
//        elevator.stabilise();

            if (gamepad2.right_bumper && elevator.elevatorExtend.getCurrentPosition() <= 1300
                     ){
                intake.intakeDown();
                elevator.collect();
            }
            if (gamepad2.left_bumper && elevator.elevatorExtend.getCurrentPosition() <= 1000
             ){
                elevator.score();
                intake.intakeUp();
            }

            if (gamepad1.left_trigger > 0.2) {
                wheels.setMaxSpeed(.5);
            } else if(gamepad1.right_trigger > 0.2){
                wheels.setMaxSpeed(0.3);
            }

            else {
                wheels.setMaxSpeed(1);
            }
            telemetry.addData("elevator position", elevator.elevatorExtend.getCurrentPosition());
            telemetry.update();
        }
    }
}



