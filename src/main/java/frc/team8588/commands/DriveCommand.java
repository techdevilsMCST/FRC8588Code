/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: DriveCommand
 * Package: frc.team8588.commands*/
package frc.team8588.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team8588.subsystems.drive.DriveDirection;
import frc.team8588.subsystems.drive.DriveSubsystem;

public class DriveCommand extends CommandBase {

    private DriveSubsystem subsystem;
    double power = 0.0;

    public DriveCommand(DriveSubsystem subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.drive(power, DriveDirection.FORWARD);
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
