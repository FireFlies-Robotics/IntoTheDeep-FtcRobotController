package org.firstinspires.ftc.teamcode.autonomous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.autonomous.coordinates.RedSpecimenCoordinateFire;

@Config
@Autonomous (name = "redSpecimen1 park", group = "autonomus")

public class Redspeciment1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, RedSpecimenCoordinateFire.getStart());
        Action scorePreLoad = drive.actionBuilder(RedSpecimenCoordinateFire.getStart())
                .strafeToConstantHeading(RedSpecimenCoordinateFire.getScore1().position)
                .build();
        Action park = drive.actionBuilder(RedSpecimenCoordinateFire.getStart())
                .strafeTo(RedSpecimenCoordinateFire.getPark().position)
                .build();

    waitForStart();

    if (isStopRequested()) return;
    Actions.runBlocking(
            new SequentialAction(
                    scorePreLoad,
                    park
            )
    );
    }
}
