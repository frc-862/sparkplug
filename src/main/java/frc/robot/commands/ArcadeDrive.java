// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class ArcadeDrive extends Command {
  Drivetrain drivetrain;
  DoubleSupplier throttleF;
  DoubleSupplier throttleR;
  DoubleSupplier rotation;

  public ArcadeDrive(Drivetrain drivetrain, DoubleSupplier throttleF, DoubleSupplier throttleR, DoubleSupplier rotation) {
    this.drivetrain = drivetrain;
    this.throttleF = throttleF;
    this.throttleR = throttleR;
    this.rotation = rotation;

    addRequirements(drivetrain);
  }

  @Override
  public void execute() {
    drivetrain.arcadeDrive(throttleF.getAsDouble() - throttleR.getAsDouble(), rotation.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
