package frc.robot.team8588.subsystems.intake;

import java.util.function.Supplier;

public class IntakeInputs {
    public final Supplier<Boolean> leftBumper;
    public final Supplier<Boolean> rightBumper;
    public final Supplier<Boolean> a;

    public IntakeInputs(Supplier<Boolean> leftBumper, Supplier<Boolean> rightBumper, Supplier<Boolean> a){
        this.leftBumper = leftBumper;
        this.rightBumper = rightBumper;
        this.a = a;
    }
}
