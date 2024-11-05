package frc.robot.util;

public class SwerveModuleConstants {
    public int driveId;
    public int steerId;
    public int steerEncoderId;
    public double offset;

    public SwerveModuleConstants(int driveId, int steerId, int steerEncoderId, double offset) {
        this.driveId = driveId;
        this.steerId = steerId;
        this.steerEncoderId = steerEncoderId;
        this.offset = offset;
    }
}
