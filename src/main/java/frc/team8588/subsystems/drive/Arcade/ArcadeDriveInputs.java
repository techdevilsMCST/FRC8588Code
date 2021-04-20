package frc.team8588.subsystems.drive.Arcade;

import java.util.function.Supplier;

public class ArcadeDriveInputs {
    public final Supplier<Double> leftStickX;
    public final Supplier<Double> leftStickY;

    public ArcadeDriveInputs(Supplier<Double> leftStickY, Supplier<Double> rightStickY) {
        this.leftStickY = leftStickY;
        this.rightStickY = rightStickY;
    }
