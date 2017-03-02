package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.robot.commands.ManualLoadTower;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Tower extends Subsystem{

	private SpectrumSpeedControllerCAN towerMotor;
	
	public Tower(String name, SpectrumSpeedControllerCAN motor){
		super(name);
		towerMotor = motor;
	}
	
	public void set(double value){
		towerMotor.set(value);
	}
	
	public double getSpeed(){
		return towerMotor.get();
	}
	
	public double getCurrent(){
		return towerMotor.getCurrent();
	}
	
	public void disable(){
		towerMotor.disable();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualLoadTower());
	}

}
