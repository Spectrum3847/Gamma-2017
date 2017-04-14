package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIntake extends Subsystem{

	SpectrumSpeedControllerCAN motor;
	
	public BallIntake(SpectrumSpeedControllerCAN mot) {
		// TODO Auto-generated constructor stub
		motor = mot;
	}
	
	public void setIntake(double speed){
		motor.set(speed);
	}
	
	public CANTalon getTalon(){
		return motor.getTalon();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
