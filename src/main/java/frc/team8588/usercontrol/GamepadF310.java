/****
 * Made by Tejas Mehta
 * Made on Monday, March 22, 2021
 * File Name: Gamepad
 * Package: frc.team8588.controller*/
package frc.team8588.usercontrol;

import edu.wpi.first.wpilibj.Joystick;

public class GamepadF310 {

    /**
     * Gamepad mappings as the F310 controller isn't officially in the WPI repo.
     * Each Number corresponds to a specific "axis" or "button" in the joystick class.
     * The variable names should be pretty self-explanatory as to what each number
     * represents.
     */
    private static final int GAMEPAD_LEFT_X = 1; //TODO replace w/ actual
    private static final int GAMEPAD_LEFT_Y = 2; //TODO replace w/ actual
    private static final int GAMEPAD_RIGHT_X = 3; //TODO replace w/ actual
    private static final int GAMEPAD_RIGHT_Y = 4; //TODO replace w/ actual
    private static final int GAMEPAD_DPAD_UP = 5; //TODO replace w/ actual
    private static final int GAMEPAD_DPAD_RIGHT = 6; //TODO replace w/ actual
    private static final int GAMEPAD_DPAD_DOWN = 7; //TODO replace w/ actual
    private static final int GAMEPAD_DPAD_LEFT = 8; //TODO replace w/ actual

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

    public boolean dpadUp() {
        return joystick.getRawButton(GAMEPAD_DPAD_UP);
    }

    public boolean dpadRight() {
        return joystick.getRawButton(GAMEPAD_DPAD_RIGHT);
    }

    public boolean dpadDown() {
        return joystick.getRawButton(GAMEPAD_DPAD_DOWN);
    }

    public boolean dpadLeft() {
        return joystick.getRawButton(GAMEPAD_DPAD_LEFT);
    }
}
