package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;

public class FuelCollector extends Subsystem{

	private SpectrumSpeedController collectorMotor;
	
	public FuelCollector(String n, SpectrumSpeedController motor){
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
