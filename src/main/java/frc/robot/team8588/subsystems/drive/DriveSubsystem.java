/****
 * Made by Tejas Mehta
 * Made on Monday, March 29, 2021
 * File Name: DriveSubsystem
 * Package: frc.team8588.subsystems*/
package frc.robot.team8588.subsystems.drive;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Subsystem;

public interface DriveSubsystem extends Subsystem {

    void drive(double power, DriveDirection direction);

    void drive(double leftX, double leftY, double rightX, double rightY);

    void resetEncoders(); //reset all encoders for the drive chassis

    boolean moveToPosition(double location, double speed); //return true if the bot is past the location requested

    boolean moveToPosition(PIDController pid, double location, double speed); //overloaded version of the above method that implements the use of a pid control loop

    boolean strafeToPosition(double location, double speed);

    boolean strafeToPosition(PIDController pid, double location, double speed);

    void setPowers();

    void setPowersFO(AHRS ahrs);

    void setBrake();

    void setCoast();

    void halfPower();

    void fullPower();

    double returnCurrentDraw();

    double debug();

}
