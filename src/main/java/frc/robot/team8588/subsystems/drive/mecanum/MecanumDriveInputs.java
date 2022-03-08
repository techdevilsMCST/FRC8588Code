/****
 * Made by Tejas Mehta
 * Made on Tuesday, February 01, 2022
 * File Name: MecanumDriveInputs
 * Package: frc.team8588.subsystems.drive.mecanum*/
package frc.robot.team8588.subsystems.drive.mecanum;

// I didn't finish this - Val

import java.util.function.Supplier;

public class MecanumDriveInputs {
    public final Supplier<Double> leftStickY;
    public final Supplier<Double> leftStickX;
    public final Supplier<Double> rightStickX;
    public final Supplier<Double> powerMultiplier;


    public MecanumDriveInputs(Supplier<Double> leftStickY, Supplier<Double> leftStickX, Supplier<Double> rightStickX) {
        this(leftStickY, leftStickX, rightStickX, () -> 1.0);
    }

    public MecanumDriveInputs(Supplier<Double> leftStickY, Supplier<Double> leftStickX, Supplier<Double> rightStickX, Supplier<Double> powerMultiplier) {
        this.leftStickY = leftStickY;
        this.leftStickX = leftStickX;
        this.rightStickX = rightStickX;
        this.powerMultiplier = powerMultiplier;
    }

}
