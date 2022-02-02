/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: TankDriveChassis
 * Package: frc.team8588.subsystems.drive.tank*/
package frc.robot.team8588.subsystems.drive.tank;

import com.revrobotics.CANSparkMax;

public class TankDriveChassis {

    private CANSparkMax frontRight, frontLeft, backRight, backLeft;

    public TankDriveChassis(CANSparkMax frontRight, CANSparkMax frontLeft, CANSparkMax backRight, CANSparkMax backLeft) {
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.backRight = backRight;
        this.backLeft = backLeft;
    }

    public CANSparkMax getFrontRight() {
        return frontRight;
    }

    public CANSparkMax getFrontLeft() {
        return frontLeft;
    }

    public CANSparkMax getBackRight() { return backRight; }

    public CANSparkMax getBackLeft() { return backLeft; }
}
