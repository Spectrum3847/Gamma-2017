package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.robot.commands.ManualIntake;

import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumCollector extends Subsystem{

	private SpectrumSpeedControllerCAN collectorMotor;
	private SpectrumSolenoid extendSol;
	private SpectrumSolenoid retractSol;
	private Boolean isDouble;
	
	public MecanumCollector(String n, SpectrumSpeedControllerCAN motor, SpectrumSolenoid e, SpectrumSolenoid r, Boolean dub){
		super(n);
		collectorMotor = motor;
		extendSol = e;
		retractSol = r;
		isDouble = dub;
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
	
	public void extend(){
		System.out.println("MECANUM EXTEND");
		retractSol.set(false);
		if(isDouble){
			extendSol.set(true);
		}
	}
	
	public void retract(){
		System.out.println("MECANUM RETRACT");
		if(isDouble){
			extendSol.set(false);
		}
		retractSol.set(true);
	}
	
	public boolean isExtended(){
		return extendSol.get();
	}
	
	public boolean isCollecting(){
		return (Math.abs(collectorMotor.get()) > 0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManualIntake());
		
	}

}
