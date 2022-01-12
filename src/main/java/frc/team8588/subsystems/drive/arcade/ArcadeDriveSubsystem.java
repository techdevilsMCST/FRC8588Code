/*
Name: Abigail Prowse
Program: ArcadeDriveSubsytem
This is the starting of the ArcadeDriveSubsytem.  Does not make complete sense yet and definitely needs work
Check out ArcadeDriveChassis and other subsystems.
Date: 3/29/2021
 */

// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.team8588.subsystems.drive.arcade;

import frc.team8588.subsystems.drive.DriveDirection;
import frc.team8588.subsystems.drive.DriveSubsystem;


public class ArcadeDriveSubsystem implements DriveSubsystem {
    private ArcadeDriveChassis chassis;
    private ArcadeDriveInputs inputs;

    public ArcadeDriveSubsystem(ArcadeDriveChassis chassis, ArcadeDriveInputs inputs) {
        this.chassis = chassis;
        this.inputs = inputs;
    }

    @Override
    public void drive(double power, DriveDirection direction) {

    }

    @Override
    public void drive(double leftX, double leftY, double rightX, double rightY) {
        /*
        double direction;
        // calculates the direction off of left joystick values
        if ((Math.abs(leftX) < 0.1 && Math.abs(leftY) < 0.1) {
            // center
            direction = 0;
            // sets direction to 0, meaning there is no movement.
        }
        else if (leftX > 0 && leftY > 0) {
            // first quadrant
            direction = Math.atan(leftY/leftX);
            // uses the arctangent of the triangle made by the x and y values to find the angle to drive in.
        }
        else if (leftX < 0 && leftY > 0) {
            direction = Math.atan(leftY/leftX) + Math.PI * 3 / 2;
            // uses the arctangent of the triangle made by the x and y values to find the angle to drive in. + 270 degrees.
        }
        else if (leftX < 0 && leftY < 0) {
            // third quadrant
            direction = Math.atan(leftY/leftX) + Math.PI;
            // uses the arctangent of the triangle made by the x and y values to find the angle to drive in. + 180 degrees.
        }
        else if (leftX > 0 && leftY < 0) {
            direction = Math.atan(leftY/leftX) + Math.PI / 2;
            // uses the arctangent of the triangle made by the x and y values to find the angle to drive in. + 90 degrees
        }

        double speed = Math.sqrt(leftX * leftX + leftY * leftY);
        // calculates the speed by the hypotenuse of the said triangle.
        */

//        chassis.getLeft.setLeft(leftX + leftY);
//        chassis.getRight.setRight(leftX - leftY);
    }

    @Override
    public void setPowers() {
        chassis.getLeft().setSpeed((-inputs.xStick.get() - inputs.yStick.get())/2.0);
        chassis.getRight().setSpeed((-inputs.xStick.get() + inputs.yStick.get())/2.0);
    }

    // method needs to take in x and y of one joystick.  Also needs to take in power.
    // then needs to change direction according to that.
}

