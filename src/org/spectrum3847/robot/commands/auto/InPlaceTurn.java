package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class InPlaceTurn extends Command{

	private double target;
	private double timeout;
	
	public InPlaceTurn(double ang, double to) {
		requires(Robot.drive);
		
		target = ang;
		timeout = to;
	}

	public void initialize(){
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		
		this.setTimeout(timeout);
		Debugger.println(("Initializing inPlaceTurn, setPoint: " + target), Robot.auton, Debugger.debug2);
	}
	
	public void execute(){
		double throttle = Robot.drive.getSideThrottlePID(target, 0, Robot.prefs.getNumber("IPT: Turn P", .04), Robot.prefs.getNumber("IPT: Turn D", .0004));
		DriveSignal signal = new DriveSignal(throttle, -throttle);
		Robot.drive.setOpenLoop(signal);
	}
	
	@Override
	protected boolean isFinished() {
		if(isTimedOut()){//this.getError() <= .1 || isTimedOut()){
			return true;
		}
		else return false;
	}
	
	public void end(){
		Debugger.println("inPlaceTurn finished, Error: " + getError() + " Timed Out? " + isTimedOut(), Robot.auton, Debugger.debug2);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));	
	}
	
	public void interrupted(){
		end();
	}
	
	public double getError(){
		return target - Robot.navX.getYaw();
	}

}
