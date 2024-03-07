// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.FlashRSL;
import frc.robot.commands.RunCollector;
import frc.robot.commands.RunShooter;
import frc.robot.commands.RunSpindexer;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.RSL;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Spindexer;

public class RobotContainer {
    private final RSL rsl = new RSL();
    private final Drivetrain drivetrain = new Drivetrain();
    private final Collector collector = new Collector();
    private final Spindexer spindexer = new Spindexer(collector);
    private final Shooter shooter = new Shooter();

    private final XboxController controller = new XboxController(0);

    public RobotContainer() {
        configureButtonBindings();
        rsl.setDefaultCommand(new FlashRSL(rsl));

        // drivetrain.setDefaultCommand(new ArcadeDrive(
        //     drivetrain,
        //     () -> controller.getLeftTriggerAxis() * drivetrain.getDemoSpeed(),
        //     () -> controller.getRightTriggerAxis() * drivetrain.getDemoSpeed(),
        //     () -> -controller.getLeftX() * drivetrain.getDemoSpeed()
        // ));

        spindexer.setDefaultCommand(new RunSpindexer(spindexer, () -> (controller.getRightTriggerAxis() - controller.getLeftTriggerAxis())));
    }

    private void configureButtonBindings() {
        new Trigger(controller::getAButton).whileTrue(new RunShooter(shooter, () -> 0.75d));
        // new Trigger(controller::getBButton).whileTrue(new RunCollector(collector, () -> 1d));
        new Trigger(controller::getLeftBumper).whileTrue(new RunCollector(collector, () -> -1d));
        new Trigger(controller::getRightBumper).whileTrue(new RunCollector(collector, () -> 1d));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
