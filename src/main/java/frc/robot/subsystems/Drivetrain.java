// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.rainstorm.subsystem.DemoDrivetrain;
import frc.rainstorm.subsystem.DemoSubsystemBase;
import frc.robot.Constants.DefaultDemoSpeeds;
import frc.robot.Constants.RobotMap;

public class Drivetrain extends DemoSubsystemBase implements DemoDrivetrain {

    private Victor left;
    private Victor right;

    private DifferentialDrive drive;

    public Drivetrain() {
        super(DefaultDemoSpeeds.DRIVETRAIN);

        left = new Victor(RobotMap.LEFT);
        right = new Victor(RobotMap.RIGHT);

        left.setInverted(true);
        right.setInverted(false);

        drive = new DifferentialDrive(left, right);

        CommandScheduler.getInstance().registerSubsystem(this);
    }

    @Override
    public void tankDrive(double leftPower, double rightPower) {
        drive.tankDrive(parsePowerLimit(leftPower), parsePowerLimit(rightPower), false);
    }

    @Override
    public void arcadeDrive(double speed, double rotation) {
        drive.arcadeDrive(parsePowerLimit(speed), parsePowerLimit(rotation), false);
    }

    public void stop() {
        tankDrive(0d, 0d);
    }
}
