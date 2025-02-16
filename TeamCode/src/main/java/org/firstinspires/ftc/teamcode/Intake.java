package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    public CRServo leftIntake;
    public CRServo rightIntake;
    public Servo pushSerevo;


    private CRServo intakeServo;
    private Servo intakeLift;

    private double intakeDownPosition = 0;
    private double intakeUpPosition = 0.9;
    public void initIntake() {
        leftIntake.setPower(0);
        rightIntake.setPower(0);
        rightIntake.setDirection(DcMotorSimple.Direction.REVERSE);

//        intakeServo.setPower(0);
    }

    public Intake(OpMode opMode) {

//        intakeServo = opMode.hardwareMap.get(CRServo.class, "intakeServo");
//        intakeLift = opMode.hardwareMap.get(Servo.class, "liftServo");

        leftIntake = opMode.hardwareMap.get(CRServo.class, "left");
        rightIntake = opMode.hardwareMap.get(CRServo.class, "right");
        rightIntake.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void pushSamples(double pos){
        pushSerevo.setPosition(pos);

    }
    public void intakeUp(){
//        intakeLift.setPosition(intakeUpPosition);
    }
    public void intakeDown(){
        //intakeLift.setPosition(intakeDownPosition);
    }

    public void rotateIntakeWheels(double wheelPower){
        leftIntake.setPower(wheelPower);
        rightIntake.setPower(wheelPower);
    }
    public void liftIntake() {
//        if (intakeLift.getPosition() <= intakeDownPosition)
//            intakeLift.setPosition(intakeUpPosition);
//        else {
//            intakeLift.setPosition(intakeDownPosition);
    }

}
//    public void collect(double power) {
//        intakeServo.setPower(power);
    //}


//}
