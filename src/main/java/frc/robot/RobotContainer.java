// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.rainstorm.RainstormContainer;
import frc.rainstorm.command.ArcadeDrive;
import frc.rainstorm.command.TankDrive;
import frc.rainstorm.subsystem.GenericSubsystem;
import frc.robot.Constants.DefaultDemoSpeeds;
import frc.robot.Constants.RobotMap;
import frc.robot.commands.FlashRSL;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Spindexer;

public class RobotContainer extends RainstormContainer {

    private final GenericSubsystem rsl = new GenericSubsystem("RSL", 0.75,
            new Victor(RobotMap.RSL));

    private Drivetrain drivetrain = new Drivetrain();
    private Collector collector = new Collector();
    private Spindexer spindexer = new Spindexer(collector);

    private GenericSubsystem shooter = new GenericSubsystem("Shooter", DefaultDemoSpeeds.SHOOTER,
            new Victor(RobotMap.FLYWHEEL));

    public RobotContainer() {
        super(new XboxController(0));

        configureButtonBindings();
        configureDefaultCommands();
    }

    @Override
    protected void configureButtonBindings() {
        new Trigger(controller::getBButton).whileTrue(shooter.getStartEndCommand(() -> 0.75d));
        new Trigger(controller::getLeftBumper).whileTrue(spindexer.getStartEndCommand(() -> -1d));
        new Trigger(controller::getRightBumper).whileTrue(spindexer.getStartEndCommand(() -> 1d));
    }

    @Override
    protected void configureDefaultCommands() {
        rsl.setDefaultCommand(new FlashRSL(rsl));

        drivetrain.setDefaultCommand(
                new ArcadeDrive(drivetrain, () -> controller.getLeftY(), () -> controller.getRightX()));
        // drivetrain.setDefaultCommand(
        //         new TankDrive(drivetrain, () -> controller.getLeftY(), () -> controller.getRightY()));

        // // Run spindexer based on controller triggers (right - left)
        collector.setDefaultCommand(
                new RunCommand(() -> collector.setPower(super.getTrigger().getAsDouble()), collector));
    }

}
