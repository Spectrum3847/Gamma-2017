package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterWheel extends Subsystem{

	public String m_name;
	
	public SpectrumSpeedControllerCAN m_motor;
	
	private double speedAdjustment = 0;
	private double speed = 0;
	

	public ShooterWheel(String name, SpectrumSpeedControllerCAN motor){
		this.m_motor = motor;
		m_name = name;
		this.getTalon().configEncoderCodesPerRev(3);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	
	public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile){
		m_motor.getTalon().setPID(p, i, d, f, izone, closeLoopRampRate, profile);
		m_motor.getTalon().configMaxOutputVoltage(12);
	}
	
	public void set(double speed){
		this.speed = speed;
		Debugger.println(m_name + " ShooteWheel Setpoint: " + speed + " with Adjustment of: " + this.speedAdjustment, Robot.shooter, Debugger.debug2);
		m_motor.getTalon().set(speed + this.speedAdjustment);
	}
	
	public void updateSpeed(){
		set(this.speed);
	}
	
	public void adjustSpeed(double adjustment){
		this.speedAdjustment += adjustment;
	}
	
	public double getAdjustment(){
		return this.speedAdjustment;
	}
	
	public void clearAdjustment(){
		this.speedAdjustment = 0;
	}
	
	public double getSpeed(){
		return getTalon().getSpeed();
	}
	
	public double getSetpoint(){
		return this.getTalon().getSetpoint();
	}
	
	public double getError(){
		return getSetpoint() - getSpeed();
	}
	
	public CANTalon getTalon(){
		return m_motor.getTalon();
	}
	
	public double getCurrent(){
		return m_motor.getCurrent();
	}
	
	public void setInverted(boolean value){
		m_motor.setInverted(value);
	}
	
	public void enable(){
		m_motor.enable();
	}
	
	public void disable(){
		m_motor.disable();
	}
	
	public boolean isEnabled(){
		return getTalon().get() != 0 && getTalon().isEnabled();
	}
	
	public void enableBrakeMode(boolean brake){
		getTalon().enableBrakeMode(brake);
	}
	

	public boolean onTarget(){
		if(getSpeed() != 0){
			double percent = Math.abs(getError() / getSetpoint()) *100;
			Debugger.println("Shooter Error Percent: " + percent, Robot.shooter, Debugger.info3);
			if (!Double.isNaN(percent) && percent < Robot.prefs.getNumber("S: OnTarget Percent", 10)){
				return true;
			} else {
				return false;
			}
		}
		else{
			return false;
		}
	}
	
}