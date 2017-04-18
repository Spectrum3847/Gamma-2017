package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.SpectrumAxisButton;
import org.spectrum3847.lib.drivers.SpectrumAxisButton.XboxAxis;
import org.spectrum3847.lib.drivers.SpectrumButton;
import org.spectrum3847.lib.drivers.SpectrumButton.XboxButton;
import org.spectrum3847.lib.drivers.SpectrumPOVButton;
import org.spectrum3847.lib.drivers.SpectrumPOVButton.XboxPOV;
import org.spectrum3847.robot.commands.AimingLightOn;
import org.spectrum3847.robot.commands.Brake;
import org.spectrum3847.robot.commands.SwitchCamera;
import org.spectrum3847.robot.commands.DriveAtVoltage;
import org.spectrum3847.robot.commands.IntakeOn;
import org.spectrum3847.robot.commands.VibrateController;
import org.spectrum3847.robot.commands.auto.DriveDistance;
import org.spectrum3847.robot.commands.auto.InPlaceTurn;
import org.spectrum3847.robot.commands.gear.AutoScoreGearBackPack;
import org.spectrum3847.robot.commands.gear.GearArmPIDPreScore;
import org.spectrum3847.robot.commands.gear.GearArmPIDUp;
import org.spectrum3847.robot.commands.gear.GearSpearToggle;
import org.spectrum3847.robot.commands.gear.IntakeGear;
import org.spectrum3847.robot.commands.gear.ScoreGear;
import org.spectrum3847.robot.commands.gear.ScoreGearBackPack;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;
import org.spectrum3847.robot.commands.leds.BlinkBlue;
import org.spectrum3847.robot.commands.leds.BlinkPurple;
import org.spectrum3847.robot.commands.leds.Blue;
import org.spectrum3847.robot.commands.leds.Purple;
import org.spectrum3847.robot.commands.shooter.DecreaseShooterSpeed;
import org.spectrum3847.robot.commands.shooter.FireBalls;
import org.spectrum3847.robot.commands.shooter.IncreaseShooterSpeed;
import org.spectrum3847.robot.commands.shooter.LoadShooter;
import org.spectrum3847.robot.commands.shooter.ReverseBeltBed;
import org.spectrum3847.robot.commands.shooter.ShooterOn;
import org.spectrum3847.robot.commands.shooter.TowerOn;
import org.spectrum3847.robot.subsystems.Cameras;

import edu.wpi.first.wpilibj.command.Command;


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
    	Command switchCamsCommand = new SwitchCamera(Robot.cams);
    	Command aimingLight = new AimingLightOn();
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.BumperRight).toggleWhenPressed(new ZeroGearArmCurrent());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.BumperLeft).whenPressed(new GearArmPIDPreScore());
        new SpectrumButton(HW.Operator_Gamepad, XboxButton.A).whenPressed(new IntakeGear());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.B).toggleWhenPressed(new ShooterOn());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.B).toggleWhenPressed(new TowerOn());
    	//new SpectrumButton(HW.Operator_Gamepad, XboxButton.X).whileHeld(new BlinkBlue(.5));
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.X).toggleWhenPressed(switchCamsCommand);
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Y).whenPressed(new GearArmPIDUp());
    	new SpectrumAxisButton(HW.Operator_Gamepad, XboxAxis.LeftTrigger, .07, true).whileHeld(new ShooterOn());
    	new SpectrumAxisButton(HW.Operator_Gamepad, XboxAxis.LeftTrigger, .07, true).whileHeld(new TowerOn());
    	new SpectrumAxisButton(HW.Operator_Gamepad, XboxAxis.LeftTrigger, .07, true).whileHeld(new IntakeOn());
    	new SpectrumAxisButton(HW.Operator_Gamepad, XboxAxis.LeftTrigger, .95, true).whileHeld(new FireBalls());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Start).toggleWhenPressed(new ReverseBeltBed());
    	new SpectrumButton(HW.Operator_Gamepad, XboxButton.Back).whenPressed(new LoadShooter());
    	new SpectrumPOVButton(HW.Operator_Gamepad, XboxPOV.Right).toggleWhenPressed(aimingLight);
    	new SpectrumPOVButton(HW.Operator_Gamepad, XboxPOV.Left).toggleWhenPressed(aimingLight);
    	new SpectrumPOVButton(HW.Operator_Gamepad, XboxPOV.Up).whileHeld(new AimingLightOn());
    	new SpectrumPOVButton(HW.Operator_Gamepad, XboxPOV.Down).toggleWhenPressed(new BlinkPurple(.5));
    
    	//Driver
    	//new SpectrumButton(HW.Driver_Gamepad, XboxButton.BumperLeft).whileHeld(new Brake());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.BumperRight).whenPressed(new ScoreGear());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.BumperLeft).toggleWhenPressed(new ScoreGearBackPack());
    	new SpectrumAxisButton(HW.Driver_Gamepad, XboxAxis.LeftTrigger, .5, true).whileHeld(new AutoScoreGearBackPack());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.A).whenPressed(new IntakeGear());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.Y).whenPressed(new GearArmPIDUp());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.X).whenPressed(new GearArmPIDPreScore());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.Start).toggleWhenPressed(new ZeroGearArmCurrent());
    	new SpectrumButton(HW.Driver_Gamepad, XboxButton.B).toggleWhenPressed(switchCamsCommand);
    	new SpectrumPOVButton(HW.Driver_Gamepad, XboxPOV.Up).toggleWhenPressed(switchCamsCommand);
    	new SpectrumPOVButton(HW.Driver_Gamepad, XboxPOV.Right).toggleWhenPressed(aimingLight);
    	new SpectrumPOVButton(HW.Driver_Gamepad, XboxPOV.Left).toggleWhenPressed(aimingLight);
    	

    }
}

