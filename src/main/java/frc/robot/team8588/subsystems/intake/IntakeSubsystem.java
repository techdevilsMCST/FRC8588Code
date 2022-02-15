package frc.robot.team8588.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Subsystem;

public class IntakeSubsystem implements Subsystem {
    private boolean in = false;
    private boolean out = false;
    private boolean shooter = false;

    private IntakeInputs inputs;

    public IntakeSubsystem(IntakeInputs inputs){
        this.inputs = inputs;
    }

    @Override
    public void periodic(){
        this.in = inputs.leftBumper.get();
        this.out = inputs.rightBumper.get();
        this.shooter = inputs.a.get();
    }

}
