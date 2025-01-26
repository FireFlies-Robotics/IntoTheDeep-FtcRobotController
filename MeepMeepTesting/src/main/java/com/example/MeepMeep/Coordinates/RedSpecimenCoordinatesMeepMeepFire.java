package com.example.MeepMeep.Coordinates;

import com.acmerobotics.roadrunner.Pose2d;

public class RedSpecimenCoordinatesMeepMeepFire {
    private static final double startX = 8;
    private static final double startY = -63;
    private static final double score1X = 8;
    private static final double score2X = -1;
    private static final double score3X = 2;
    private static final double score4X = 5;
    private static final double score5X = 8;
    private static final double scoreY = -38;
    private static final double midWayMoveSpecimensY = -40;
    private static final double moveSpecimensStart0X = 36;
    private static final double moveSpecimensStartY = -13;
    private static final double moveSpecimensEndY = -46;
    private static final double specimen1X = 43;
    private static final double specimen2X = 56;
    private static final double specimen3X = 63;
    private static final double wallStartY = -50;
    private static final double wallEndY = -60;

    private static final double intakeX = 47;
    private static final double parkX = 60;

    private static final double midWayMoveSpecimensTangent = Math.toRadians(270);
    private static final double startPoseHeading = Math.toRadians(90);
    private static final double scorePoseHeading = Math.toRadians(270);


    private static final Pose2d start = new Pose2d(startX, startY, startPoseHeading);

    private static final Pose2d score1 = new Pose2d(score1X, scoreY, startPoseHeading);
    private static final Pose2d score2 = new Pose2d(score2X, scoreY, startPoseHeading);
    private static final Pose2d score3 = new Pose2d(score3X, scoreY, startPoseHeading);
    private static final Pose2d score4 = new Pose2d(score4X, scoreY, startPoseHeading);
    private static final Pose2d score5 = new Pose2d(score5X, scoreY, startPoseHeading);

    private static final Pose2d midWayMoveSpecimens = new Pose2d(moveSpecimensStart0X, midWayMoveSpecimensY, scorePoseHeading);
    private static final Pose2d moveSpecimensStart0 = new Pose2d(moveSpecimensStart0X, moveSpecimensStartY, startPoseHeading);

    private static final Pose2d moveSpecimenStart1 = new Pose2d(specimen1X, moveSpecimensStartY, startPoseHeading);
    private static final Pose2d moveSpecimenStart2 = new Pose2d(specimen2X, moveSpecimensStartY, startPoseHeading);
    private static final Pose2d moveSpecimenStart3 = new Pose2d(specimen3X, moveSpecimensStartY, startPoseHeading);

    private static final Pose2d moveSpecimenEnd1 = new Pose2d(specimen1X, moveSpecimensEndY, startPoseHeading);
    private static final Pose2d moveSpecimenEnd2 = new Pose2d(specimen2X, moveSpecimensEndY, startPoseHeading);
    private static final Pose2d moveSpecimenEnd3 = new Pose2d(specimen3X, moveSpecimensEndY, startPoseHeading);

    private static final Pose2d intakeStart = new Pose2d(intakeX, wallStartY, startPoseHeading);
    private static final Pose2d intakeEnd = new Pose2d(intakeX, wallEndY, startPoseHeading);


    private static final Pose2d park = new Pose2d(parkX, wallEndY, startPoseHeading);


    public static Pose2d getStart() {
        return start;
    }

    public static Pose2d getScore1() {
        return score1;
    }

    public static Pose2d getScore2() {
        return score2;
    }

    public static Pose2d getScore3() {
        return score3;
    }

    public static Pose2d getScore4() {
        return score4;
    }

    public static Pose2d getScore5() {
        return score5;
    }

    public static Pose2d getMidWayMoveSpecimens() {
        return midWayMoveSpecimens;
    }

    public static Pose2d getMoveSpecimensStart0() {
        return moveSpecimensStart0;
    }

    public static Pose2d getMoveSpecimenStart1() {
        return moveSpecimenStart1;
    }

    public static Pose2d getMoveSpecimenStart2() {
        return moveSpecimenStart2;
    }

    public static Pose2d getMoveSpecimenStart3() {
        return moveSpecimenStart3;
    }

    public static Pose2d getMoveSpecimenEnd1() {
        return moveSpecimenEnd1;
    }

    public static Pose2d getMoveSpecimenEnd2() {
        return moveSpecimenEnd2;
    }

    public static Pose2d getMoveSpecimenEnd3() {
        return moveSpecimenEnd3;
    }

    public static Pose2d getIntakeStart() {
        return intakeStart;
    }
    public static Pose2d getIntakeEnd() {
        return intakeEnd;
    }

    public static Pose2d getPark() {
        return park;
    }

    public static double getMidWayMoveSpecimensTangent() {
        return midWayMoveSpecimensTangent;
    }
}
