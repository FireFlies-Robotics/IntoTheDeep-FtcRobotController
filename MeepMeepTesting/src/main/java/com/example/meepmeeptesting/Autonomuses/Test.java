package com.example.meepmeeptesting.Autonomuses;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.example.meepmeeptesting.Robot.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Test {
    private double center = 8.5;
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(750);
//        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width

                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();
//        TrajectoryActionBuilder tab1 = drive.actionBuilder(initialPose)
                myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -62, Math.toRadians(90)))
//
//                .lineToYSplineHeading(33, Math.toRadians(0))
//                .waitSeconds(2)
//                .setTangent(Math.toRadians(90))
//                .lineToY(48)
//                .setTangent(Math.toRadians(0))
//                .lineToX(32)
//                .strafeTo(new Vector2d(44.5, 30))
//                .turn(Math.toRadians(180))
//                .lineToX(47.5)
//                .waitSeconds(3)
                        .build());
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0, -62, Math.toRadians(90)))
                .lineToY(-23-8.5)
                .waitSeconds(2)
                .setTangent(0)
                .splineToLinearHeading(new Pose2d(48, -30, Math.toRadians(90)), Math.PI / 2)
                .waitSeconds(1)
                .lineToY(-60)
                .splineTo(new Vector2d(62, -62 ), Math.toRadians(30))//                .turn(Math.toRadians(90))
                .lineToY(30)
                .turn(Math.toRadians(90))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .lineToY(0)
                .turn(Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}