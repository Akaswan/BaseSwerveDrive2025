package frc.robot.util;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;

import frc.robot.Constants.DriveConstants;

public class DeviceConfigurator {
    public static void configureSparkMaxSteerMotor(CANSparkMax motor) {
        RelativeEncoder encoder = motor.getEncoder();
        SparkPIDController controller = motor.getPIDController();

        motor.setInverted(true);
        motor.setSmartCurrentLimit(40);
        motor.setIdleMode(IdleMode.kBrake);
        
        encoder.setPositionConversionFactor(DriveConstants.kTurnRotationsToDegrees);
        
        controller.setP(DriveConstants.turnkp);
        controller.setI(DriveConstants.turnki);
        controller.setD(DriveConstants.turnkd);
        controller.setFF(DriveConstants.turnkff);
    }

    public static void configureSparkFlexDriveMotor(CANSparkFlex motor) {
        RelativeEncoder encoder = motor.getEncoder();
        SparkPIDController controller = motor.getPIDController();

        motor.setInverted(true);
        motor.setSmartCurrentLimit(80);
        motor.setIdleMode(IdleMode.kBrake);
        motor.setOpenLoopRampRate(DriveConstants.driverampRate);
        
        encoder.setPositionConversionFactor(DriveConstants.kDriveRevToMeters);
        encoder.setVelocityConversionFactor(DriveConstants.kDriveRpmToMetersPerSecond);
        
        controller.setP(DriveConstants.drivekp);
        controller.setI(DriveConstants.driveki);
        controller.setD(DriveConstants.drivekd);
        controller.setFF(DriveConstants.drivekff);
    }

    public static void configureCANcoder(CANcoder encoder) {
        CANcoderConfiguration configuration = new CANcoderConfiguration();

        encoder.getConfigurator().apply(configuration);

        configuration.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Unsigned_0To1;
        configuration.MagnetSensor.MagnetOffset = 0;
        configuration.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;

        encoder.getConfigurator().apply(configuration);
    }
}
