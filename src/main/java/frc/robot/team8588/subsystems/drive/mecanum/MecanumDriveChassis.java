/****
 * Made by Tejas Mehta
 * Made on Tuesday, February 01, 2022
 * File Name: MecanumDriveChassis
 * Package: frc.team8588.subsystems.drive.mecanum*/
package frc.robot.team8588.subsystems.drive.mecanum;

import com.revrobotics.CANSparkMax;

public class MecanumDriveChassis {

    private CANSparkMax frontRight, frontLeft, backRight, backLeft;

    public MecanumDriveChassis(CANSparkMax frontRight, CANSparkMax frontLeft, CANSparkMax backRight, CANSparkMax backLeft) {
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
