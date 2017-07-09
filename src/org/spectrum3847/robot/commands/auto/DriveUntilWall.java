package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilWall extends Command {

	private double cruiseCurrent = 40;
	private double throttle;
	private double turnAdjustment;
	private double initTime;
	private double lastUpdate;
	
	public DriveUntilWall() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(){
		Debugger.println("Driving to wall", Robot.auton, Debugger.info3);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.Voltage);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.Voltage);
		this.throttle = -1* Robot.prefs.getNumber("A: Drive To Wall Throttle", -5);
		this.turnAdjustment = Robot.prefs.getNumber("A: Drive to Wall Drive Straight Adjust", -.05);
		this.initTime = Timer.getFPGATimestamp();
		this.lastUpdate = initTime;
	}
	
	public void execute(){
		DriveSignal signal = new DriveSignal(throttle + this.turnAdjustment, throttle + this.turnAdjustment);
		Robot.drive.setOpenLoop(signal);
		
		if((Timer.getFPGATimestamp() - initTime) > 1.25){
			if( (Timer.getFPGATimestamp() - lastUpdate) > .5){
				cruiseCurrent = Robot.leftDrive.getTalon().getOutputCurrent();
				lastUpdate = Timer.getFPGATimestamp();
			}
		}
		
		Debugger.println("Drive to wall cruiseCurrent:" + cruiseCurrent, Robot.auton, Debugger.verbose1);
		Debugger.println("Drive to wall throttle:" + throttle, Robot.auton, Debugger.verbose1);
			
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if ((Timer.getFPGATimestamp() - initTime > .75) && Robot.leftDrive.getTalon().getOutputCurrent() > Robot.prefs.getNumber("A: Drive to Wall Current Trigger", 11)){//(cruiseCurrent * Robot.prefs.getNumber("A: Drive Into Wall Current Ratio",  1.25))){
			Debugger.println("Hit wall. Time: " + (Timer.getFPGATimestamp() - initTime) + " Current: " + Robot.leftDrive.getTalon().getOutputCurrent(), Robot.auton, Debugger.info3);
			return true;
		}
		return false;
	}
	
	protected void end(){
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	protected void interrupted(){
		this.end();
	}

}
