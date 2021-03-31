/*
Name: Abigail Prowse
Program: ArcadeDriveChassis
This is the starting of the ArcadeDriveChassis.  Does not make complete sense yet and definitely needs work
Check out ArcadeDriveSubsystem and other subsystems.
Date: 3/29/2021
 */

package frc.team8588.subsystems.drive.arcade;

import edu.wpi.first.wpilibj.PWMSparkMax;

public class ArcadeDriveChassis {

    private PWMSparkMax left, right;

    public ArcadeDriveChassis(PWMSparkMax left, PWMSparkMax right) {
        this.left = left;
        this.right = right;
    }

    public PWMSparkMax getLeft() {
        return left;
    }

    public PWMSparkMax getRight() {
        return right;
    }
}
