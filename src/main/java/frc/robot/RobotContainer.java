// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.controller.PIDController;
import frc.robot.team8588.commands.DriveCommand;
import frc.robot.team8588.subsystems.drive.DriveSubsystem;
import frc.robot.team8588.subsystems.drive.arcade.ArcadeDriveChassis;
import frc.robot.team8588.subsystems.drive.arcade.ArcadeDriveInputs;
import frc.robot.team8588.subsystems.drive.arcade.ArcadeDriveSubsystem;
import frc.robot.team8588.subsystems.drive.mecanum.MecanumDriveChassis;
import frc.robot.team8588.subsystems.drive.mecanum.MecanumDriveInputs;
import frc.robot.team8588.subsystems.drive.mecanum.MecanumDriveSubsystem;
import frc.robot.team8588.subsystems.drive.tank.TankDriveChassis;
import frc.robot.team8588.subsystems.drive.tank.TankDriveInputs;
import frc.robot.team8588.subsystems.drive.tank.TankDriveSubsystem;
import frc.robot.team8588.usercontrol.GamepadF310;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private GamepadF310 gamepad = new GamepadF310(0);
    private DriveSubsystem driveSubsystem = new ArcadeDriveSubsystem(
                new ArcadeDriveChassis(
                        new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushed),
                        new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushed),
                        new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushed),
                        new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushed)
                ),
                new ArcadeDriveInputs(gamepad::getRightX, gamepad::getLeftY, gamepad::getLeftTrigger, gamepad::getRightTrigger));

    private DriveCommand driveCommand = new DriveCommand(driveSubsystem);

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
