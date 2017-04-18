package org.spectrum3847.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.spectrum3847.robot.commands.leds.Purple;

import edu.wpi.first.wpilibj.I2C;

public class LEDs extends Subsystem {
	
	I2C i2c;
	byte[] toSend = new byte[1];

	public LEDs() {
		super();
		i2c = new I2C(I2C.Port.kOnboard, 5);
	}

	protected void initDefaultCommand() {
		this.setDefaultCommand(new Purple());

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

}
