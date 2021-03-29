// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team8588;

import edu.wpi.first.wpilibj.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import frc.team8588.commands.DriveCommand;
import frc.team8588.subsystems.drive.DriveSubsystem;
import frc.team8588.subsystems.drive.tank.TankDriveChassis;
import frc.team8588.subsystems.drive.tank.TankDriveSubsystem;
import frc.team8588.usercontrol.GamepadF310;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private DriveSubsystem driveSubsystem = new TankDriveSubsystem(new TankDriveChassis(new PWMSparkMax(0), new PWMSparkMax(1)));
    private DriveCommand driveCommand = new DriveCommand(driveSubsystem);
    private GamepadF310 gamepad = new GamepadF310(0);

    /** The container for the robot.  Contains subsystems, OI devices, and commands. */
    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link edu.wpi.first.wpilibj.GenericHID GenericHID}, or one of its
     * subclasses ({@link edu.wpi.first.wpilibj.Joystick Joystick} or
     * {@link edu.wpi.first.wpilibj.XboxController XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton JoystickButton}.
     */
    private void configureButtonBindings()
    {

    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public GamepadF310 getGamepad() {
        return gamepad;
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public DriveCommand getDriveCommand()
    {
        // An ExampleCommand will run in autonomous
        return driveCommand;
    }
}
