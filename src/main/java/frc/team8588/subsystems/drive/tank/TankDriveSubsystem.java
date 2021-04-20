/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: TankDriveSubsystem
 * Package: frc.team8588.subsystems.drive*/
package frc.team8588.subsystems.drive.tank;

import frc.team8588.subsystems.drive.DriveDirection;
import frc.team8588.subsystems.drive.DriveSubsystem;

public class TankDriveSubsystem implements DriveSubsystem {

    private TankDriveChassis chassis;
    private TankDriveInputs inputs;

    public TankDriveSubsystem(TankDriveChassis chassis, TankDriveInputs inputs) {
        this.chassis = chassis;
        this.inputs = inputs;
    }

    @Override
    public void drive(double power, DriveDirection direction) {
        switch (direction) {
            case FORWARD:
                chassis.getLeft().setSpeed(power);
                chassis.getRight().setSpeed(-power);
                break;
            case BACKWARD:
                chassis.getLeft().setSpeed(-power);
                chassis.getRight().setSpeed(power);
                break;
            case LEFT:
            case TURN_CCW:
                chassis.getLeft().setSpeed(-power);
                chassis.getRight().setSpeed(-power);
                break;
            case RIGHT:
            case TURN_CW:
                chassis.getLeft().setSpeed(power);
                chassis.getRight().setSpeed(power);
                break;
        }
    }

    @Override
    public void setPowers() {
        chassis.getLeft().setSpeed(-inputs.rightStickY.get());
        chassis.getRight().setSpeed(inputs.leftStickY.get());
    }

    @Override
    public void drive(double leftX, double leftY, double rightX, double rightY) {

    }
}
