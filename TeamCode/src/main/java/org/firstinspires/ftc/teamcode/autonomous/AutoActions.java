package org.firstinspires.ftc.teamcode.autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Intake;

public class AutoActions{
    Elevator elevator;
    Intake intake;
    public class ElevatorUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.elevatorExtend.setPower(0.8);
                initialized = true;
            }

            double pos = elevator.elevatorExtend.getCurrentPosition();
            packet.put("elevatorPos", pos);
            if (pos > 1000) {
                return true;
            } else {
                elevator.elevatorExtend.setPower(0);
                return false;
            }
        }

    }
    public Action elevatorDown() {
        return new ElevatorUp();
    }
    public class ElevatorDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.elevatorExtend.setPower(-0.8);
                initialized = true;
            }

            double pos = elevator.elevatorExtend.getCurrentPosition();
            packet.put("elevatorPos", pos);
            if (pos < 20) {
                return true;
            } else {
                elevator.elevatorExtend.setPower(0);
                return false;
            }
        }

    }
    public Action elevatorUp() {
        return new ElevatorUp();
    }

    public class ArmUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.elevatorLeftArm.setPower(0.8);
                elevator.elevatorRightArm.setPower(0.8);
                initialized = true;
            }

            double pos = elevator.elevatorRightArm.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos > -870) {
                return true;
            } else {
                elevator.elevatorLeftArm.setPower(0);
                elevator.elevatorRightArm.setPower(0);
                return false;
            }
        }
    }

    public Action armUp() {
        return new ArmUp();
    }

    public class ArmDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.elevatorLeftArm.setPower(-0.8);
                elevator.elevatorRightArm.setPower(-0.8);
                initialized = true;
            }

            double pos = elevator.elevatorRightArm.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos < -20) {
                return true;
            } else {
                elevator.elevatorLeftArm.setPower(0);
                elevator.elevatorRightArm.setPower(0);
                return false;
            }
        }
    }
    public Action armDown() {
        return new ArmDown();
    }

    public class ClawOut implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.collect(-1);
            return false;
        }
    }
    public Action clowOut() {
        return new ClawOut();
    }


    public class ClawIn implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.collect(1);
            return false;
        }
    }
    public Action clawIn() {
        return new ClawIn();
    }


}
