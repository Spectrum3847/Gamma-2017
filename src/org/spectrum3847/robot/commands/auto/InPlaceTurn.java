package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class InPlaceTurn extends Command{

	//Positive turn is left
	private double target;
	private double timeout;
	
	public InPlaceTurn(double ang, double to) {
		requires(Robot.drive);
		
		target = ang;
		timeout = to;
	}

	public void initialize(){
		Robot.navX.zeroYaw();
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.Voltage);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.Voltage);
		
		this.setTimeout(timeout);
		Debugger.println(("Initializing inPlaceTurn, setPoint: " + target), Robot.auton, Debugger.debug2);
	}
	
	public void execute(){
		double throttle = 6*Robot.drive.getTurnThrottlePID(target, Robot.prefs.getNumber("IPT: Turn P", .04), Robot.prefs.getNumber("IPT: Turn D", .0004));
		double minThrottle = Robot.prefs.getNumber("IPT: Min Throttle", 1.3);
		if (Math.abs(throttle) < minThrottle){
			throttle = minThrottle * Math.signum(throttle);
		}

		//Debugger.println(("inPlaceTurn, throttle: " + throttle), Robot.auton, Debugger.debug2);
		DriveSignal signal = new DriveSignal(throttle, -throttle);
		Robot.drive.setOpenLoop(signal);
	}
	
	@Override
	protected boolean isFinished() {
		if(Math.abs(this.getError()) <= 2 || isTimedOut()){
			return true;
		}
		else return false;
	}
	
	public void end(){
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Debugger.println("inPlaceTurn finished, Error: " + getError() + " Timed Out? " + isTimedOut(), Robot.auton, Debugger.debug2);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));	
	}
	
	public void interrupted(){
		end();
	}
	
	public double getError(){
		return target + Robot.navX.getYaw();
	}

}
