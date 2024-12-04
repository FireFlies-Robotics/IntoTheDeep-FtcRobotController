package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Elevator {

    final public static double ARM_MAX_LIMIT = 20000;
    final public static double ARM_MIN_LIMIT = 200;

    private DcMotor elevatorExtend;
    private DcMotor elevatorLeftArm;
    private DcMotor elevatorRightArm;


    private OpMode opMode;

    public Elevator(OpMode opMode) {
        this.opMode = opMode;
    }

    public void initElevator(){
        elevatorExtend = opMode.hardwareMap.get(DcMotor.class, "elevatorExtend");
        elevatorRightArm = opMode.hardwareMap.get(DcMotor.class, "elevatorRightArm");
        elevatorLeftArm = opMode.hardwareMap.get(DcMotor.class, "elevatorLeftArm");

//        elevatorExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorLeftArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        elevatorRightArm.setDirection(DcMotorSimple.Direction.REVERSE);//todo check which motor to reverse
//        elevatorLeftArm.setPower(1);
//        elevatorRightArm.setPower(1);

    }

    // this function use value (like the gamepad stick) to give the extension motor power.
    public void extend(double extension){
//        if (elevatorExtend.getPower()>=0.3){
            elevatorExtend.setPower(extension);
//        }
    }
    // this function use value (like the gamepad stick) to give the rotation motor power.
    public void rotateBackwards(){
//        elevatorArm.setPower(rotation);
        if(elevatorLeftArm.getCurrentPosition()>=ARM_MAX_LIMIT){
            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getCurrentPosition());
            elevatorRightArm.setTargetPosition(elevatorRightArm.getCurrentPosition());
            elevatorLeftArm.setPower(0);
            elevatorRightArm.setPower(0);
            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else {
            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getCurrentPosition()+200);
            elevatorRightArm.setTargetPosition(elevatorRightArm.getCurrentPosition()+200);
            elevatorLeftArm.setPower(1);
            elevatorRightArm.setPower(1);


            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } // todo check real limit numbers

    }
    public int rotateForwards(){
//        elevatorArm.setPower(rotation);
        if(elevatorLeftArm.getCurrentPosition()>=ARM_MIN_LIMIT){
            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getCurrentPosition());
            elevatorRightArm.setTargetPosition(elevatorRightArm.getCurrentPosition());
            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else {
            elevatorLeftArm.setTargetPosition(elevatorLeftArm.getCurrentPosition()-200);
            elevatorRightArm.setTargetPosition(elevatorRightArm.getCurrentPosition()-200);
            elevatorLeftArm.setPower(1);
            elevatorRightArm.setPower(1);
            elevatorLeftArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorRightArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
        return elevatorLeftArm.getCurrentPosition();

}
}

