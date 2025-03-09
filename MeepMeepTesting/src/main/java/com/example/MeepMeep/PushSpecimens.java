package com.example.MeepMeep;

import com.example.MeepMeep.Autonomuses.Coordinates.RedSpecimenCoordinatesMeepMeepFire;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class PushSpecimens {
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

        botRedSpecimen.runAction(botRedSpecimen.getDrive().actionBuilder(RedSpecimenCoordinatesMeepMeepFire.getIntakeStart())
                        .setTangent(-90)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimensStart0().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimensStart0().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMidWayMoveSpecimensTangent())
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimensStart0().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimensStart0().heading)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().heading)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart2().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimensStart0().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd2().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd2().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().heading)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart2().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart2().heading)
                .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart1().heading)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart3().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenStart2().heading)
                        .setTangent(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd1().heading)
                        .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd3().position, RedSpecimenCoordinatesMeepMeepFire.getMoveSpecimenEnd2().heading)
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