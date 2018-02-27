package org.spectrum3847.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.commands.leds.Purple;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;

public class LEDs extends Subsystem {
	
	I2C i2c;
	byte[] toSend = new byte[1];
	
	public double timeToDisplay = 0;
	public double startTime = Timer.getFPGATimestamp();
	public double blinkTime;
	public double spd = 1;
	public final String defaultColor = "purple";
	public String tempColor = "purple";
	public boolean isBlinking = false;
	
	public LEDs() {
		super();
		i2c = new I2C(I2C.Port.kOnboard, 5);
	}

	protected void initDefaultCommand() {

	}
	public void yellow(){
		toSend[0] = 76;
		i2c.transaction(toSend, 1, null, 0);
	}
	
	public void green(){
		toSend[0] = 75;
		i2c.transaction(toSend, 1, null, 0);
	}
	
	public void blue(){
		toSend[0] = 74;
		i2c.transaction(toSend, 1, null, 0);
	}	
	public void purple(){
		toSend[0] = 72;
		i2c.transaction(toSend, 1, null, 0);
	}	
	
	public void red(){
		toSend[0] = 73;
		i2c.transaction(toSend, 1, null, 0);
	}	
	
	public void off(){
		toSend[0] = 71;
		i2c.transaction(toSend, 1, null, 0);
	}
	
	public void setColor(String c) { //put in leds
		startTime = Timer.getFPGATimestamp();
		timeToDisplay = 0;
		tempColor = c;
		printGeneralInfo("Color currently is " + tempColor + " ");
		
	}
	
	public void setColor(String c, double t) {
		startTime = Timer.getFPGATimestamp();
		timeToDisplay = t;
		tempColor = c;
		printGeneralInfo("Color currently is " + tempColor + " ");
	}
	
	public void blinkOn() {
		
		isBlinking = true;
		printGeneralInfo("Blinking is on");
	}
	
	public void blinkOff() {
		
		isBlinking = false;
		printGeneralInfo("Blinking is off");
	}
	
	public void printGeneralInfo(String msg){
    	Debugger.println(msg, "LIGHTS", Debugger.info3);
    }
}
