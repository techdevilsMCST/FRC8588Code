/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: TankDriveChassis
 * Package: frc.team8588.subsystems.drive.tank*/
package frc.team8588.subsystems.drive.tank;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.PWMSparkMax;

public class TankDriveChassis {

    private CANSparkMax left, right;

    public TankDriveChassis(CANSparkMax left, CANSparkMax right) {
        this.left = left;
        this.right = right;
//        new CANSparkMax()
    }

    public CANSparkMax getLeft() {
        return left;
    }

    public CANSparkMax getRight() {
        return right;
    }
}
