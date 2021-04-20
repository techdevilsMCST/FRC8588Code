/****
 * Made by Tejas Mehta
 * Made on Tuesday, April 20, 2021
 * File Name: ArcadeDriveInputs
 * Package: frc.team8588.subsystems.drive.arcade*/
package frc.team8588.subsystems.drive.arcade;

import java.util.function.Supplier;

public class ArcadeDriveInputs {
    public final Supplier<Double> xStick;
    public final Supplier<Double> yStick;

    public ArcadeDriveInputs(Supplier<Double> xStick, Supplier<Double> yStick) {
        this.xStick = xStick;
        this.yStick = yStick;
    }
}
