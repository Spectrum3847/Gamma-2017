package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveForTime extends Command{

	private double throttle;
	private double time;
	
	public DriveForTime() {
		// TODO Auto-generated constructor stub
		this.requires(Robot.drive);
	}
	
	public DriveForTime(double time, double throttle){
		this.time = time;
		this.setTimeout(time);
		this.throttle = throttle;
	}

	public void initialize(){
		/*
		if ((Double)this.time != null || (Double)this.throttle != null){
			this.setTimeout(SmartDashboard.getNumber("DriveForTime Duration",3));
			this.throttle = SmartDashboard.getNumber("DriveForTime Throttle", 0.4);
		}
		*/
	}
	
	public void execute(){
		System.out.println("DRIVE FOR TIME");
		System.out.println("TIME : " + time +" Throttle: " + throttle);
		Robot.drive.setOpenLoop(new DriveSignal(-throttle, -throttle));
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	public void end(){
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void isInterrupted(){
		//System.out.println("DriveForTime INTERRUPTED");
		this.end();
	}
}
