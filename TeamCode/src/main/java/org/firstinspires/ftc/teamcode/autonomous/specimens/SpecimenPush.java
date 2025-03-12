package org.firstinspires.ftc.teamcode.autonomous.specimens;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.PosePath;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Wheels;
import org.firstinspires.ftc.teamcode.autonomous.AutoActionsSample;
import org.firstinspires.ftc.teamcode.autonomous.AutoActionsSpecimen;
import org.firstinspires.ftc.teamcode.autonomous.coordinates.RedSpecimenCoordinatesFire;

import java.util.Arrays;

@Config
@Autonomous (name = "specimen push", group = "autonomus")

public class SpecimenPush extends LinearOpMode {
    Wheels wheels;
    AutoActionsSpecimen autoActionsSpecimen;
    AutoActionsSample autoActionsSample;

    @Override
    public void runOpMode() throws InterruptedException {
//            Wheels wheels = new Wheels(this);
        Elevator elevator = new Elevator(this);
        Intake intake = new Intake(this);  // Ensure Intake is also initialized if needed
        autoActionsSample = new AutoActionsSample(elevator, intake);
        autoActionsSpecimen = new AutoActionsSpecimen(elevator, intake);  // Pass required dependencies
        MinVelConstraint velCon = new MinVelConstraint(Arrays.asList(new TranslationalVelConstraint(20),new AngularVelConstraint(7)));
        elevator.initElevator();
        intake.initIntake();
        MecanumDrive drive = new MecanumDrive(hardwareMap, RedSpecimenCoordinatesFire.getStart());
        Action goToScore = drive.actionBuilder(RedSpecimenCoordinatesFire.getStart())
                .setTangent(Math.toRadians(90)).setTangent(Math.toRadians(90))
                .splineToLinearHeading(RedSpecimenCoordinatesFire.getStartScore(), RedSpecimenCoordinatesFire.getStartScore().heading, velCon)
                .build();

        Action scorePreLoad = drive.actionBuilder(RedSpecimenCoordinatesFire.getStartScore())
                .splineToConstantHeading(RedSpecimenCoordinatesFire.getScore1().position, RedSpecimenCoordinatesFire.getScore1().heading, velCon)
                .build();
        Action score2 = drive.actionBuilder(RedSpecimenCoordinatesFire.getStartScore())
                .splineToConstantHeading(RedSpecimenCoordinatesFire.getScore2().position, RedSpecimenCoordinatesFire.getScore1().heading, velCon)
                .build();
        Action backOff = drive.actionBuilder(RedSpecimenCoordinatesFire.getScore1())
                .strafeTo(RedSpecimenCoordinatesFire.getStartScore().position)
                .build();
        Action backOff2 = drive.actionBuilder(RedSpecimenCoordinatesFire.getScore1())
                .strafeTo(RedSpecimenCoordinatesFire.getStartScore().position)
                .build();
        Action park = drive.actionBuilder(RedSpecimenCoordinatesFire.getStartScore())
                .strafeToConstantHeading(RedSpecimenCoordinatesFire.getPark().position)
                .build();
        Action pushSpecimens = drive.actionBuilder(RedSpecimenCoordinatesFire.getStartScore())

                .setTangent(Math.toRadians(0))


                .splineToLinearHeading(RedSpecimenCoordinatesFire.getMoveSpecimensStart0(), RedSpecimenCoordinatesFire.getMoveSpecimensStart0().heading)
                .setTangent(RedSpecimenCoordinatesFire.getMidWayMoveSpecimensTangent())
                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenStart1().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
                .setTangent(RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
                .setTangent(RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenStart1().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
//                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenStart2().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
//                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenEnd2().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
//                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenStart2().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
//                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart2().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenStart3().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)
//                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesFire.getMoveSpecimenEnd3().position, RedSpecimenCoordinatesFire.getMoveSpecimenEnd1().heading)

                .build();

        VelConstraint con = new VelConstraint() {
            @Override
            public double maxRobotVel(@NonNull Pose2dDual<Arclength> pose2dDual, @NonNull PosePath posePath, double v) {
                return 20;
            }
        };


        Action collect1 = drive.actionBuilder(RedSpecimenCoordinatesFire.getStartScore())
                .splineToLinearHeading(RedSpecimenCoordinatesFire.getIntakeStart(), RedSpecimenCoordinatesFire.getIntakeStart().heading)
                .build();

        Action goToScore2 = drive.actionBuilder(RedSpecimenCoordinatesFire.getIntakeStart())
                .setTangent(Math.toRadians(115))
                .splineToLinearHeading(RedSpecimenCoordinatesFire.getStartScore(), RedSpecimenCoordinatesFire.getStartScore().heading)
                .build();


        waitForStart();

        if (isStopRequested()) return;

        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                autoActionsSpecimen.armUp(),
                                goToScore
//                        autoActions.armUp()
                        ),
                        autoActionsSpecimen.elevatorUp(),
                        scorePreLoad,
                        new ParallelAction(
                                autoActionsSpecimen.elevatorDown(),
                                backOff),
                        new ParallelAction(
                                collect1,
                                autoActionsSpecimen.clawIn()
                                ),
                        autoActionsSample.armToCollect(),

                        autoActionsSpecimen.elevatorToCollect(),
                        new ParallelAction(
                                autoActionsSpecimen.elevatorDown(),
                                autoActionsSpecimen.clawStop(),
                                goToScore2,
                                autoActionsSpecimen.armUpFromCollect()
                        ),
                        autoActionsSpecimen.elevatorUp(),
                        score2,
                        new ParallelAction(
                                autoActionsSpecimen.elevatorDown(),
                                backOff2),
                        new ParallelAction(
                                autoActionsSpecimen.armDown(),
                                pushSpecimens)
                )

        );
        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());

    }
}
