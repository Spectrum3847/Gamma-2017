package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.Gamepad;
import org.spectrum3847.lib.drivers.SpectrumButton;
import org.spectrum3847.lib.drivers.SpectrumButton.XboxButton;
import org.spectrum3847.robot.commands.MecanumCollect;
import org.spectrum3847.robot.commands.MecanumDown;
import org.spectrum3847.robot.commands.Brake;
import org.spectrum3847.robot.commands.Climb;
import org.spectrum3847.robot.commands.Fire;
import org.spectrum3847.robot.commands.IntakeOn;
import org.spectrum3847.robot.commands.LoadShooter;
import org.spectrum3847.robot.commands.LoadTower;
import org.spectrum3847.robot.commands.MecanumUp;
import org.spectrum3847.robot.commands.ShooterOn;
import org.spectrum3847.robot.commands.SolenoidCommand;
import org.spectrum3847.robot.commands.gear.GearArmPIDDown;
import org.spectrum3847.robot.commands.gear.GearArmPIDScore;
import org.spectrum3847.robot.commands.gear.GearArmPIDUp;
import org.spectrum3847.robot.commands.gear.GearIntakeOn;
import org.spectrum3847.robot.commands.gear.IntakeGear;import org.spectrum3847.robot.commands.gear.ScoreGear;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.X).toggleWhenPressed(new MecanumCollect());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.B).whileHeld(new Climb()); 	
    	//new SpectrumButton(HW.Operator_Gamepad, XboxButton.A).toggleWhenPressed(new ShooterOn());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Y).whileHeld(new LoadShooter());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Y).whileHeld(new LoadTower());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Y).whileHeld(new ShooterOn());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Start).toggleWhenPressed(new ZeroGearArmCurrent());
       	new SpectrumButton(HW.Operator_Gamepad, XboxButton.DDown).whenPressed(new IntakeGear());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.DRight).whileHeld(new GearIntakeOn(false));
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.DLeft).whileHeld(new GearIntakeOn(true));
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.BumperLeft).whenPressed(new GearArmPIDScore());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.BumperRight).whenPressed(new ScoreGear());

    	//Driver
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.BumperLeft).whenPressed(new GearArmPIDScore());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.BumperRight).whenPressed(new ScoreGear());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.DDown).whenPressed(new IntakeGear());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.DUp).whenPressed(new GearArmPIDUp());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.DRight).whileHeld(new GearIntakeOn(false));
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.DLeft).whileHeld(new GearIntakeOn(true));
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.B).whileHeld(new Climb());
    	
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.X).toggleWhenPressed(new Brake());
    	//new SpectrumButton(HW.Driver_Gamepad, XboxButton.Y).toggleWhenPressed(new GearArmPIDUp());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.Start).toggleWhenPressed(new ZeroGearArmCurrent());
    }
}

