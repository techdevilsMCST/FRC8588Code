/*
Name: Abigail Prowse
Program: ArcadeDriveSubsytem
This is the starting of the ArcadeDriveSubsytem.  Does not make complete sense yet and definitely needs work
Check out ArcadeDriveChassis and other subsystems.
Date: 3/29/2021
 */

// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team8588.subsystems.drive.arcade;

import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.team8588.subsystems.drive.DriveDirection;
import frc.team8588.subsystems.drive.DriveSubsystem;
import frc.team8588.usercontrol.GamepadF310;


public class ArcadeDriveSubsystem implements DriveSubsystem {
    private ArcadeDriveChassis chassis;

    public ArcadeDriveSubsystem(ArcadeDriveChassis chassis) {
        this.chassis = chassis;
    }

    @Override
    public void drive(double power, DriveDirection direction) {

    }

    @Override
    public void drive(double leftX, double leftY, double rightX, double rightY) {

    }

    @Override
    public void setPowers() {

    }

    // method needs to take in x and y of one joystick.  Also needs to take in power.
    // then needs to change direction according to that.
}
