/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: TankDriveChassis
 * Package: frc.team8588.subsystems.drive.tank*/
package frc.team8588.subsystems.drive.tank;

import edu.wpi.first.wpilibj.PWMSparkMax;

public class TankDriveChassis {

    private PWMSparkMax left, right;

    public TankDriveChassis(PWMSparkMax left, PWMSparkMax right) {
        this.left = left;
        this.right = right;
    }

    public PWMSparkMax getLeft() {
        return left;
    }

    public PWMSparkMax getRight() {
        return right;
    }
}
