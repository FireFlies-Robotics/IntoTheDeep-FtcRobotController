package org.firstinspires.ftc.teamcode.autonomous.samples;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.onbotjava.handlers.objbuild.WaitForBuild;
import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Wheels;
import org.firstinspires.ftc.teamcode.autonomous.AutoActionsSample;
import org.firstinspires.ftc.teamcode.autonomous.coordinates.BlueSampleCoordinates;
import org.firstinspires.ftc.teamcode.autonomous.coordinates.RedSpecimenCoordinatesFire;

import java.util.Arrays;

@Config
@Autonomous (name = "sample1", group = "autonomus")

public class Sample1 extends LinearOpMode {
    Wheels wheels;
    AutoActionsSample autoActions;

    @Override
    public void runOpMode() throws InterruptedException {
        Elevator elevator = new Elevator(this);
        Intake intake = new Intake(this);  // Ensure Intake is also initialized if needed
        autoActions = new AutoActionsSample(elevator, intake);  // Pass required dependencies
        MinVelConstraint velCon = new MinVelConstraint(Arrays.asList(new TranslationalVelConstraint(10),new AngularVelConstraint(10)));
        elevator.initElevator();


        intake.initIntake();
        MecanumDrive drive = new MecanumDrive(hardwareMap, BlueSampleCoordinates.getStart());
        Action startScoreSample1 = drive.actionBuilder(BlueSampleCoordinates.getStart())
                .setTangent(BlueSampleCoordinates.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinates.getEndScore(), BlueSampleCoordinates.getScore().heading)
                .build();
        Action scoreSample1 = drive.actionBuilder(BlueSampleCoordinates.getIntake2End())
                .setTangent(BlueSampleCoordinates.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinates.getScore(), BlueSampleCoordinates.getScore().heading)
                .build();


        Action wait1 = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .waitSeconds(2)
                .build();
        Action wait2 = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .waitSeconds(2)
                .build();
        Action backOff = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .splineToConstantHeading(BlueSampleCoordinates.getEndScore().position, BlueSampleCoordinates.getEndScore().heading)
                        .build();
        Action backOff2 = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .splineToConstantHeading(BlueSampleCoordinates.getEndScore().position, BlueSampleCoordinates.getEndScore().heading)
                .splineToConstantHeading(BlueSampleCoordinates.getEndScore().position, BlueSampleCoordinates.getIntake2Start().heading)


                .build();
        Action intake2 = drive.actionBuilder(BlueSampleCoordinates.getEndScore())
                .splineToLinearHeading(BlueSampleCoordinates.getIntake2Start(), BlueSampleCoordinates.getIntake2Start().heading)
                .build();
        Action scoreSecond = drive.actionBuilder(BlueSampleCoordinates.getIntake2Start())
                .splineToLinearHeading(BlueSampleCoordinates.getScore(), BlueSampleCoordinates.getScore().heading)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());
        Actions.runBlocking(
                new SequentialAction(
                        startScoreSample1,
                        autoActions.armUp(),
                        autoActions.elevatorUp(),
                        scoreSample1,
                        autoActions.clowOut(),
                        wait1,
                        backOff,
                        autoActions.elevatorDown(),
                        autoActions.armDown(),
                        intake2,
                        autoActions.armToCollect(),
                        new ParallelAction(
                                autoActions.elevatorToCollect(),
                                autoActions.clawIn()
                        ),
                        autoActions.elevatorDown(),
                        autoActions.armDown(),
                        scoreSecond,
                        autoActions.armUp(),
                        autoActions.elevatorUp(),
                        autoActions.clowOut(),
                        wait2,
                        backOff2,
                        autoActions.elevatorDown(),
                        autoActions.armDown()

                        )

        );
        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());

    }
}
