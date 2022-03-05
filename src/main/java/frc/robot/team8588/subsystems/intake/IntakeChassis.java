package frc.robot.team8588.subsystems.intake;

import com.revrobotics.CANSparkMax;

public class IntakeChassis {
    private CANSparkMax left;
    private CANSparkMax right;
    private CANSparkMax indexer;
    private CANSparkMax shooter;

    public IntakeChassis(CANSparkMax left, CANSparkMax right, CANSparkMax indexer, CANSparkMax shooter) {
        this.left = left;
        this.right = right;
        this.indexer = indexer;
        this.shooter = shooter;
    }

    public CANSparkMax getLeft() {
        return left;
    }

    public CANSparkMax getRight() {
        return right;
    }

    public CANSparkMax getIndexer() { return indexer; }

    public CANSparkMax getShooter() { return shooter; }
}
