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
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Elevator;
import org.firstinspires.ftc.teamcode.Intake;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.Wheels;
import org.firstinspires.ftc.teamcode.autonomous.AutoActions;
import org.firstinspires.ftc.teamcode.autonomous.coordinates.RedSpecimenCoordinatesFire;

import java.util.Arrays;

@Config
@Autonomous (name = "redSpecimen1 park", group = "autonomus")

public class Redspeciment1 extends LinearOpMode {
    Wheels wheels;
    AutoActions autoActions;

    @Override
    public void runOpMode() throws InterruptedException {
//            Wheels wheels = new Wheels(this);
        Elevator elevator = new Elevator(this);
        Intake intake = new Intake(this);  // Ensure Intake is also initialized if needed
        autoActions = new AutoActions(elevator, intake);  // Pass required dependencies
MinVelConstraint velCon = new MinVelConstraint(Arrays.asList(new TranslationalVelConstraint(10),new AngularVelConstraint(10)));
        elevator.initElevator();


        intake.initIntake();
        MecanumDrive drive = new MecanumDrive(hardwareMap, RedSpecimenCoordinatesFire.getStart());
        Action goToScore = drive.actionBuilder(RedSpecimenCoordinatesFire.getStart())
                .setTangent(Math.toRadians(90)).setTangent(Math.toRadians(90))
                .splineToLinearHeading(RedSpecimenCoordinatesFire.getStartScore(), RedSpecimenCoordinatesFire.getStartScore().heading)
                .build();

        Action scorePreLoad = drive.actionBuilder(RedSpecimenCoordinatesFire.getStartScore())
                .splineToConstantHeading(RedSpecimenCoordinatesFire.getScore1().position, RedSpecimenCoordinatesFire.getScore1().heading)

                .build();
        VelConstraint con = new VelConstraint() {
            @Override
            public double maxRobotVel(@NonNull Pose2dDual<Arclength> pose2dDual, @NonNull PosePath posePath, double v) {
                return 20;
            }
        };
        Action park = drive.actionBuilder(RedSpecimenCoordinatesFire.getStart())
                .strafeToConstantHeading(RedSpecimenCoordinatesFire.getPark().position,con)
                .build();

    waitForStart();

        if (isStopRequested()) return;

        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());
        Actions.runBlocking(
            new SequentialAction(
                    new ParallelAction(
                            autoActions.armUp(),
                            goToScore,
                            autoActions.armUp()

                    ),
                    autoActions.elevatorUp(),
                    scorePreLoad,
                    new ParallelAction(
                            autoActions.elevatorDown()
                    ),
                    autoActions.armDown()

            )

        );
        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());

    }
}
