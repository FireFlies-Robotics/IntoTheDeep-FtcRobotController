package com.example.MeepMeep.meepmeeptesting;

import com.example.MeepMeep.Autonomuses.Coordinates.BlueSampleCoordinatesMeepMeep;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueSample4Park {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity robot = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14).build();
        robot.setDimensions(15, 16.5);

        robot.runAction(robot.getDrive().actionBuilder(BlueSampleCoordinatesMeepMeep.getStart())
                .setTangent(BlueSampleCoordinatesMeepMeep.getScoreTangent())
                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getScore(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())

                .splineToLinearHeading(BlueSampleCoordinatesMeepMeep.getIntake2Start(), BlueSampleCoordinatesMeepMeep.getIntake2HeadingChange())

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


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(robot).start();
    }
}
