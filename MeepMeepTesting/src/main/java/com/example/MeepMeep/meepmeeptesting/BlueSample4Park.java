package com.example.MeepMeep.meepmeeptesting;

import com.example.MeepMeep.Coordinates.BlueSampleCoordinatesMeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueSample4Park {
    public static void main(String[] args) {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(700);

        RoadRunnerBotEntity robot = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14).build();
        robot.setDimensions(15, 16.5);

        robot.runAction(robot.getDrive().actionBuilder(BlueSampleCoordinatesMeepMeep.getStart())
                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())

                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake2(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())

                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())

                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake3(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())

                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())

                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake4(), BlueSampleCoordinatesMeepMeep.getIntake4HeadingChange())

                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getIntake4HeadingChange())

                .waitSeconds(15)

                .strafeToLinearHeading(BlueSampleCoordinatesMeepMeep.getPark().component1(), BlueSampleCoordinatesMeepMeep.getPark().heading)

                .build());


        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(robot).start();
    }
}
