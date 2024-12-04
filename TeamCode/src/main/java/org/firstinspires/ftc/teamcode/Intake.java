package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    private CRServo intakeServo;
    private Servo intakeLift;

    private int intakeDownPosition = 0;
    private int intakeUpPosition = 1;
    public void initIntake() {
        intakeServo.setPower(0);
    }

    public Intake(OpMode opMode) {
        intakeServo = opMode.hardwareMap.get(CRServo.class, "intakeServo");
        intakeLift = opMode.hardwareMap.get(Servo.class, "liftServo");
    }

    public void liftIntake() {
        if (intakeLift.getPosition() <= intakeDownPosition)
            intakeLift.setPosition(intakeUpPosition);
        else {
            intakeLift.setPosition(intakeDownPosition);
    }

}


    public void collect(double power) {
        intakeServo.setPower(power);

    }

}
