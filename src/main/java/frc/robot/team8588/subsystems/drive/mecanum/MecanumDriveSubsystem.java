/****
 * Made by Tejas Mehta
 * Made on Tuesday, February 01, 2022
 * File Name: MecanumDriveSubsystem
 * Package: frc.team8588.subsystems.drive.mecanum*/
package frc.robot.team8588.subsystems.drive.mecanum;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.team8588.subsystems.drive.DriveDirection;
import frc.robot.team8588.subsystems.drive.DriveSubsystem;

public class MecanumDriveSubsystem implements DriveSubsystem {

    private MecanumDriveInputs inputs;
    private MecanumDrive drive;
    private MecanumDriveChassis chassis;

    public MecanumDriveSubsystem(MecanumDriveChassis chassis, MecanumDriveInputs inputs) {
        this.inputs = inputs;

        this.chassis = chassis;

        this.drive = new MecanumDrive(
                chassis.getFrontLeft(),
                chassis.getBackLeft(),
                chassis.getFrontRight(),
                chassis.getBackRight()
        );
    }

    @Override
    public void drive(double power, DriveDirection direction) {
        // TODO
        switch (direction)
        {
            case LEFT:
                break;

            case RIGHT:
                break;
        }
    }

    @Override
    public void drive(double leftX, double leftY, double rightX, double rightY) {
        // TODO
    }

    @Override
    public void resetEncoders() {

    }

    @Override
    public boolean moveToPosition(double location, double speed) {
        return false;
    }

    @Override
    public boolean moveToPosition(PIDController pid, double location, double speed) {
        return false;
    }

    @Override
    public void setPowers() {
       drive.driveCartesian(inputs.leftStickX.get(), inputs.leftStickY.get(), inputs.rightStickX.get());
       // TODO Field-centric w/ gyro
    }

    public double returnCurrentDraw() {
        return chassis.getBackLeft().getOutputCurrent() + chassis.getBackRight().getOutputCurrent() + chassis.getFrontLeft().getOutputCurrent() + chassis.getFrontRight().getOutputCurrent();
    }
}
