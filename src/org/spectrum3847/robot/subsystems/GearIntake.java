package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.gear.GearArmDrive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntake extends Subsystem {

	private SpectrumSpeedControllerCAN intake_motor;
	private SpectrumSpeedControllerCAN arm_motor;
	private boolean currentLimitFlag;
	public static DigitalInput sensor;
	private static double upPos;
	
	
	public GearIntake (SpectrumSpeedControllerCAN intake, SpectrumSpeedControllerCAN arm, DigitalInput s){
		intake_motor = intake;
		arm_motor = arm;
		sensor = s;
		getArmTalon().setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Absolute);
		getArmTalon().configPeakOutputVoltage(+6f, -6f);
		getArmTalon().setCloseLoopRampRate(10000);
		getIntakeTalon().setVoltageRampRate(10000);
		getArmTalon().changeControlMode(TalonControlMode.Position);
	}
	
	 public void initDefaultCommand() {
			//setDefaultCommand(new GearArmDrive());
	 }
	

	public void setArmMotor(double speed){
		if(Math.abs(speed) >= SmartDashboard.getNumber("Gear Arm Deadband",.06) && !this.isCurrentLimited())
			arm_motor.set(speed);
		else
			arm_motor.set(0);
				
	}
	
	public SpectrumSpeedControllerCAN getArmSpeedController(){
		return arm_motor;
	}
	public SpectrumSpeedControllerCAN getIntakeSpeedController(){
		return intake_motor;
	}
	public CANTalon getArmTalon(){
		return arm_motor.getTalon();
	}
	public CANTalon getIntakeTalon(){
		return intake_motor.getTalon();
	}
	
	
	
	private boolean isCurrentLimited(){
		 if(this.getArmCurrent() >= SmartDashboard.getNumber("Gear Arm Current Limit",40)){
			 currentLimitFlag = true;
		 }
		 if(this.getArmCurrent() < SmartDashboard.getNumber("Gear Arm Current Limit Low Bound",30)){
			 currentLimitFlag = false;
		 }
		 return currentLimitFlag;
	}
	
	public void setIntake(double speed){
		intake_motor.set(speed);
	}
	public boolean getSensorOutput(){
		return sensor.get();
	}
	
	public void setUpPos(double pos){
		upPos = pos;
	}
	
	public double getUpPos(){
		return upPos;
	}

	public double getEncPosition(){
		return arm_motor.getTalon().getPosition();
	}
	
	public double getArmCurrent(){
		return Math.abs(arm_motor.getSignedCurrent());
	}
	
	public void printDebug(String msg){
		Debugger.println(msg, Robot.intake, Debugger.debug2);
	}

}
