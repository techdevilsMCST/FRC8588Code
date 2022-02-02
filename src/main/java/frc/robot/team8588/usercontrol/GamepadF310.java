/****
 * Made by Tejas Mehta
 * Made on Monday, March 22, 2021
 * File Name: Gamepad
 * Package: frc.team8588.controller*/
package frc.robot.team8588.usercontrol;

import edu.wpi.first.wpilibj.Joystick;

public class GamepadF310 {

    /**
     * Gamepad mappings as the F310 controller isn't officially in the WPI repo.
     * Each Number corresponds to a specific "axis" or "button" in the joystick class.
     * The variable names should be pretty self-explanatory as to what each number
     * represents.
     */
    private static final int GAMEPAD_LEFT_X = 0;
    private static final int GAMEPAD_LEFT_Y = 1;
    private static final int GAMEPAD_RIGHT_X = 4;
    private static final int GAMEPAD_RIGHT_Y = 5;

    private static final int GAMEPAD_LEFT_TRIGGER = 2;
    private static final int GAMEPAD_RIGHT_TRIGGER = 3;

    private static final int GAMEPAD_A = 1;
    private static final int GAMEPAD_B = 2;
    private static final int GAMEPAD_X = 3;
    private static final int GAMEPAD_Y = 4;

    private static final int GAMEPAD_LEFT_BUMPER = 5;
    private static final int GAMEPAD_RIGHT_BUMPER = 6;


    private Joystick joystick;
    public GamepadF310(int port) {
        joystick = new Joystick(port);
    }

    public double getLeftX() {
        return joystick.getRawAxis(GAMEPAD_LEFT_X);
    }

    public double getLeftY() {
        return joystick.getRawAxis(GAMEPAD_LEFT_Y);
    }

    public double getRightX() {
        return joystick.getRawAxis(GAMEPAD_RIGHT_X);
    }

    public double getRightY() {
        return joystick.getRawAxis(GAMEPAD_RIGHT_Y);
    }

    public double getRightTrigger() {
        return joystick.getRawAxis(GAMEPAD_RIGHT_TRIGGER);
    }

    public double getLeftTrigger() {
        return joystick.getRawAxis(GAMEPAD_LEFT_TRIGGER);
    }

    public boolean getA() {
        return joystick.getRawButton(GAMEPAD_A);
    }

    public boolean getB() {
        return joystick.getRawButton(GAMEPAD_B);
    }

    public boolean getX() {
        return joystick.getRawButton(GAMEPAD_X);
    }

    public boolean getY() {
        return joystick.getRawButton(GAMEPAD_Y);
    }

    public boolean getLeftBumper() {
        return joystick.getRawButton(GAMEPAD_LEFT_BUMPER);
    }

    public boolean getRightBumper() {
        return joystick.getRawButton(GAMEPAD_RIGHT_BUMPER);
    }

}
