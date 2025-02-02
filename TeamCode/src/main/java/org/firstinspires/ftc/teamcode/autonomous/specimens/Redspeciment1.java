package org.firstinspires.ftc.teamcode.autonomous.specimens;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Wheels;
import org.firstinspires.ftc.teamcode.autonomous.AutoActions;
import org.firstinspires.ftc.teamcode.autonomous.coordinates.RedSpecimenCoordinatesFire;

@Config
@Autonomous (name = "redSpecimen1 park", group = "autonomus")

public class Redspeciment1 extends LinearOpMode {
    Wheels wheels;
    @Override
    public void runOpMode() throws InterruptedException {
//            Wheels wheels = new Wheels(this);
        Elevator elevator = new Elevator(this);
            AutoActions autoActions;
        MecanumDrive drive = new MecanumDrive(hardwareMap, RedSpecimenCoordinatesFire.getStart());
        Action scorePreLoad = drive.actionBuilder(RedSpecimenCoordinatesFire.getStart())
                .splineTo(RedSpecimenCoordinatesFire.getScore1().position, RedSpecimenCoordinatesFire.getScore1().heading )
                .build();
        Action park = drive.actionBuilder(RedSpecimenCoordinatesFire.getStart())
                .strafeToConstantHeading(RedSpecimenCoordinatesFire.getPark().position)
                .build();

    waitForStart();

    if (isStopRequested()) return;


        Actions.runBlocking(
            new SequentialAction(
                    scorePreLoad



            )
        );
    }
}
