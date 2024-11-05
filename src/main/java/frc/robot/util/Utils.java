package frc.robot.util;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class Utils {
    public static void optimize(
      SwerveModuleState desiredState, Rotation2d currentAngle) {
    double targetAngle =
        placeInAppropriate0To360Scope(currentAngle.getDegrees(), desiredState.angle.getDegrees());
    double targetSpeed = desiredState.speedMetersPerSecond;
    double delta = targetAngle - currentAngle.getDegrees();
    if (Math.abs(delta) > 90) {
      targetSpeed = -targetSpeed;
      targetAngle = delta > 90 ? (targetAngle -= 180) : (targetAngle += 180);
    }

    desiredState.speedMetersPerSecond = targetSpeed;
    desiredState.angle = Rotation2d.fromDegrees(targetAngle);
  }

    /**
   * @param scopeReference Current Angle
   * @param newAngle Target Angle
   * @return Closest angle within scope
   */
  private static double placeInAppropriate0To360Scope(double scopeReference, double newAngle) {
    double lowerBound;
    double upperBound;
    double lowerOffset = scopeReference % 360;
    if (lowerOffset >= 0) {
      lowerBound = scopeReference - lowerOffset;
      upperBound = scopeReference + (360 - lowerOffset);
    } else {
      upperBound = scopeReference - lowerOffset;
      lowerBound = scopeReference - (360 + lowerOffset);
    }
    while (newAngle < lowerBound) {
      newAngle += 360;
    }
    while (newAngle > upperBound) {
      newAngle -= 360;
    }
    if (newAngle - scopeReference > 180) {
      newAngle -= 360;
    } else if (newAngle - scopeReference < -180) {
      newAngle += 360;
    }
    return newAngle;
  }

  public static void copyModuleStates(SwerveModuleState[] copier, SwerveModuleState[] reciever) {
    for (int i = 0; i < copier.length; i++) {
      reciever[i] = new SwerveModuleState(copier[i].speedMetersPerSecond, copier[i].angle);
    }
  }

  public static double getAdjustedYawDegrees(double initialvalue, double addedValue) {
    double numTo180 = 180 - addedValue;

    return (initialvalue + numTo180) % 360 < 0
        ? ((initialvalue + numTo180) % 360) + 360.0
        : ((initialvalue + numTo180) % 360);
  }
}
