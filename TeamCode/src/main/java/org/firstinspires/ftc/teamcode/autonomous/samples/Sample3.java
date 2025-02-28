package org.firstinspires.ftc.teamcode.autonomous.samples;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Wheels;
import org.firstinspires.ftc.teamcode.autonomous.AutoActionsSample;
import org.firstinspires.ftc.teamcode.autonomous.coordinates.BlueSampleCoordinates;

import java.util.Arrays;

@Config
@Autonomous (name = "sample3", group = "autonomus")

public class Sample3 extends LinearOpMode {
    Wheels wheels;
    AutoActionsSample autoActionsSample;

    @Override
    public void runOpMode() throws InterruptedException {
        Elevator elevator = new Elevator(this);
        Intake intake = new Intake(this);  // Ensure Intake is also initialized if needed
        autoActionsSample = new AutoActionsSample(elevator, intake);  // Pass required dependencies
        MinVelConstraint velCon = new MinVelConstraint(Arrays.asList(new TranslationalVelConstraint(10),new AngularVelConstraint(10)));
        elevator.initElevator();


        intake.initIntake();
        MecanumDrive drive = new MecanumDrive(hardwareMap, BlueSampleCoordinates.getStart());
        Action goToScore1 = drive.actionBuilder(BlueSampleCoordinates.getStart())
                .setTangent(BlueSampleCoordinates.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinates.getStartScore(), BlueSampleCoordinates.getScore().heading)
                .build();
        Action score1 = drive.actionBuilder(BlueSampleCoordinates.getStartScore())
                .splineToConstantHeading(BlueSampleCoordinates.getScore().position, BlueSampleCoordinates.getScore().heading)
                .build();
        Action backOff = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .splineToConstantHeading(BlueSampleCoordinates.getStartScore().position, BlueSampleCoordinates.getScore().heading)
                .build();
        Action backOff2 = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .splineToConstantHeading(BlueSampleCoordinates.getStartScore().position, BlueSampleCoordinates.getScore().heading)
                .build();
        Action backOff3 = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .splineToConstantHeading(BlueSampleCoordinates.getStartScore().position, BlueSampleCoordinates.getScore().heading)
                .build();

        Action wait = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .waitSeconds(1)
                .build();
        Action wait2 = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .waitSeconds(1)
                .build();
        Action wait3 = drive.actionBuilder(BlueSampleCoordinates.getScore())
                .waitSeconds(1)
                .build();
        Action collect2 = drive.actionBuilder(BlueSampleCoordinates.getStartScore())
                .splineToLinearHeading(BlueSampleCoordinates.getIntake2Start(), BlueSampleCoordinates.getIntake2Start().heading)
                .build();
        Action goToScore2 = drive.actionBuilder(BlueSampleCoordinates.getIntake2Start())
                .setTangent(BlueSampleCoordinates.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinates.getStartScore(), BlueSampleCoordinates.getScore().heading)
                .waitSeconds(1)
                .build();
        Action score2 = drive.actionBuilder(BlueSampleCoordinates.getStartScore())
                .splineToConstantHeading(BlueSampleCoordinates.getScore().position, BlueSampleCoordinates.getScore().heading)
                .build();
        Action collect3 = drive.actionBuilder(BlueSampleCoordinates.getStartScore())
                .splineToLinearHeading(BlueSampleCoordinates.getIntake3(), BlueSampleCoordinates.getIntake2Start().heading)
                .build();

        Action goToScore3 = drive.actionBuilder(BlueSampleCoordinates.getIntake2Start())
                .setTangent(BlueSampleCoordinates.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinates.getStartScore(), BlueSampleCoordinates.getScore().heading)
                .waitSeconds(1)
                .build();
        Action score3 = drive.actionBuilder(BlueSampleCoordinates.getStartScore())
                .splineToConstantHeading(BlueSampleCoordinates.getScore().position, BlueSampleCoordinates.getScore().heading)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());
        Actions.runBlocking(
                new SequentialAction(
                        goToScore1,
                        autoActionsSample.armUp(),
                        autoActionsSample.elevatorUp(),
                        score1,
                        autoActionsSample.clawOut(),
                        wait,
                        backOff,
                        autoActionsSample.elevatorDown(),
//                        autoActionsSample.armDown(),
                        collect2,
                        autoActionsSample.armToCollect(),
                        autoActionsSample.clawIn(),
                        autoActionsSample.elevatorToCollect(),
                        autoActionsSample.elevatorDown(),
                        autoActionsSample.armUpFromCollect(),
                        goToScore2,
                        autoActionsSample.elevatorUp(),
                        score2,
                        autoActionsSample.clawOut(),
                        wait2,
                        backOff2,
                        autoActionsSample.elevatorDown(),
                        autoActionsSample.armDown(),
                        autoActionsSample.elevatorDown(),
                        collect3,
                        autoActionsSample.armToCollect(),
                        autoActionsSample.clawIn(),
                        autoActionsSample.elevatorToCollect(),
                        autoActionsSample.elevatorDown(),
                        autoActionsSample.armUpFromCollect(),
                        goToScore3,
                        autoActionsSample.elevatorUp(),
                        score3,
                        autoActionsSample.clawOut(),
                        wait3,
                        backOff3,
                        autoActionsSample.elevatorDown(),
                        autoActionsSample.armDown(),
                        autoActionsSample.elevatorDown()
                        )

        );
        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());

    }
}
