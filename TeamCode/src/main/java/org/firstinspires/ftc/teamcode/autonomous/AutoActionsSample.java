package org.firstinspires.ftc.teamcode.autonomous;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Intake;

public class AutoActionsSample {
    private Servo pushServo;
    OpMode opMode;
    Elevator elevator;
    Intake intake;

    public AutoActionsSample(Elevator elevator, Intake intake) {
        this.elevator = elevator;
        this.intake = intake;
    }
//
//    public AutoActions(Elevator elevator, Intake intake) {
//        this.elevator = elevator;
//        this.intake = intake;
//    }

    public class ElevatorUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorExtend.setPower(1);
                double power = elevator.elevatorExtend.getPower();
                packet.put("elevator power", power);

                initialized = true;
            }

            double pos = elevator.elevatorExtend.getCurrentPosition();
            packet.put("elevator pos", pos);
            if (pos < 2785) {
                return true;
            } else {
                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.elevatorExtend.setTargetPosition(2785);
                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.elevatorExtend.setPower(1);
                return false;
            }
        }

    }
    public class ElevatorDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorExtend.setPower(-1);

                double power = elevator.elevatorExtend.getPower();
                packet.put("elevator power", power);

                initialized = true;
            }

            double pos = elevator.elevatorExtend.getCurrentPosition();
            packet.put("elevator pos", pos);
            if (pos > 20) {
                return true;
            }
            else {
                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.elevatorExtend.setTargetPosition(0);
                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.elevatorExtend.setPower(-1);
                return false;
            }
        }

    }

    public Action elevatorDown() {
        return new ElevatorDown();
    }
    public Action elevatorUp() {
        return new ElevatorUp();
    }

    public class ArmUp implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {

                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                elevator.elevatorRightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorLeftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorLeftArm.setPower(-1);
                elevator.elevatorRightArm.setPower(-1);
                initialized = true;

            }

            double pos = elevator.elevatorRightArm.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos > -1170) {
                return true;
            } else {
                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.elevatorLeftArm.setTargetPosition(-1170);
                elevator.elevatorRightArm.setTargetPosition(-1170);
                elevator.elevatorLeftArm.setPower(-1);
                elevator.elevatorRightArm.setPower(-1);
                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                return false;
            }
        }
    }


    public class ArmDown implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {

                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorLeftArm.setPower(0.7);
                elevator.elevatorRightArm.setPower(0.7);
                initialized = true;
            }

            double pos = elevator.elevatorRightArm.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos < -20) {
                return true;
            } else {

                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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
            intake.rotateIntakeWheels(-0.7);
            return false;
        }
    }

    public Action armUp() {
        return new ArmUp();
    }

    public class ArmToCollect implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {

                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorLeftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorRightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorLeftArm.setPower(-1);
                elevator.elevatorRightArm.setPower(-1);
                initialized = true;
            }

            double pos = elevator.elevatorRightArm.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos > -2430) {
                return true;
            } else {
                elevator.elevatorLeftArm.setPower(0);
                elevator.elevatorRightArm.setPower(0);
                return false;
            }
        }
    }
    public Action armToCollect(){return new ArmToCollect();}


    public Action clawOut() {
        return new ClawOut();
    }


    public class ClawIn implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.leftIntake.setPower(1);
            intake.rightIntake.setPower(1);
            return false;
        }
    }
    public Action clawIn() {
        return new ClawIn();
    }

    public class ElevatorToCollect implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                elevator.elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorExtend.setPower(0.7);
                double power = elevator.elevatorExtend.getPower();
                packet.put("elevator power", power);

                initialized = true;
            }

            double pos = elevator.elevatorExtend.getCurrentPosition();
            packet.put("elevator pos", pos);
            if (pos < 900) {
                return true;
            } else {
//                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                elevator.elevatorExtend.setTargetPosition(770);
//                elevator.elevatorExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.elevatorExtend.setPower(0);
                return false;
            }
        }
    }
    public Action elevatorToCollect() {
        return new ElevatorToCollect();
    }


    public class ArmUpFromCollect implements Action {
        private boolean initialized = false;

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                packet.put("isArmFromCollectInitialized", initialized);
                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                elevator.elevatorRightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorLeftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                elevator.elevatorLeftArm.setPower(0.8);
                elevator.elevatorRightArm.setPower(0.8);
                initialized = true;
            }

            double pos = elevator.elevatorRightArm.getCurrentPosition();
            packet.put("armPos", pos);
            if (pos > -1170) {
                return true;
            } else {
                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                elevator.elevatorLeftArm.setTargetPosition(-1170);
                elevator.elevatorRightArm.setTargetPosition(-1170);
                elevator.elevatorLeftArm.setPower(0.8);
                elevator.elevatorRightArm.setPower(0.8);
                elevator.elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                elevator.elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                return false;
            }
        }
    }
    public Action armUpFromCollect(){return new ArmUpFromCollect();}

}
//    public class PushSumples implements Action{
//
//    }





