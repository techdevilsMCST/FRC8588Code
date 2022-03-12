/****
 * Made by Tejas Mehta
 * Made on Tuesday, February 01, 2022
 * File Name: MecanumDriveSubsystem
 * Package: frc.team8588.subsystems.drive.mecanum*/
package frc.robot.team8588.subsystems.drive.mecanum;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.team8588.subsystems.drive.DriveDirection;
import frc.robot.team8588.subsystems.drive.DriveSubsystem;

public class MecanumDriveSubsystem implements DriveSubsystem {

    private MecanumDriveInputs inputs;
    private MecanumDrive drive;
    private MecanumDriveChassis chassis;

    private double power = 1;

    public MecanumDriveSubsystem(MecanumDriveChassis chassis, MecanumDriveInputs inputs) {
        this.inputs = inputs;

        this.chassis = chassis;

        this.drive = new MecanumDrive(
                chassis.getFrontLeft(),
                chassis.getBackLeft(),
                chassis.getFrontRight(),
                chassis.getBackRight()
        );
        chassis.getFrontRight().setSmartCurrentLimit(40);
        chassis.getFrontLeft().setSmartCurrentLimit(40);
        chassis.getBackRight().setSmartCurrentLimit(40);
        chassis.getBackLeft().setSmartCurrentLimit(40);
    }

    private void setMotors(double left, double right) {
        chassis.getBackLeft().set(left);
        chassis.getFrontLeft().set(left);

        chassis.getBackRight().set(right);
        chassis.getFrontRight().set(right);
    }

    @Override
    public boolean strafeToPosition(double a, double b) {
        // do nothing
        return false;
    }

    public boolean strafeToPosition(PIDController p, double b, double c) {
        // do nothing
        return false;
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

        if(position >= Math.abs((int)location))
            drive(0, DriveDirection.FORWARD);

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

        drive.driveCartesian(inputs.leftStickY.get() * - power, inputs.leftStickX.get() * power, inputs.rightStickX.get() * power);

       SmartDashboard.putNumber("Total Current Draw: ", returnCurrentDraw());
    }

    @Override
    public void setPowersFO(AHRS ahrs) {
        double power = inputs.powerMultiplier.get();
        try {
            drive.driveCartesian(inputs.leftStickY.get() * -power, inputs.leftStickX.get() * power, inputs.rightStickX.get() * power/*, ahrs.getAngle() % 360*/);
            SmartDashboard.putNumber("Current Angle: ", ahrs.getAngle());

            double temperature = (chassis.getBackLeft().getMotorTemperature() + chassis.getBackRight().getMotorTemperature() +chassis.getFrontLeft().getMotorTemperature() +chassis.getFrontRight().getMotorTemperature()) / 4;
            SmartDashboard.putNumber("Average motor temperature: ", temperature);

            SmartDashboard.putNumber("Total Current Draw: ", returnCurrentDraw());

        } catch (Exception ex) {
            DriverStation.reportError("Error communicating with drive system: " + ex.getMessage(), true);
        }
    }

    public void setBrake() {
        chassis.getBackLeft().setIdleMode(CANSparkMax.IdleMode.kBrake);
        chassis.getBackRight().setIdleMode(CANSparkMax.IdleMode.kBrake);
        chassis.getFrontLeft().setIdleMode(CANSparkMax.IdleMode.kBrake);
        chassis.getFrontRight().setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void setCoast() {
        chassis.getBackLeft().setIdleMode(CANSparkMax.IdleMode.kCoast);
        chassis.getBackRight().setIdleMode(CANSparkMax.IdleMode.kCoast);
        chassis.getFrontLeft().setIdleMode(CANSparkMax.IdleMode.kCoast);
        chassis.getFrontRight().setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    public void STOP() {
        setBrake();
        chassis.getBackLeft().set(0);
        chassis.getBackRight().set(0);
        chassis.getFrontLeft().set(0);
        chassis.getFrontRight().set(0);
    }

    public void halfPower() {
        power = 0.35;
    }

    public void fullPower() {
        power = 1;
    }

    public double returnCurrentDraw() {
        return chassis.getBackLeft().getOutputCurrent() + chassis.getBackRight().getOutputCurrent() + chassis.getFrontLeft().getOutputCurrent() + chassis.getFrontRight().getOutputCurrent();
    }

    @Override
    public double debug() {
        return chassis.getBackLeft().getEncoder().getPosition();
    }
}
