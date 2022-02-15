package frc.robot.team8588.subsystems.drive.arcade;

import java.util.function.Supplier;

public class ArcadeDriveInputs {
    public final Supplier<Double> xStick;
    public final Supplier<Double> yStick;
    public final Supplier<Double> rTrigger;
    public final Supplier<Double> lTrigger;

    public ArcadeDriveInputs(Supplier<Double> xStick, Supplier<Double> yStick, Supplier<Double> lTrigger, Supplier<Double> rTrigger) {
        this.xStick = xStick;
        this.yStick = yStick;
        this.rTrigger = rTrigger;
        this.lTrigger = lTrigger;
    }
}
