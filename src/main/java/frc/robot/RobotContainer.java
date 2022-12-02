// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.sound.midi.ControllerEventListener;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.RunCollector;
import frc.robot.commands.RunShooter;
import frc.robot.commands.RunSpindexer;
import frc.robot.commands.TankDrive;
import frc.robot.constants.JoystickConstants;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Spindexer;

public class RobotContainer {
  private final Drivetrain drivetrain = new Drivetrain();
  private final Collector collector = new Collector();
  private final Spindexer spindexer = new Spindexer(collector);
  private final Shooter shooter = new Shooter();

  private final XboxController controller = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();
    drivetrain.setDefaultCommand(new TankDrive(drivetrain, () -> controller.getLeftY(), () -> controller.getRightY()));
  }

  private void configureButtonBindings() {
    (new JoystickButton(controller, JoystickConstants.BUTTON_A)).whileHeld(new RunShooter(shooter, 1d));
    (new JoystickButton(controller, JoystickConstants.BUTTON_B)).whileHeld(new RunCollector(collector, () -> 1d));
    (new JoystickButton(controller, JoystickConstants.LEFT_BUMPER)).whileHeld(new RunSpindexer(spindexer, () -> -1));
    (new JoystickButton(controller, JoystickConstants.RIGHT_BUMPER)).whileHeld(new RunSpindexer(spindexer, () -> 1));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
