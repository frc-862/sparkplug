// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RSL;

public class FlashRSL extends Command {

    private RSL rsl;

    private int count = 0;
    private boolean state = true;

    public FlashRSL(RSL rsl) {
        this.rsl = rsl;

        addRequirements(rsl);
    }

    @Override
    public void initialize() {
        rsl.set(true);

    }

    @Override
    public void execute() {
        count++;
        if (count == 15) {
            state = !state;
            rsl.set(state);
            count = 0;
        }
    }

    @Override
    public void end(boolean interrupted) {
        rsl.set(false);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
