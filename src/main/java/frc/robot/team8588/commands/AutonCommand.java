package frc.robot.team8588.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.team8588.subsystems.drive.DriveSubsystem;
import frc.robot.team8588.subsystems.intake.IntakeSubsystem;

public class AutonCommand extends SequentialCommandGroup {

    private final DriveSubsystem subsystem;
    private final IntakeSubsystem intakeSubsystem;

    public AutonCommand(DriveSubsystem subsystem, IntakeSubsystem intakeSubsystem) {
        this.subsystem = subsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(subsystem, intakeSubsystem);
        addCommands(
                // Shoot a preloaded ball into low hoop
                new InstantCommand(intakeSubsystem::runFlywheelHIGH),
                new WaitCommand(2),
                new InstantCommand(intakeSubsystem::runIndexer),
                // wait for ball to get shot
                new WaitCommand(2),
                // stop motors
                new InstantCommand(intakeSubsystem::stopAll),
                // reset encoders
                new InstantCommand(subsystem::resetEncoders),
                // autobots, roll out
                new RunCommand(() -> {subsystem.moveToPosition(-60,0.5);})
        );
    }
}
