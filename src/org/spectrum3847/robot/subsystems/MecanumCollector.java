package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumCollector extends Subsystem{

	private SpectrumSpeedControllerCAN collectorMotor;
	
	public MecanumCollector(String n, SpectrumSpeedControllerCAN motor){
		super(n);
		collectorMotor = motor;
	}
	
	public void set(double value){
		collectorMotor.set(value);
	}
	
	public double getSpeed(){
		return collectorMotor.get();
	}
	
	public double getCurrent(){
		return collectorMotor.getCurrent();
	}
	
	public void disable(){
		collectorMotor.disable();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
