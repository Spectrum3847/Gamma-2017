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
	private boolean reset = true;
	private boolean isRight = true;
	private double throttlePref = 7;
	private double minThrottlePref = 4;
	private double kp = 0;
	private double kd = 0;
			
	
	public InPlaceTurn(double ang, boolean r, double to) {
		requires(Robot.drive);
		reset = r;
		target = ang;
		timeout = to;
	}
	
	public InPlaceTurn(double ang, double to) {
		requires(Robot.drive);
		
		target = ang;
		timeout = to;
	}

	public void initialize(){
		if (reset){
			Robot.navX.zeroYaw();
		}
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.Voltage);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.Voltage);
		
		this.setTimeout(timeout);
		Debugger.println(("Initializing inPlaceTurn, setPoint: " + target), Robot.auton, Debugger.debug2);
		if (target >= 0) {
			throttlePref = Robot.prefs.getNumber("IPTR: Turn Throttle", 8);
			kp = Robot.prefs.getNumber("IPTR: Turn P", .0225);
			kd = Robot.prefs.getNumber("IPTR: Turn D", .09);
			minThrottlePref = Robot.prefs.getNumber("IPTR: Min Throttle", 4);
		} else{
			throttlePref = Robot.prefs.getNumber("IPTL: Turn Throttle", 8);
			kp = Robot.prefs.getNumber("IPTL: Turn P", .04);
			kd = Robot.prefs.getNumber("IPTL: Turn D", .0004);
			minThrottlePref = Robot.prefs.getNumber("IPTL: Min Throttle", 4);
		}
		Robot.prefs.remove("IPT: Turn Throttle");
		Robot.prefs.remove("IPT: Turn P");
		Robot.prefs.remove("IPT: Turn D");
		Robot.prefs.remove("IPT: Min Throttle");
		Robot.prefs.remove("IPRT: Turn P");
	}
	
	public void execute(){
		double throttle = throttlePref * Robot.drive.getTurnThrottlePID(target, kp, kd);
		double minThrottle = minThrottlePref;
		if (Math.abs(throttle) < minThrottle){
			throttle = minThrottle * Math.signum(throttle +.000001);
		}
		DriveSignal signal = new DriveSignal(-throttle, throttle);
		Robot.drive.setOpenLoop(signal);
		debug("Running IPT, Setting talons to: " + Robot.leftDrive.getTalon().get() + " Error: " + this.getError());
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
		return target - Robot.navX.getYaw();
	}

	public void debug(String msg, int level){
		Debugger.println(msg, Robot.auton, level);
	}
	public void debug(String msg){
		Debugger.println(msg, Robot.auton, Debugger.debug2);
	}
}
