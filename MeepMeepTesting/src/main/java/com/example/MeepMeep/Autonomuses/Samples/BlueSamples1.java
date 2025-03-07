package com.example.MeepMeep.Autonomuses.Samples;


import com.example.MeepMeep.Autonomuses.Coordinates.BlueSampleCoordinatesMeepMeep;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueSamples1 {
    public static void main(String[] args) {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(680);

        RoadRunnerBotEntity robot = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14).build();
        robot.setDimensions(17, 17);

        robot.runAction(robot.getDrive().actionBuilder(BlueSampleCoordinatesMeepMeep.getStart())
                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getStartScore(), BlueSampleCoordinatesMeepMeep.getScore().heading)
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getScore().heading)

                //score preload

//                .splineToConstantHeading(BlueSampleCoordinatesMeepMeep.getStartScore().position, BlueSampleCoordinatesMeepMeep.getScore().heading)
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake2Start(), BlueSampleCoordinatesMeepMeep.getIntake2Start().heading)
                //collect second sample

                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getStartScore(), BlueSampleCoordinatesMeepMeep.getScore().heading)
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getScore().heading)
                // score 2

                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake3(), BlueSampleCoordinatesMeepMeep.getIntake3().heading)
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getStartScore(), BlueSampleCoordinatesMeepMeep.getScore().heading)

                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getScore().heading)
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake4(), BlueSampleCoordinatesMeepMeep.getIntake4().heading)
//                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake3(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getStartScore(), BlueSampleCoordinatesMeepMeep.getScore().heading)
                .splineToConstantHeading(BlueSampleCoordinatesMeepMeep.getScore().position,BlueSampleCoordinatesMeepMeep.getScore().heading)
//
//                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
//                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())
//
//                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake4(), BlueSampleCoordinatesMeepMeep.getIntake4HeadingChange())
//
//                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
//                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getIntake4HeadingChange())
//
//                .waitSeconds(15)
//
//                .strafeToLinearHeading(BlueSampleCoordinatesMeepMeep.getPark().component1(), BlueSampleCoordinatesMeepMeep.getPark().heading)

                .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(robot).start();
    }
}
