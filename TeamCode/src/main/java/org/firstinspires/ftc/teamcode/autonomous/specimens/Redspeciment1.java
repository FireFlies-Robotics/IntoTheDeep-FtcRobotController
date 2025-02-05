package org.firstinspires.ftc.teamcode.autonomous.specimens;

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
        Action scorePreLoad = drive.actionBuilder(RedSpecimenCoordinatesFire.getStart())

                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(RedSpecimenCoordinatesFire.getScore1(),RedSpecimenCoordinatesFire.getScore1().heading )
                .build();
        Action park = drive.actionBuilder(RedSpecimenCoordinatesFire.getStart())
                .strafeToConstantHeading(RedSpecimenCoordinatesFire.getPark().position)
                .build();

    waitForStart();

        if (isStopRequested()) return;

        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());
        Actions.runBlocking(
            new SequentialAction(
                    autoActions.armUp(),
                    new ParallelAction(
                            scorePreLoad,
                            autoActions.elevatorUp()),
                    autoActions.elevatorDown(),
                    park
//                        autoActions.armUp(),
//                    new ParallelAction(
//                        scorePreLoad,
//                            autoActions.elevatorUp()
//                    ),
//                    new ParallelAction(
//                            autoActions.elevatorDown(),
//                    autoActions.clawIn()
//                    )
            )

        );
        telemetry.addData("arm position", elevator.elevatorRightArm.getCurrentPosition());

    }
}
