/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: DriveSubsystem
 * Package: frc.team8588.subsystems*/
package frc.team8588.subsystems.drive;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface DriveSubsystem extends Subsystem {

    void drive(double power, DriveDirection direction);

    void drive(double leftX, double leftY, double rightX, double rightY);

    void setPowers();

}
