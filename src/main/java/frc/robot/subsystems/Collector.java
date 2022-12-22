// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class Collector extends SubsystemBase {
  public Victor lowerBelt;
  public Victor upperBelt;
  public Victor spinny;

  public Collector() {
    lowerBelt = new Victor(RobotMap.LOWER_BELT);
    upperBelt = new Victor(RobotMap.UPPER_BELT);
    spinny = new Victor(RobotMap.SPINNY);

    spinny.setInverted(true);

    CommandScheduler.getInstance().registerSubsystem(this);
  }

  public void setPower(double power) {
    lowerBelt.set(power);
    upperBelt.set(power);
    spinny.set(power);
  }

  public void runUpperBelt(double power) {
    upperBelt.set(power);
  }

  public void stopUpperBelt() {
    runUpperBelt(0d);
  }

  public void stop() {
    setPower(0);
  }
}
