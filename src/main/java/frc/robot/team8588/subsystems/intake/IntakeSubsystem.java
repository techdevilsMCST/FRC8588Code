package frc.robot.team8588.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class IntakeSubsystem implements Subsystem {
    private boolean in = false;
    private boolean out = false;
    private boolean shooter = false;

    private IntakeInputs inputs;
    private IntakeChassis chassis;

    public IntakeSubsystem(IntakeChassis chassis, IntakeInputs inputs){
        this.chassis = chassis;
        this.inputs = inputs;
    }

    @Override
    public void periodic(){
        this.in = inputs.leftBumper.get();
        this.out = inputs.rightBumper.get();
        this.shooter = inputs.a.get();

        double intakePower = 0.6;
        if (this.in) {
            chassis.getLeft().set(intakePower);
            chassis.getRight().set(-intakePower);
        } else if (this.out) {
            chassis.getLeft().set(-intakePower);
            chassis.getRight().set(intakePower);
        } else if (this.shooter) {
            chassis.getLeft().set(0);
            chassis.getRight().set(0);
        }
    }

}
