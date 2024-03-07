// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class RSL extends SubsystemBase {

    public static final double POWER = 0.75d;

    private Victor rslLight;

    public RSL() {
        this.rslLight = new Victor(7);

        // this.rslLight.setNeutralMode(NeutralMode.Brake);
    }
    
    // public double getCurrentVoltage() {
    //     return rslLight.get;
    // }

    public void set(boolean on) {
        set(on ? POWER : 0d);
    }
    public void set(double power) {
        rslLight.set(power);
    }

    @Override
    public void periodic() {}
}
