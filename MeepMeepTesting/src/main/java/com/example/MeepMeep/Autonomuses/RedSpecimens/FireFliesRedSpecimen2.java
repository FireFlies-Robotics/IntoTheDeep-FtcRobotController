package com.example.MeepMeep.Autonomuses.RedSpecimens;

import com.example.MeepMeep.Autonomuses.Coordinates.RedSpecimenCoordinatesMeepMeepFire;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class FireFliesRedSpecimen2 {
    public static void main(String[] args) {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(600);

        RoadRunnerBotEntity botRedSpecimen = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13.4).build();

        botRedSpecimen.setDimensions(17, 17);


        botRedSpecimen.runAction(botRedSpecimen.getDrive().actionBuilder(RedSpecimenCoordinatesMeepMeepFire.getStart())
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(RedSpecimenCoordinatesMeepMeepFire.getStartScore(), RedSpecimenCoordinatesMeepMeepFire.getStartScore().heading)
                .waitSeconds(0.5)
                // לך לתחילת הפריקה
                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getScore1().position, RedSpecimenCoordinatesMeepMeepFire.getScore1().heading)
//                .setTangent(Math.toRadians(-45))
//                .splineToLinearHeading(RedSpecimenCoordinatesMeepMeepFire.getIntakeStart(), RedSpecimenCoordinatesMeepMeepFire.getIntakeStart().heading)
                //תפרוק
                        .strafeTo(RedSpecimenCoordinatesMeepMeepFire.getStartScore().position)
                        .strafeToLinearHeading(RedSpecimenCoordinatesMeepMeepFire.getIntakeStart().position, RedSpecimenCoordinatesMeepMeepFire.getIntakeStart().heading)
                        .strafeToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getIntakeEnd().position)
                        .setTangent(Math.toRadians(115))
                .splineToLinearHeading(RedSpecimenCoordinatesMeepMeepFire.getScore2(), RedSpecimenCoordinatesMeepMeepFire.getScore2().heading)

                .build());


        meepMeep.setBackground(com.noahbres.meepmeep.MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(botRedSpecimen).start();
    }
}