package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.SpectrumButton;
import org.spectrum3847.lib.drivers.SpectrumButton.XboxButton;
import org.spectrum3847.robot.commands.Brake;
import org.spectrum3847.robot.commands.LoadShooter;
import org.spectrum3847.robot.commands.ShooterOn;
import org.spectrum3847.robot.commands.TowerOn;
import org.spectrum3847.robot.commands.VibrateController;
import org.spectrum3847.robot.commands.gear.GearArmPIDPreScore;
import org.spectrum3847.robot.commands.gear.GearArmPIDUp;
import org.spectrum3847.robot.commands.gear.GearSpearToggle;
import org.spectrum3847.robot.commands.gear.IntakeGear;
import org.spectrum3847.robot.commands.gear.ScoreGear;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // four ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	// Toggle a command when the button is pushed each time, on, off, on, off, etc
	// button.toggleWhenPressed(new ExampleCommand());

    //Use this constructor to setup up button schedulers for commands
    public OI() {
    	//Driver
    	/*HW.Driver_Gamepad.getButton(Gamepad.A_BUTTON).toggleWhenPressed(
    								new SolenoidCommand("0 & 1 Extend",
    								Robot.sol_0_1,
    								true));
    	*/	
    	//Operator
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Start).toggleWhenPressed(new ZeroGearArmCurrent());
        new SpectrumButton(HW.Operator_Gamepad, XboxButton.A).whenPressed(new IntakeGear());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.BumperLeft).whenPressed(new GearArmPIDPreScore());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.BumperRight).whenPressed(new ScoreGear());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Y).whenPressed(new GearArmPIDUp());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.X).whileHeld(new LoadShooter());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Back).toggleWhenPressed(new ShooterOn());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Back).toggleWhenPressed(new TowerOn());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.B).whenPressed(new GearSpearToggle());

    	//Driver
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.BumperLeft).whileHeld(new Brake());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.BumperRight).whenPressed(new ScoreGear());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.A).whenPressed(new IntakeGear());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.Y).whenPressed(new GearArmPIDUp());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.X).whenPressed(new GearArmPIDPreScore());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.Start).toggleWhenPressed(new ZeroGearArmCurrent());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.B).whenPressed(new GearSpearToggle());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.StickLeft).whenPressed(new VibrateController(HW.Driver_Gamepad, 1, 1));
    }
}

