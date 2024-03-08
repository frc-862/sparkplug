// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.rainstorm.subsystem.GenericSubsystem;
import frc.robot.Constants.DefaultDemoSpeeds;
import frc.robot.Constants.RobotMap;

public class Spindexer extends GenericSubsystem {

    private Collector collector;

    public Spindexer(Collector collector) {
        super(DefaultDemoSpeeds.SHOOTER, new Victor(RobotMap.SPINDEXER));

        this.collector = collector;

        CommandScheduler.getInstance().registerSubsystem(this);
    }

    @Override
    public void setPower(double power) {
        motor.set(parsePowerLimit(power));
        collector.setUpperBelt(parsePowerLimit(power));
    }

    @Override
    public void stop() {
        setPower(0d);
        collector.stopUpperBelt();
    }
}
