/**
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

    private static final double functionEndPoint = 0.5;
    private double accelAmount = 0.001;
    private double deccelAmount = 0.05;
    private double targetAmount = 0;
    private double currentAmount = 0;

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

    public double getCurInput(double x) {
        targetAmount = x;
        long curTime = System.currentTimeMillis();
        if (curTime % 10 == 0 && Math.abs(targetAmount - currentAmount) >= accelAmount) {
            if (targetAmount < currentAmount) {
                currentAmount -= deccelAmount;
                //JOSHUA, ALEXEI AND NEIL WERE HERE
            }else if (targetAmount > currentAmount) {
                currentAmount += accelAmount;
            }
        }
        return targetAmount;
        /*double finReturn = 0;

        long neededTime = curTime + (long)(accelAmount * 1000);
        while (System.currentTimeMillis() < neededTime) {
            if (Math.abs(x) < functionEndPoint) {
                finReturn = 4 * Math.pow(x * ((System.currentTimeMillis() - curTime) / accelAmount * 1000), 3); // Gets the percentage of time to desirted input.
            }else {
                finReturn = x;
            }
        }
        if (Math.abs(x) < functionEndPoint) {
            finReturn = 4 * Math.pow(x, 3);
        }else {
            finReturn = x;
        }
        return finReturn;*/
        /*execute.schedule(() -> {
            curInput -
        }, 10, TimeUnit.MILLISECONDS);*/
    }

    @Override
    public void setPowers() {



        chassis.getLeft().setSpeed(getCurInput(-inputs.rightStickY.get()));
        chassis.getRight().setSpeed(getCurInput(inputs.leftStickY.get()));
    }

    @Override
    public void drive(double leftX, double leftY, double rightX, double rightY) {

    }
}
