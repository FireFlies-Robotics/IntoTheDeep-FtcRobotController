package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.autonomous.AutoActions;

public class Elevator {
    public static int ARM_MAX_LIMIT = -2710;
    final public static int ARM_MIN_LIMIT = 0; // cant expend over -500 //todo change min limit

    final public static int ARM_MAX_SCORE = -1250;
            ;

    final public static int ARM_MAX_COLLECT= -2300;

    final public static int ELEVATOR_MAX_COLLECT = 1660;
    final public static int ELEVATOR_MAX_LIMIT = 3300;

    public  DcMotor elevatorExtend;
    public DcMotor elevatorLeftArm;
    public DcMotor elevatorRightArm;


    private OpMode opMode;

    public Elevator(OpMode opMode) {
        this.opMode = opMode;
    }

    public void initElevator(){
        elevatorExtend = opMode.hardwareMap.get(DcMotor.class, "elevatorExtend");
        elevatorRightArm = opMode.hardwareMap.get(DcMotor.class, "elevatorRightArm");
        elevatorLeftArm = opMode.hardwareMap.get(DcMotor.class, "elevatorLeftArm");
        elevatorLeftArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorRightArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorRightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorLeftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorLeftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorRightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        elevatorRightArm.setTargetPosition(0);
        elevatorLeftArm.setTargetPosition(0);



        elevatorLeftArm.setDirection(DcMotorSimple.Direction.REVERSE);//todo check which motor to reverse

//        elevatorLeftArm.setPower(1);
//        elevatorRightArm.setPower(1);
    }

    public void start(){
        elevatorLeftArm.setTargetPosition(ARM_MAX_LIMIT);
        elevatorRightArm.setTargetPosition(ARM_MAX_LIMIT);
        elevatorLeftArm.setPower(1);
        elevatorRightArm.setPower(1);

        elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    // this function use value (like the gamepad stick) to give the extension motor power.
    public void extend(double extension){
        boolean useExtend = extension>=0.3 || extension <= -0.3;
        if (elevatorRightArm.getCurrentPosition() > ARM_MAX_SCORE){
             elevatorExtend.setPower(-1);
        }
//        if (elevatorRightArm.getCurrentPosition() > ARM_MAX_SCORE){
//            opMode.telemetry.addLine("1");
//            elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            elevatorExtend.setTargetPosition(0);
//            elevatorExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            elevatorExtend.setPower(1);
//        }
        else if (useExtend
                && elevatorRightArm.getCurrentPosition() > ARM_MAX_COLLECT
                && elevatorExtend.getCurrentPosition() < ELEVATOR_MAX_LIMIT) {
            opMode.telemetry.addLine("2");
            elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevatorExtend.setPower(extension);
        }
        else if (useExtend
                && elevatorRightArm.getCurrentPosition() > ARM_MAX_COLLECT
                && elevatorExtend.getCurrentPosition() >= ELEVATOR_MAX_LIMIT) {
            opMode.telemetry.addLine("3");
            elevatorExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            elevatorExtend.setPower(Math.min(extension, -0.3));
        }
        else if (useExtend
                && elevatorRightArm.getCurrentPosition() <= ARM_MAX_COLLECT
                && elevatorExtend.getCurrentPosition() < ELEVATOR_MAX_COLLECT) {
            opMode.telemetry.addLine("4");
            elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevatorExtend.setPower(extension);
        }
        else if (elevatorRightArm.getCurrentPosition() <= ARM_MAX_COLLECT
                && elevatorExtend.getCurrentPosition() >= ELEVATOR_MAX_COLLECT) {
            opMode.telemetry.addLine("5");
            elevatorExtend.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            elevatorExtend.setPower(Math.min(extension, -0.3));
        }
        else {
            opMode.telemetry.addLine("6");
            elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            elevatorExtend.setPower(0);
        }
    }
    // this function use value (like the gamepad stick) to give the rotation motor power.
    public void rotateForwards(){
        if (elevatorRightArm.getTargetPosition() >= ARM_MAX_LIMIT) {

            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getTargetPosition() - 25);
            elevatorRightArm.setTargetPosition(elevatorRightArm.getTargetPosition() - 25);
            elevatorLeftArm.setPower(1);
            elevatorRightArm.setPower(1);

            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (elevatorRightArm.getCurrentPosition() <= ARM_MAX_LIMIT) {
//
//            elevatorLeftArm.setTargetPosition(ARM_MAX_LIMIT);
//            elevatorRightArm.setTargetPosition(ARM_MAX_LIMIT);
//            elevatorLeftArm.setPower(1);
//            elevatorRightArm.setPower(1);
//
//            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }


    // todo check real limit numbers


    public void rotateBackwords(){
        if (elevatorRightArm.getTargetPosition() < ARM_MIN_LIMIT ) {

            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getTargetPosition() + 40);
            elevatorRightArm.setTargetPosition(elevatorRightArm.getTargetPosition() + 40);
            elevatorLeftArm.setPower(1);
            elevatorRightArm.setPower(1);

            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else if (elevatorRightArm.getTargetPosition() >= ARM_MIN_LIMIT) {
//
//            elevatorLeftArm.setTargetPosition(ARM_MIN_LIMIT);
//            elevatorRightArm.setTargetPosition(ARM_MIN_LIMIT);
//            elevatorLeftArm.setPower(1);
//            elevatorRightArm.setPower(1);
//
//            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

    }
    public void score(){
        elevatorLeftArm.setTargetPosition(-1260); //todo change to real scoring poison if servo ita -860
        elevatorRightArm.setTargetPosition(-1260);
        elevatorLeftArm.setPower(1);
        elevatorRightArm.setPower(1);

        elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void collect(){
        elevatorLeftArm.setTargetPosition(-2360);
        elevatorRightArm.setTargetPosition(-2360);
        elevatorLeftArm.setPower(1);
        elevatorRightArm.setPower(1);
        elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void scoreSpecimen(){
        elevatorLeftArm.setTargetPosition(-1500);
        elevatorRightArm.setTargetPosition(-1500);
        elevatorLeftArm.setPower(1);
        elevatorRightArm.setPower(1);
        elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}