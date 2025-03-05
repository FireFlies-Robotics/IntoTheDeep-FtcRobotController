package org.firstinspires.ftc.teamcode.autonomous.coordinates;

import com.acmerobotics.roadrunner.Pose2d;

public class BlueSampleCoordinates {
    private static final double startX = 38.8;
    private static final double startY = 62;
    private static final double scoreX = 56.3;
    private static final double startScoreX = 54;
    private static final double scoreY = 56.3;
    private static final double startScoreY = 54;
    private static final double StartIntake2Y = 43;
    private static final double intake2X = 49;
    private static final double EndIntake2Y = 32;
    private static final double EndIntake2X = 45;

    private static final double intake3X = 59;
    private static final double intake4X = 57;
    private static final double intake4Y = 37;
    private static final double parkX = 26;
    private static final double parkY = 10;
    private static final double startPoseHeading = Math.toRadians(180);
    private static final double scorePoseHeading = Math.toRadians(225);
    private static final double intake2PoseHeading = Math.toRadians(-91);
    private static final double intake3PoseHeading = Math.toRadians(315);


    private static final double parkPoseHeading = Math.toRadians(0);
    private static final double scoreTangent = Math.toRadians(-90);
    private static final double intake4PoseHeading = Math.toRadians(315);
    private static final double intake2HeadingChange = Math.toRadians(45);
    private static final double intake4HeadingChange = Math.toRadians(90);
    private static final Pose2d start = new Pose2d(startX, startY, startPoseHeading);
    private static final Pose2d score = new Pose2d(scoreX, scoreY, scorePoseHeading);

    private static final Pose2d startScore = new Pose2d(startScoreX, startScoreY, scorePoseHeading);

    private static final Pose2d intake2Start = new Pose2d(intake2X, StartIntake2Y, intake2PoseHeading);
    private static final Pose2d intake2End = new Pose2d(EndIntake2X, EndIntake2Y, intake2PoseHeading);

    private static final Pose2d intake3 = new Pose2d(intake3X, StartIntake2Y, intake3PoseHeading);
    private static final Pose2d intake4 = new Pose2d(intake4X, intake4Y, intake4PoseHeading);
    private static final Pose2d park = new Pose2d(parkX, parkY, parkPoseHeading);

    public static Pose2d getStart() {
        return start;
    }

    public static Pose2d getScore() {
        return score;
    }
    public static Pose2d getStartScore(){return startScore;}

    public static Pose2d getIntake2Start() {
        return intake2Start;
    }
    public static Pose2d getIntake2End() {
        return intake2End;
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
