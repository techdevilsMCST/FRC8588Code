/*
* THIS CLASS IS NOT BEING USED
*/

package frc.robot.team8588.subsystems.intake;

import java.util.function.Supplier;

public class IntakeInputs {
    public final Supplier<Boolean> leftBumper;
    public final Supplier<Boolean> rightBumper;
    public final Supplier<Boolean> a;
    public final Supplier<Boolean> b;

    public IntakeInputs(Supplier<Boolean> leftBumper, Supplier<Boolean> rightBumper, Supplier<Boolean> a, Supplier<Boolean> b){
        this.leftBumper = leftBumper;
        this.rightBumper = rightBumper;
        this.a = a;
        this.b = b;
    }
}
