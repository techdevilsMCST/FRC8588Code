/****
 * Made by Tejas Mehta
 * Made on Tuesday, February 01, 2022
 * File Name: MecanumDriveSubsystem
 * Package: frc.team8588.subsystems.drive.mecanum*/
package frc.robot.team8588.subsystems.drive.mecanum;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private void setMotors(double left, double right) {
        chassis.getBackLeft().set(left);
        chassis.getFrontLeft().set(left);

        chassis.getBackRight().set(right);
        chassis.getFrontRight().set(right);
    }

    @Override
    public void drive(double power, DriveDirection direction) {
        // TODO
        switch (direction)
        {
            case FORWARD:
                drive.driveCartesian(power, 0, 0);
                break;

            case BACKWARD:
                drive.driveCartesian(-power, 0, 0);
                break;

            case RIGHT:
                drive.driveCartesian(0, power, 0);
                break;

            case LEFT:
                drive.driveCartesian(0, -power, 0);
                break;

            case TURN_LEFT:
                drive.driveCartesian(0, 0, -power);
                break;

            case TURN_RIGHT:
                drive.driveCartesian(0, 0, power);
                break;
        }
    }

    @Override
    public void drive(double leftX, double leftY, double rightX, double rightY) {
        // TODO
    }

    @Override
    public void resetEncoders() {
        chassis.getFrontLeft().getEncoder().setPosition(0);
        chassis.getFrontRight().getEncoder().setPosition(0);

        chassis.getBackLeft().getEncoder().setPosition(0);
        chassis.getBackRight().getEncoder().setPosition(0);
    }

    @Override
    public boolean moveToPosition(double location, double speed) {
        if(location > 0)
            drive(speed, DriveDirection.FORWARD);
        else
            drive(speed, DriveDirection.BACKWARD);

        double position = Math.abs(chassis.getBackLeft().getEncoder().getPosition());

        return position >= Math.abs((int)location); //use the back left encoder for position tracking (driving in a straight line, doesn't really matter which one we use for arcade)
    }

    @Override
    public boolean moveToPosition(PIDController pid, double location, double speed) {
        double currentLocation = chassis.getBackLeft().getEncoder().getPosition();
        double finalSpeed = Math.min(1, Math.max(0.10, pid.calculate(currentLocation, location))) * speed;

        return moveToPosition(location, finalSpeed);
    }

    @Override
    public void setPowers() {
        double lTrig = inputs.leftTrig.get();
        double rTrig = inputs.rightTrig.get();

        double triggerThreshold = 0.3;

        double power = 0.45;

        // Vary power limits based on state of a trigger
        if (lTrig > triggerThreshold) { // 25% power
            power = 0.25;
            SmartDashboard.putNumber("Power", 25);
        } else if (rTrig > triggerThreshold) { // 100% power
            power = 1;
            SmartDashboard.putNumber("Power", 100);
        } else { // default
            power = 0.5;
            SmartDashboard.putNumber("Power", 50);
        }

       drive.driveCartesian(inputs.leftStickY.get() * - power, inputs.leftStickX.get() * power, inputs.rightStickX.get() * power);

       SmartDashboard.putNumber("Total Current Draw: ", returnCurrentDraw());
       // TODO Field-centric w/ gyro
    }

    public double returnCurrentDraw() {
        return chassis.getBackLeft().getOutputCurrent() + chassis.getBackRight().getOutputCurrent() + chassis.getFrontLeft().getOutputCurrent() + chassis.getFrontRight().getOutputCurrent();
    }

    @Override
    public double debug() {
        return chassis.getBackLeft().getEncoder().getPosition();
    }
}
