package com.example.MeepMeep.Coordinates.CoordinatesFireFlies;

import com.acmerobotics.roadrunner.Pose2d;

public class RedSampleCoordinatesMeepMeep {
    private static final double startX = -40.5;
    private static final double startY = -63.75;
    private static final double scoreX = -57;
    private static final double scoreY = -57;
    private static final double intake2X = -49;
    private static final double intake2Y = -40;
    private static final double intake3X = -60;
    private static final double intake4X = -57;
    private static final double intake4Y = -37;
    private static final double parkX = -26;
    private static final double parkY = -10;
    private static final double startPoseHeading = Math.toRadians(0);
    private static final double scorePoseHeading = Math.toRadians(45);
    private static final double intake2PoseHeading = Math.toRadians(90);
    private static final double parkPoseHeading = Math.toRadians(180);
    private static final double scoreTangent = Math.toRadians(90);
    private static final double intake4PoseHeading = Math.toRadians(135);
    private static final double intake2HeadingChange = Math.toRadians(125);
    private static final double intake4HeadingChange = Math.toRadians(270);
    private static final Pose2d start = new Pose2d(startX, startY, startPoseHeading);
    private static final Pose2d score = new Pose2d(scoreX, scoreY, scorePoseHeading);
    private static final Pose2d intake2 = new Pose2d(intake2X, intake2Y, intake2PoseHeading);
    private static final Pose2d intake3 = new Pose2d(intake3X, intake2Y, intake2PoseHeading);
    private static final Pose2d intake4 = new Pose2d(intake4X, intake4Y, intake4PoseHeading);
    private static final Pose2d park = new Pose2d(parkX, parkY, parkPoseHeading);

    public static Pose2d getStart() {
        return start;
    }

    public static Pose2d getScore() {
        return score;
    }

    public static Pose2d getIntake2() {
        return intake2;
    }

    public static Pose2d getIntake3() {
        return intake3;
    }

    public static Pose2d getIntake4() {
        return intake4;
    }

    public static Pose2d getPark() {
        return park;
    }

    public static double getScoreTangent() {
        return scoreTangent;
    }

    public static double getIntake2HeadingChange() {
        return intake2HeadingChange;
    }

    public static double getIntake4HeadingChange() {
        return intake4HeadingChange;
    }
}
