
package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
		Debugger.println("DRIVE FOR TIME", Robot.auton, Debugger.info3);
	}
	
	public void execute(){
		Debugger.println("TIME : " + time +" Throttle: " + throttle, Robot.auton, Debugger.debug2);
		Robot.drive.setOpenLoop(new DriveSignal(-throttle, -throttle));
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	public void end(){
		Debugger.println("DRIVE FOR TIME FINISHED", Robot.auton, Debugger.info3);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void isInterrupted(){
		Debugger.println("DRIVE FOR TIME INTERRUPTED", Robot.auton, Debugger.info3);
		this.end();
	}
}
