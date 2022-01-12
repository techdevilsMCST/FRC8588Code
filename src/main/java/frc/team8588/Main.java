// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team8588;

import edu.wpi.first.wpilibj.RobotBase;
import frc.team8588.subsystems.drive.DriveDirection;
import frc.team8588.subsystems.drive.DriveSubsystem;
import frc.team8588.subsystems.drive.tank.TankDriveSubsystem;

import java.util.Scanner;

/**
 * Do NOT add any static variables to this class, or any initialization at all. Unless you know what
 * you are doing, do not modify this file except to change the parameter class to the startRobot call.
 */



public final class Main
{

    private Main() {}

   /**
    * Main initialization method. Do not perform any initialization here.
    * <p>
    * If you change your main Robot class (name), change the parameter type.
    */
    public static void main(String... args)
    {
        RobotBase.startRobot(Robot::new);
    }
}
