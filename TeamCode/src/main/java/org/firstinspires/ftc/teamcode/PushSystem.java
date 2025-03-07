package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PushSystem {
    private Servo pushMechanism;

    private final double OPEN_POSITION = 0;
    private final double CLOSE_POSITION = 0.35;
    private OpMode opMode;

    public PushSystem(OpMode opMode) {
        this.opMode = opMode;
    }

    public void initPushSystem(){
        pushMechanism = opMode.hardwareMap.get(Servo.class, "push");
        pushMechanism.setPosition(CLOSE_POSITION);
    }

    public void push(){
        pushMechanism.setPosition(OPEN_POSITION);


    }
    public void close(){
        pushMechanism.setPosition(CLOSE_POSITION);
    }
    public void moveToCustomPosition(double position){
        pushMechanism.setPosition(position);
    }
}
