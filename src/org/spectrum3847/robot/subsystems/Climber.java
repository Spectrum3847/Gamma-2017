package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{

	private SpectrumSpeedControllerCAN climber_Motors;
	
	public Climber(String n, SpectrumSpeedControllerCAN motors){
		super(n);
		climber_Motors = motors;
	}
	
	public void set(double value){
		climber_Motors.set(value);
	}
	
	public double getSpeed(){
		return climber_Motors.get();
	}
	
	//returns average current
	public double getCurrent(){
		return climber_Motors.getCurrent();
	}
	
	public void disable(){
		climber_Motors.disable();
	}
	
	
	public boolean isClimbing(){
		return (Math.abs(climber_Motors.get()) > 0);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
