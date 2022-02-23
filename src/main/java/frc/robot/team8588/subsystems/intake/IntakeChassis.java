package frc.robot.team8588.subsystems.intake;

import com.revrobotics.CANSparkMax;

public class IntakeChassis {
    private CANSparkMax left;
    private CANSparkMax right;

    public IntakeChassis(CANSparkMax left, CANSparkMax right) {
        this.left = left;
        this.right = right;
    }

    public CANSparkMax getLeft() {
        return left;
    }

    public CANSparkMax getRight() {
        return right;
    }
}
