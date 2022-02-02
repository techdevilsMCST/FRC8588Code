/****
 * Made by Tejas Mehta
 * Made on Monday, April 12, 2021
 * File Name: TankDriveInputs
 * Package: frc.team8588.subsystems.drive.tank*/
package frc.robot.team8588.subsystems.drive.tank;

import java.util.function.Supplier;

public class TankDriveInputs {
    public final Supplier<Double> leftStickY;
    public final Supplier<Double> rightStickY;

    public TankDriveInputs(Supplier<Double> leftStickY, Supplier<Double> rightStickY) {
        this.leftStickY = leftStickY;
        this.rightStickY = rightStickY;
    }
}
