package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class BeltBed extends Subsystem{

	private SpectrumSpeedControllerCAN beltBedMotor;
	
	public BeltBed(String name, SpectrumSpeedControllerCAN motor){
		super(name);
		beltBedMotor = motor;
	}
	
	public void set(double value){
		beltBedMotor.set(value);
	}
	
	public double getSpeed(){
		return beltBedMotor.get();
	}
	
	public double getCurrent(){
		return beltBedMotor.getCurrent();
	}
	
	public void disable(){
		beltBedMotor.disable();
	}
	
	public CANTalon getTalon(){
		return beltBedMotor.getTalon();	
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
