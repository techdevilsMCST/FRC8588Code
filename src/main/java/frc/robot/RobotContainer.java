// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.team8588.commands.AutonCommand;
import frc.robot.team8588.commands.DriveCommand;
import frc.robot.team8588.subsystems.drive.DriveSubsystem;
import frc.robot.team8588.subsystems.drive.mecanum.MecanumDriveChassis;
import frc.robot.team8588.subsystems.drive.mecanum.MecanumDriveInputs;
import frc.robot.team8588.subsystems.drive.mecanum.MecanumDriveSubsystem;
import frc.robot.team8588.subsystems.intake.IntakeChassis;
import frc.robot.team8588.subsystems.intake.IntakeSubsystem;
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
    private DriveSubsystem driveSubsystem = new MecanumDriveSubsystem(
                new MecanumDriveChassis(
                        new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless),
                        new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless),
                        new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless),
                        new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless)
                ),
                new MecanumDriveInputs(gamepad::getLeftY, gamepad::getLeftX, gamepad::getRightX)
    );


    private IntakeSubsystem intakeSubsystem = new IntakeSubsystem(
            new IntakeChassis(
                    new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless),
                    new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless),
                    new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless),
                    new CANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless)
            )
    );

    private DriveCommand driveCommand = new DriveCommand(driveSubsystem);
    private AutonCommand autonCommand = new AutonCommand(driveSubsystem, intakeSubsystem);

    /** The container for the robot.  Contains subsystems, OI devices, and commands. */
    public RobotContainer(AHRS ahrs)
    {
        // Configure the button bindings
        configureButtonBindings(ahrs);
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link edu.wpi.first.wpilibj.GenericHID GenericHID}, or one of its
     * subclasses ({@link edu.wpi.first.wpilibj.Joystick Joystick} or
     * {@link edu.wpi.first.wpilibj.XboxController XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton JoystickButton}.
     */
    private void configureButtonBindings(AHRS ahrs)
    {
        // Brought to you by Val's lack of mental stability, his TIDAL playlist, and the WPILibJ2 command library.
        // Thank God for inline definitions.

        // ABXY SECTION //

        // When A is pressed, stop intake, indexer, and shooter
        new JoystickButton(gamepad.joystick, GamepadF310.GAMEPAD_A)
                .whenPressed(new InstantCommand(intakeSubsystem::stopAll));

        // While B is held, 35% power
        new JoystickButton(gamepad.joystick, GamepadF310.GAMEPAD_B)
                .whenHeld(new RunCommand(driveSubsystem::halfPower))
                .whenReleased(new RunCommand(driveSubsystem::fullPower));

        // When X is pressed, toggle between braking and coasting
        new JoystickButton(gamepad.joystick, GamepadF310.GAMEPAD_X)
                .toggleWhenPressed(new StartEndCommand(driveSubsystem::setBrake, driveSubsystem::setCoast, driveSubsystem));

        // When Y is pressed, reset bot heading for Field Oriented Drive
        new JoystickButton(gamepad.joystick, GamepadF310.GAMEPAD_Y)
                .whenPressed(new InstantCommand(ahrs::reset));

        // BUMPERS AND TRIGGERS SECTION //

        // When LB is pressed, intake IN
        new JoystickButton(gamepad.joystick, GamepadF310.GAMEPAD_LEFT_BUMPER)
                .whenPressed(new InstantCommand(intakeSubsystem::intakeIn));

        // When RB is pressed, intake OUT
        new JoystickButton(gamepad.joystick, GamepadF310.GAMEPAD_RIGHT_BUMPER)
                .whenPressed(new InstantCommand(intakeSubsystem::intakeOut));

        double triggerThreshold = 0.3;

        // When LT is held, spin up the flywheel to half speed, wait two seconds, then run indexer ( LOW SHOT )
        new Trigger(() -> { return gamepad.getLeftTrigger() > triggerThreshold; })
                .whileActiveOnce(new SequentialCommandGroup(new InstantCommand(intakeSubsystem::runFlywheelLOW), new WaitCommand(2.5), new InstantCommand(intakeSubsystem::runIndexer)))
                .whenInactive(new InstantCommand(intakeSubsystem::stopAll));

        // When RT is held, spin up the flywheel to full speed, wait two seconds, then run indexer ( HIGH SHOT )
        new Trigger(() -> { return gamepad.getRightTrigger() > triggerThreshold; })
                .whileActiveOnce(new SequentialCommandGroup(new InstantCommand(intakeSubsystem::runFlywheelHIGH), new WaitCommand(3), new InstantCommand(intakeSubsystem::runIndexer)))
                .whenInactive(new InstantCommand(intakeSubsystem::stopAll));
    }

    public DriveSubsystem getDriveSubsystem() {
        return driveSubsystem;
    }

    public GamepadF310 getGamepad() {
        return gamepad;
    }

    /**
     * Use this to pass the teleop command to the main {@link Robot} class.
     *
     * @return the command to run in teleop
     */
    public DriveCommand getDriveCommand()
    {
        // driveCommand will run in teleop
        return driveCommand;
    }

    public AutonCommand getAutonCommand() {
        // autonCommand will run in autonomous
        return autonCommand;
    }

    public IntakeSubsystem getIntake() {
        return intakeSubsystem;
    }
}
