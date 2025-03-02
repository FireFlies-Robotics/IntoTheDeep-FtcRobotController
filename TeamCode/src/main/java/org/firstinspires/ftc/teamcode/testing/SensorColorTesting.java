package org.firstinspires.ftc.teamcode.testing;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "Sensor: Color", group = "Sensor")
public class SensorColorTesting extends LinearOpMode {
  private boolean isBlueAlliance = false;

  private final float GAIN = 23;
  private final float RED_HUE_LOW = 20;
  private final float RED_HUE_HIGH = 330;
  private final float BLUE_HUE_LOW = 180;
  private final float BLUE_HUE_HIGH = 230;
  private final float YELLOW_HUE_LOW = 21;
  private final float YELLOW_HUE_HIGH = 70;
  private final float MAX_HUE = 375;
  private final float MIN_HUE = 0;

  NormalizedColorSensor colorSensor;
  View relativeLayout;

  @Override
  public void runOpMode() {
    int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
    relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);

    // Alliance selection before match starts
    while (!isStarted() && !isStopRequested()) {
      if (gamepad1.cross) {
        isBlueAlliance = true;
        gamepad1.rumble(200);
        telemetry.addLine("cross pressed");
      }
      if (gamepad1.circle) {
        isBlueAlliance = false;
        gamepad1.rumble(200);
        telemetry.addLine("circle pressed");

      }
      telemetry.addData("isBlue=", isBlueAlliance);
      telemetry.update();
    }

    waitForStart();
    runSample();
  }

  protected void runSample() {
    float gain = GAIN;
    final float[] hsvValues = new float[3];
    boolean xButtonPreviouslyPressed = false;
    boolean xButtonCurrentlyPressed = false;

    colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
    if (colorSensor instanceof SwitchableLight) {
      ((SwitchableLight) colorSensor).enableLight(true);
    }

    while (opModeIsActive()) {
      if (gamepad1.a) {
        gain += 0.05;
      } else if (gamepad1.b && gain > 1) {
        gain -= 0.05;
      }

      telemetry.addData("Gain", gain);
      colorSensor.setGain(gain);

      xButtonCurrentlyPressed = gamepad1.x;
      if (xButtonCurrentlyPressed != xButtonPreviouslyPressed) {
        if (xButtonCurrentlyPressed && colorSensor instanceof SwitchableLight) {
          SwitchableLight light = (SwitchableLight) colorSensor;
          light.enableLight(!light.isLightOn());
        }
      }
      xButtonPreviouslyPressed = xButtonCurrentlyPressed;

      NormalizedRGBA colors = colorSensor.getNormalizedColors();
      Color.colorToHSV(colors.toColor(), hsvValues);

      telemetry.addLine()
              .addData("Red", "%.3f", colors.red)
              .addData("Green", "%.3f", colors.green)
              .addData("Blue", "%.3f", colors.blue);
      telemetry.addLine()
              .addData("Hue", "%.3f", hsvValues[0])
              .addData("Saturation", "%.3f", hsvValues[1])
              .addData("Value", "%.3f", hsvValues[2]);
      telemetry.addData("Alpha", "%.3f", colors.alpha);

      if (colorSensor instanceof DistanceSensor) {
        telemetry.addData("Distance (cm)", "%.3f", ((DistanceSensor) colorSensor).getDistance(DistanceUnit.CM));
      }

      if (hsvValues[2] < 0.3) {
        telemetry.addLine("nothing");
      }
      if ((hsvValues[0] > MIN_HUE && hsvValues[0] < RED_HUE_LOW) || (hsvValues[0] > RED_HUE_HIGH && hsvValues[0] < MAX_HUE)) {
        if (!isBlueAlliance) {
          telemetry.addLine("red");

          gamepad1.rumbleBlips(3);
          gamepad2.rumbleBlips(3);
        }
      }
      if (hsvValues[0] > BLUE_HUE_LOW && hsvValues[0] < BLUE_HUE_HIGH) {
        telemetry.addLine("blue");
        if (isBlueAlliance) {
          gamepad1.rumbleBlips(3);
          gamepad2.rumbleBlips(3);
        }
      }
      if (hsvValues[0] > YELLOW_HUE_LOW && hsvValues[0] < YELLOW_HUE_HIGH) {
        telemetry.addLine("yellow");
        gamepad1.rumble(500);
        gamepad2.rumble(500);
      }

      telemetry.addData("isBlue", isBlueAlliance);
      telemetry.update();

      relativeLayout.post(() -> relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues)));
    }
  }
}