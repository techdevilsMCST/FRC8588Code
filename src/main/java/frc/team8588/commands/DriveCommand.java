/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: DriveCommand
 * Package: frc.team8588.commands*/
package frc.team8588.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.team8588.subsystems.drive.DriveSubsystem;
import frc.team8588.usercontrol.GamepadF310;

public class DriveCommand extends CommandBase {

    private DriveSubsystem subsystem;
    private GamepadF310 gamepad;

    public DriveCommand(DriveSubsystem subsystem) {
        this.subsystem = subsystem;
        gamepad = new GamepadF310(0);
        addRequirements(subsystem);
    }



}
