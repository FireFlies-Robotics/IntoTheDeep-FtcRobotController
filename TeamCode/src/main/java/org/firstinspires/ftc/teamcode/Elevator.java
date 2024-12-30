package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Config
public class Elevator {

    final public static int ARM_MAX_LIMIT = 1600;
    final public static int ARM_MIN_LIMIT = 0; // cant expend over -500 //todo change min limit

    final public static int ARM_MAX_SCORE = -850;

    final public static int ARM_MAX_COLLECT= -1100;

    final public static int ELEVATOR_MAX_COLLECT = 1600;
    final public static int ELEVATOR_MAX_LIMIT = 3000;

    public  DcMotor elevatorExtend;
    public DcMotor elevatorLeftArm;
    public DcMotor elevatorRightArm;

    public PIDController controller;

    public double p, i, d, f;
    public int targetPos;
    public double ticsPerDegree; // todo למצוא רפמ

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

        elevatorRightArm.setDirection(DcMotorSimple.Direction.REVERSE);//todo check which motor to reverse

        controller = new PIDController(p, i, d);

//        elevatorLeftArm.setPower(1);
//        elevatorRightArm.setPower(1);
    }
    public void PID(){
        controller.setPID(p, i, d);
        int currentPosition = -1*(elevatorRightArm.getCurrentPosition());
        double pidPower = controller.calculate(currentPosition,targetPos);
        double FF = Math.cos(Math.toRadians(targetPos/ticsPerDegree)) *f;
        double power = pidPower + FF;
        elevatorRightArm.setPower(power);
        elevatorLeftArm.setPower(power);
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
            opMode.telemetry.addLine("1");
            elevatorExtend.setTargetPosition(0);
            elevatorExtend.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elevatorExtend.setPower(1);
        }
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
            elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevatorExtend.setPower(Math.min(extension, 0));
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
            elevatorExtend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elevatorExtend.setPower(Math.min(extension, -0.3));
        }
        else {
            opMode.telemetry.addLine("6");
            elevatorExtend.setPower(0);
        }
    }
    // this function use value (like the gamepad stick) to give the rotation motor power.
    public void rotateForwards(){
        if (targetPos+25 <= ARM_MAX_LIMIT) {
            targetPos +=25;
        }
    }


    // todo check real limit numbers


    public void rotateBackwords(){
        if (targetPos-25 >= ARM_MIN_LIMIT ) {
            targetPos-=25;
        }
    }
        public void score(){
        targetPos = 860;
        }
        public void collect(){targetPos = 1400;}
    }




