/****
 * Made by Tejas Mehta
 * Made on Tuesday, February 01, 2022
 * File Name: MecanumDriveInputs
 * Package: frc.team8588.subsystems.drive.mecanum*/
package frc.robot.team8588.subsystems.drive.mecanum;

import java.util.function.Supplier;

public class MecanumDriveInputs {
    public final Supplier<Double> leftStickY;
    public final Supplier<Double> leftStickX;
    public final Supplier<Double> rightStickX;
    public final Supplier<Double> leftTrig;
    public final Supplier<Double> rightTrig;
    public final Supplier<Boolean> xButton;


    public MecanumDriveInputs(Supplier<Double> leftStickY, Supplier<Double> leftStickX, Supplier<Double> rightStickX, Supplier<Double> leftTrig, Supplier<Double> rightTrig, Supplier<Boolean> xButton) {
        this.leftStickY = leftStickY;
        this.leftStickX = leftStickX;
        this.rightStickX = rightStickX;
        this.leftTrig = leftTrig;
        this.rightTrig = rightTrig;
        this.xButton = xButton;
    }
}
