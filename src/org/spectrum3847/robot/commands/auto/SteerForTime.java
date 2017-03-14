package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SteerForTime extends Command{

	private double leftThrottle;
	private double rightThrottle;
	private double time;
	
	public SteerForTime() {
		this.requires(Robot.drive);
	}
	
	public SteerForTime(double time, double leftThrottle, double rightThrottle){
		this.time = time;
		this.setTimeout(time);
		this.leftThrottle = leftThrottle;
		this.rightThrottle = rightThrottle;
	}

	public void initialize(){
		Debugger.println("STEER FOR TIME", Robot.auton, Debugger.info3);
	}
	
	public void execute(){
		Debugger.println("TIME : " + time +" Left Throttle: " + leftThrottle + " Right Throttle: " + rightThrottle, Robot.auton, Debugger.debug2);
		Robot.drive.setOpenLoop(new DriveSignal(-leftThrottle, -rightThrottle));
	}
	
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	public void end(){
		Debugger.println("STEER FOR TIME FINISHED", Robot.auton, Debugger.info3);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void isInterrupted(){
		this.end();
	}
}
