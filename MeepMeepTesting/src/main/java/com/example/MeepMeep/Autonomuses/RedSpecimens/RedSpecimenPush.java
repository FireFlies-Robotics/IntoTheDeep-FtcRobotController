package com.example.MeepMeep.Autonomuses.RedSpecimens;



import com.example.MeepMeep.Autonomuses.Coordinates.RedSpecimenCoordinatesMeepMeepFire;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RedSpecimenPush {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);


        RoadRunnerBotEntity botRedSpecimen = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13.4).build();


        botRedSpecimen.setDimensions(17.5, 17);




        botRedSpecimen.runAction(botRedSpecimen.getDrive().actionBuilder(RedSpecimenCoordinatesMeepMeepFire.getScore1())
                .strafeToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getSpecimentpushStart().position)

                .splineToConstantHeading(RedSpecimenCoordinatesMeepMeepFire.getspecimentpush1().position, RedSpecimenCoordinatesMeepMeepFire.getScore1().heading)
                        .setTangent(0)
                .splineToConstantHeading( RedSpecimenCoordinatesMeepMeepFire.getObservation().position, RedSpecimenCoordinatesMeepMeepFire.getScore1().heading)
//                .strafeTo((RedSpecimenCoordinatesMeepMeepFire.getspecimentpush1().position))
//                .strafeTo((RedSpecimenCoordinatesMeepMeepFire.getspecimentpush2().position))
//                .strafeTo(RedSpecimenCoordinatesMeepMeepFire.getObservation().position)
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




        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(botRedSpecimen).start();
    }
}