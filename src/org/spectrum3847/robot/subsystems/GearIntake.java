package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.robot.HW;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntake extends Subsystem {

	private SpectrumSpeedControllerCAN intake_motor;
	private SpectrumSpeedControllerCAN arm_motor;
	private boolean currentLimitFlag;
	
	public GearIntake (SpectrumSpeedControllerCAN intake, SpectrumSpeedControllerCAN arm){
		intake_motor = intake;
		arm_motor = arm;
		
	}
	
	public void setArmMotor(double speed){
		if(Math.abs(speed) >= SmartDashboard.getNumber("Gear Arm Deadband") && !this.isCurrentLimited())
			arm_motor.set(speed);
		else
			arm_motor.set(0);
				
	}
	
	private boolean isCurrentLimited(){
		 if(this.getArmCurrent() >= SmartDashboard.getNumber("Gear Arm Current Limit")){
			 currentLimitFlag = true;
		 }
		 if(this.getArmCurrent() < SmartDashboard.getNumber("Gear Arm Current Limit Low Bound")){
			 currentLimitFlag = false;
		 }
		 return currentLimitFlag;
	}
	
	public void setIntake(double speed){
		intake_motor.set(speed);
	}
	
	public double getArmCurrent(){
		return Math.abs(arm_motor.getSignedCurrent());
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
