// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LightningShuffleboard;
import frc.robot.constants.RobotMap;

public class Drivetrain extends SubsystemBase {
  private Victor left;
  private Victor right;

  private DifferentialDrive drive;

  private double demoSpeedVal;

  public Drivetrain() {
    left = new Victor(RobotMap.LEFT);
    right = new Victor(RobotMap.RIGHT);

    left.setInverted(true);
    right.setInverted(false);

    drive = new DifferentialDrive(left, right);

    CommandScheduler.getInstance().registerSubsystem(this);
  }

  @Override
  public void periodic() {
    super.periodic();

    demoSpeedVal = LightningShuffleboard.getDouble("Demo", "demoSpeed", 0.5);
  }

  public void tankDrive(double leftPower, double rightPower) {
    // not sure if these are needed
    leftPower *= 0.5;
    rightPower *= 0.5;
    drive.tankDrive(leftPower, rightPower, false);
  }

  public void arcadeDrive(double speed, double rotation) {
    drive.arcadeDrive(speed, rotation, false);
  }

  public void stop() {
    tankDrive(0d, 0d);
  }

  public double getDemoSpeed() {
    return demoSpeedVal;
  }
}
