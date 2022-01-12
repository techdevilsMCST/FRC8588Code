/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: TankDriveChassis
 * Package: frc.team8588.subsystems.drive.tank*/
package frc.team8588.subsystems.drive.tank;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.PWMSparkMax;

public class TankDriveChassis {

    private CANSparkMax frontRight, frontLeft, backRight, backLeft;

    public TankDriveChassis(CANSparkMax frontRight, CANSparkMax frontLeft, CANSparkMax backRight, CANSparkMax backLeft) {
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.backRight = backRight;
        this.backLeft = backLeft;
//        new CANSparkMax()
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
