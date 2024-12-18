// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.SwerveDrive.DriveMode;

public class RobotContainer {

  private CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private SwerveDrive m_driveBase = SwerveDrive.getInstance();

  public static ShuffleboardTab m_mainTab = Shuffleboard.getTab("Main");

  public RobotContainer() {
    m_driveBase.setDefaultCommand(
      new TeleopSwerve(
        m_driverController,
        OperatorConstants.kThrottleAxis,
        OperatorConstants.kStrafeAxis,
        OperatorConstants.kSteerAxis,
        OperatorConstants.kPercentModifier,
        false,
        true
      )
    );

    configureBindings();
  }

  private void configureBindings() {
    m_driverController.a().onTrue(Commands.runOnce(() -> m_driveBase.setDriveMode(DriveMode.XWHEELS), m_driveBase));

    m_driverController.b().onTrue(new TurnToAngle(100));
    m_driverController.x().onTrue(new TurnToAngle(200));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
