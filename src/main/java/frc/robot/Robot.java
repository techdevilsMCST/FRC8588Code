// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.team8588.commands.DriveCommand;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.team8588.subsystems.drive.DriveDirection;
import frc.robot.team8588.subsystems.drive.DriveSubsystem;
import frc.robot.team8588.subsystems.drive.arcade.ArcadeDriveSubsystem;
import frc.robot.team8588.subsystems.intake.IntakeSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * methods corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot
{
    private DriveCommand driveCommand;
    private RobotContainer robotContainer;

    private DriveSubsystem subsystem;

    private IntakeSubsystem subsystemIntake;

    //Variables for auton:
    private Timer timer;
    private AHRS ahrs;
    private PIDController turnPID;
    private PIDController drivePID;

    private int currentStep; //what step the auton is currently on

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit()
    {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();
        subsystem = robotContainer.getDriveSubsystem();
        subsystemIntake = robotContainer.getIntake();

        timer = new Timer();
        turnPID = new PIDController(0.015, 0.001, 0.001);
        drivePID = new PIDController(0.01, 0, 0);

        try {
            ahrs = new AHRS(SPI.Port.kMXP); //the kMXP port is the expansion port for the roborio
            ahrs.enableLogging(true);
            //ahrs.calibrate(); //takes approximately 15 seconds to finish (leave commented out for now)
        }
        catch (RuntimeException ex) {
            DriverStation.reportError("Error creating navx sensor object! " + ex.getMessage(), true);
        }
    }

    /**
     * This method is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before LiveWindow and
     * SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic()
    {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();

        /* Display 6-axis Processed Angle Data                                      */
        SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
        SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
        SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
        SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
        SmartDashboard.putNumber(   "IMU_Roll",             ahrs.getRoll());

        /* Display tilt-corrected, Magnetometer-based heading (requires             */
        /* magnetometer calibration to be useful)                                   */

        SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());

        /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
        SmartDashboard.putNumber(   "IMU_FusedHeading",     ahrs.getFusedHeading());

        /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
        /* path for upgrading from the Kit-of-Parts gyro to the navx MXP            */

        SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
        SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());

        /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */

        SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
        SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
        SmartDashboard.putBoolean(  "IMU_IsMoving",         ahrs.isMoving());
        SmartDashboard.putBoolean(  "IMU_IsRotating",       ahrs.isRotating());

        /* Display estimates of velocity/displacement.  Note that these values are  */
        /* not expected to be accurate enough for estimating robot position on a    */
        /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
        /* of these errors due to single (velocity) integration and especially      */
        /* double (displacement) integration.                                       */

        // Also known as "So we were right to not use that for closed-loop control"

        SmartDashboard.putNumber(   "Velocity_X",           ahrs.getVelocityX());
        SmartDashboard.putNumber(   "Velocity_Y",           ahrs.getVelocityY());
        SmartDashboard.putNumber(   "Displacement_X",       ahrs.getDisplacementX());
        SmartDashboard.putNumber(   "Displacement_Y",       ahrs.getDisplacementY());

        /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
        /* NOTE:  These values are not normally necessary, but are made available   */
        /* for advanced users.  Before using this data, please consider whether     */
        /* the processed data (see above) will suit your needs.                     */

        /*
        SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
        SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
        SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
        SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
        SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
        SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
        SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
        SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
        SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
        */

        SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC()); // temp always nice to keep around :)

        /* Omnimount Yaw Axis Information                                           */
        /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */

        /*
        // Not sure if we need this either right now, roboRIO + navX seem to be mounted correctly
        AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
        SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
        SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
         */

        /* Sensor Board Information                                                 */
        SmartDashboard.putString(   "FirmwareVersion",      ahrs.getFirmwareVersion());

        /* Quaternion Data                                                          */
        /* Quaternions are fascinating, and are the most compact representation of  */
        /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
        /* from the Quaternions.  If interested in motion processing, knowledge of  */
        /* Quaternions is highly recommended.                                       */

        /*
        // Quaternion data is currently commented out as it's unneeded (cluttering up screen)
        SmartDashboard.putNumber(   "QuaternionW",          ahrs.getQuaternionW());
        SmartDashboard.putNumber(   "QuaternionX",          ahrs.getQuaternionX());
        SmartDashboard.putNumber(   "QuaternionY",          ahrs.getQuaternionY());
        SmartDashboard.putNumber(   "QuaternionZ",          ahrs.getQuaternionZ());
         */

        /* Sensor Data Timestamp */
        SmartDashboard.putNumber(   "SensorTimestamp",		ahrs.getLastSensorTimestamp());

        /* Connectivity Debugging Support                                           */
        SmartDashboard.putNumber(   "IMU_Byte_Count",       ahrs.getByteCount());
        SmartDashboard.putNumber(   "IMU_Update_Count",     ahrs.getUpdateCount());
        SmartDashboard.putNumber("Encoder value: ", subsystem.debug());
        SmartDashboard.putNumber("Current auton step: ", currentStep);
    }

    /** This method is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
    @Override
    public void autonomousInit()
    {
//        driveCommand = robotContainer.getDriveCommand();
//
//        // schedule the autonomous command (example)
//        if (driveCommand != null)
//        {
//            driveCommand.schedule();
//        }

        timer.reset();
        timer.start();

        currentStep = -1;
    }

    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        //double turnAmount = turnPID.calculate(ahrs.getRotation2d().getDegrees(), 0);

        //subsystem.drive(turnAmount, DriveDirection.LEFT);
        double turnAmount = 0;
        double currentAHRSRotation =ahrs.getRotation2d().getDegrees();
        /*
        switch (currentStep)
        {
            case -1:
                subsystem.resetEncoders();
                currentStep++;
                break;

            case 0:
                if(subsystem.moveToPosition(20, 0.5))   {currentStep++; subsystem.resetEncoders();}
                break;

            case 1:
                //turn 180 degrees
                turnAmount = -turnPID.calculate(currentAHRSRotation, 180);
                subsystem.drive(turnAmount, DriveDirection.TURN_RIGHT);

                if(turnPID.getPositionError() < 5) {currentStep++; ahrs.reset();}
                break;

            case 2:
                if(subsystem.moveToPosition(20, 0.5))  {currentStep++; subsystem.resetEncoders(); ahrs.reset();}
                break;

            case 3:
                //turn 90 degrees
                turnAmount = -turnPID.calculate(currentAHRSRotation, 180);
                subsystem.drive(turnAmount, DriveDirection.TURN_RIGHT);

                if(turnPID.getPositionError() < 5) {currentStep++; ahrs.reset();}
                break;
        }
        */

        // 20 units forward
        // turn 180 degrees
        // 20 units back

        switch (currentStep){
            case -1:
                subsystem.resetEncoders();
                currentStep++; //moves to next case
                break;

            case 0:
                if (subsystem.moveToPosition(-60,0.5)) {
                    //move 20 units forward

                    currentStep++;
                    subsystem.resetEncoders();
                }
                break;

            case 1:
                subsystem.moveToPosition(0,0);
                break;
        }


        /*
        switch(currentStep){
            // what step are we on???

            case -1:
                subsystem.resetEncoders();
                currentStep++;
                // move to next step / case
                break;
            case 0:
                // 20 units forward
                if (subsystem.moveToPosition(drivePID, 20, 0.5)) {
                    // does the movement and returns whether or not the movement was done.
                    currentStep++;
                    // make sure to go to the next step
                    subsystem.resetEncoders();
                    // reset the encoders for the next move.
                }
                break;
            case 1:
                // turn 180 degrees
                turnAmount = turnPID.calculate(currentAHRSRotation, 180);
                // determine the amount needed to turn by throwing it through the PID.

                subsystem.drive(turnAmount, DriveDirection.TURN_RIGHT);
                // actually does the driving.  this repeats until we get to . . .

                if (turnPID.getPositionError() < 5) {
                    // we have reached basically where we need to be.
                    currentStep++;
                    // go to the next step / case
                    ahrs.reset();
                }
                break;
            case 2:
                // 20 units back
                if (subsystem.moveToPosition(drivePID, -20, 0.5)) {
                    currentStep++;
                    // goes to next step once done (if there was one)
                    subsystem.resetEncoders();
                    // reset the encoders.
                }
                break;
            case 3:
                // strafe left
                if (subsystem.strafeToPosition(drivePID, -20, 0.5)) {
                    currentStep++;
                    subsystem.resetEncoders();
                }
                break;
            case 4:
                // strafe right
                if (subsystem.strafeToPosition(drivePID, 20, 0.5)) {
                    currentStep++;
                    subsystem.resetEncoders();
                }
                break;
        }
        */
    }

    @Override
    public void teleopInit()
    {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        driveCommand = robotContainer.getDriveCommand();
        if (driveCommand != null)
        {
            driveCommand.schedule();
        }
    }

    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        driveCommand.execute(ahrs);
        subsystemIntake.periodic();
    }

    @Override
    public void testInit()
    {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
