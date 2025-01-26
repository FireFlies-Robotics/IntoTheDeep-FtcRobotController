package com.example.MeepMeep;

import com.example.MeepMeep.Coordinates.RedSpecimenCoordinatesMeepMeepFire;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeep {
    public static void main(String[] args) {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(600);

        RoadRunnerBotEntity botRedSpecimen = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13.4).build();
        RoadRunnerBotEntity botBlueSpecimen = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14).build();
        RoadRunnerBotEntity botRedSample = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14).build();
        RoadRunnerBotEntity botBlueSample = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14).build();

        botRedSpecimen.setDimensions(17, 17);
        botBlueSpecimen.setDimensions(15, 16.5);
        botBlueSample.setDimensions(15, 16.5);
        botRedSample.setDimensions(15, 16.5);

        botRedSpecimen.runAction(botRedSpecimen.getDrive().actionBuilder(RedSpecimenCoordinatesMeepMeepFire.getStart())
                .strafeToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getScore1().position)

                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMidWayMoveSpecimensTangent())
                .splineTo(RedSpecimenCoordinatesMeepMeepFire.getMidWayMoveSpecimens().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimensStart0().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
//
                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart2().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd2().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart2().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart3().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd3().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getIntakeStart().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getIntakeEnd().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineToLinearHeading(RedSpecimenCoordinatesMeepMeepFire.getScore2(), RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

                .splineTo(RedSpecimenCoordinatesMeepMeepFire.getIntakeStart().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineTo(RedSpecimenCoordinatesMeepMeepFire.getIntakeEnd().position, RedSpecimenCoordinatesMeepMeepFire.getStart().heading)
                .splineToLinearHeading(RedSpecimenCoordinatesMeepMeepFire.getIntakeStart(), RedSpecimenCoordinatesMeepMeepFire.getStart().heading)

//                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeep.getIntakeStart().position, RedSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeep.getIntakeEnd().position, RedSpecimenCoordinatesMeepMeep.getStart().heading)
//                .strafeToConstantHeading(RedSpecimenCoordinatesMeepMeep.getScore4().component1())
//
//                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeep.getIntakeStart().position, RedSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeep.getIntakeEnd().position, RedSpecimenCoordinatesMeepMeep.getStart().heading)
//                .strafeToConstantHeading(RedSpecimenCoordinatesMeepMeep.getScore5().component1())
//
//                .strafeToConstantHeading(RedSpecimenCoordinatesMeepMeep.getPark().position)
//
//                .build());
//
//        botBlueSpecimen.runAction(botBlueSpecimen.getDrive().actionBuilder(BlueSpecimenCoordinatesMeepMeep.getStart())
//                .strafeToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getScore1().position)
//                .waitSeconds(1)
//
//                .setTangent(BlueSpecimenCoordinatesMeepMeep.getMidWayMoveSpecimensTangent())
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMidWayMoveSpecimens().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimensStart0().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenStart1().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenEnd1().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenStart1().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenStart2().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenEnd2().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenStart2().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenStart3().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getMoveSpecimenEnd3().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .setTangent(BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeStart().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeEnd().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//
//                .strafeToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getScore2().component1())
//                .waitSeconds(1)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeStart().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeEnd().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .strafeToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getScore3().component1())
//                .waitSeconds(1)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeStart().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeEnd().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .strafeToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getScore4().component1())
//                .waitSeconds(1)
//
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeStart().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//                .splineToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getIntakeEnd().position, BlueSpecimenCoordinatesMeepMeep.getStart().heading)
//
//                .strafeToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getScore5().component1())
//                .waitSeconds(1)
//
//                .strafeToConstantHeading(BlueSpecimenCoordinatesMeepMeep.getPark().position)
//
//                .build());
//
//        botRedSample.runAction(botRedSample.getDrive().actionBuilder(RedSampleCoordinatesMeepMeep.getStart())
//                .waitSeconds(26)
//                .strafeToConstantHeading(RedSampleCoordinatesMeepMeep.getPark().position)
//
//                .build());
//
//        botBlueSample.runAction(botBlueSample.getDrive().actionBuilder(BlueSampleCoordinatesMeepMeep.getStart())
//                .waitSeconds(26)
//                .strafeToConstantHeading(BlueSampleCoordinatesMeepMeep.getPark().position)

                .build());


        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(botRedSpecimen).start()
                .addEntity(botBlueSpecimen).start()
                .addEntity(botRedSample).start()
                .addEntity(botBlueSample).start();
    }
}