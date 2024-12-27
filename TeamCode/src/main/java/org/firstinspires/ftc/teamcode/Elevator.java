package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Elevator {

    final public static int ARM_MAX_LIMIT = -1600;
    final public static int ARM_MIN_LIMIT = 0; // cant expend over -500 //todo change min limit

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
//        elevatorExtend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorRightArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorLeftArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        elevatorLeftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorRightArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        elevatorRightArm.setDirection(DcMotorSimple.Direction.REVERSE);//todo check which motor to reverse

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
        if (extension>=0.3 || extension <= -0.3 || elevatorRightArm.getCurrentPosition() != ARM_MIN_LIMIT || elevatorExtend.getCurrentPosition() <=100000)
        {elevatorExtend.setPower(extension);
        }
        else {elevatorExtend.setPower(0);
}
    }
    // this function use value (like the gamepad stick) to give the rotation motor power.
    public void rotateForwards(){
        if (elevatorRightArm.getCurrentPosition() >= ARM_MAX_LIMIT) {

            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getCurrentPosition() - 25);
            elevatorRightArm.setTargetPosition(elevatorRightArm.getCurrentPosition() - 25);
            elevatorLeftArm.setPower(0.8);
            elevatorRightArm.setPower(0.8);

            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (elevatorRightArm.getCurrentPosition() <= ARM_MAX_LIMIT ) {
            elevatorLeftArm.setPower(0);
            elevatorRightArm.setPower(0);
        }
    }


    // todo check real limit numbers


    public void rotateBackwords(){
        if (elevatorRightArm.getCurrentPosition() <= ARM_MIN_LIMIT ) {

            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getCurrentPosition() + 25);
            elevatorRightArm.setTargetPosition(elevatorRightArm.getCurrentPosition() + 25);
            elevatorLeftArm.setPower(0.8);
            elevatorRightArm.setPower(0.8);

            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        if (elevatorRightArm.getCurrentPosition() >= ARM_MIN_LIMIT) {
            elevatorLeftArm.setPower(0);
            elevatorRightArm.setPower(0);
        }
    }
        public void score(){
            elevatorLeftArm.setTargetPosition(500); //todo change to real scoring poison
            elevatorRightArm.setTargetPosition(500);
            elevatorLeftArm.setPower(1);
            elevatorRightArm.setPower(1);

            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        public void collect(){
            elevatorExtend.setTargetPosition(0);
            elevatorExtend.setPower(1);
            elevatorLeftArm.setTargetPosition(100);
            elevatorRightArm.setTargetPosition(100);
            elevatorLeftArm.setPower(1);
            elevatorRightArm.setPower(1);
            elevatorExtend.setPower(1);
            elevatorExtend.setTargetPosition(0);
            elevatorExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
    }




