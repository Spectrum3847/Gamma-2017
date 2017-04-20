package org.spectrum3847.robot.commands.auto;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Constants;
import org.spectrum3847.robot.Robot;
//import org.spectrum3847.robot.subsystems.Drive;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

public class DriveDistance extends Command {

	private double target;
	private double timeout;
	private CANTalon leftTalon;
	private CANTalon rightTalon;
	private double wheelDiameter = 4.2;
	private double yaw = 0;
	
	public DriveDistance(double target, double to)
	{
		requires(Robot.drive);
		timeout = to;
		leftTalon = Robot.leftDrive.getTalon();
		rightTalon = Robot.rightDrive.getTalon();
		this.target = target / (Math.PI * Robot.prefs.getNumber("Wheel Diameter", 4));
	}
	
	public void initialize()
	{
		//target = Robot.prefs.addNumber("A: MMDistance", 3);// / (wheelDiameter*Math.PI);
		rightTalon.changeControlMode(TalonControlMode.PercentVbus);
		
		//17.5 is the distance from the back of the robot to the turning circle's center
		leftTalon.changeControlMode(TalonControlMode.MotionMagic);
		leftTalon.setF(Robot.prefs.getNumber("DA: MM F",0.4));
		leftTalon.setP(Robot.prefs.getNumber("DA: MM P",2));
		leftTalon.setI(Robot.prefs.getNumber("DA: MM I",0));
		leftTalon.setD(Robot.prefs.getNumber("DA: MM D",0.5));
		leftTalon.setMotionMagicCruiseVelocity(Robot.prefs.getNumber("DA: MM CV", 3));
		leftTalon.setMotionMagicAcceleration(Robot.prefs.getNumber("DA: MM CA", 10));
		leftTalon.setPosition(0);
		leftTalon.enable();
		leftTalon.set(target);

		this.setTimeout(timeout);
		debug("Initializing MoveDistance, Setpoint: " + Robot.leftDrive.getTalon().getSetpoint() + "Current position: " + Robot.leftDrive.getTalon().get() + " target: " + target);
		yaw = Robot.navX.getYaw();
		
		//leftTalon.configPeakOutputVoltage(+8f, -8f);
		//rightTalon.configPeakOutputVoltage(+8f,  -8f);
	}
	
	public void execute()
	{
		//debugMotionMagic();
		double rightDrive = -1* leftTalon.get(); //Robot.drive.getSideThrottlePID(yaw, leftTalon.get(), Robot.prefs.getNumber("D: Straight P", 0.04), Robot.prefs.getNumber("D: Straight D", 0.0004));
		rightTalon.set(rightDrive);
		debug("Running MoveDistance, Setting talons to: " + leftTalon.get() + " Setpoint: " + Robot.leftDrive.getTalon().getSetpoint());
	}
	
	public boolean isFinished()
	{
		if(Math.abs(this.getInError()) <= .1 || isTimedOut())
		{
			Debugger.println("Finished Drive Distance", Robot.commands,Debugger.debug2);
			return true;
		} else return false;
	}
	
	public double getError(){
		return Robot.leftDrive.getTalon().getSetpoint() - Robot.leftDrive.getTalon().getPosition();
	}
	
	public double getInError(){
		return getError() * Math.PI * wheelDiameter;
	}
	
	public void end()
	{
		debug("Drive Distance Finished/ Error: " + getInError() + "(" + leftTalon.getClosedLoopError() +") / TimedOut?: " + isTimedOut());
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void interrupted()
	{
		end();
	}
	
	public void debugMotionMagic(){
		debug("------------------------------------------",  Debugger.verbose1);
		debug("Motion Magic Drive Distance",  Debugger.verbose1);
		debug("Pos: " + leftTalon.getPosition() + "/ Vel: " + leftTalon.getSpeed() + "/ Error: " + leftTalon.getClosedLoopError());
		debug("ActTrajPos: " + leftTalon.getMotionMagicActTrajPosition() + "/ ActTrajVel: " + leftTalon.getMotionMagicActTrajVelocity());
		debug("M Ouput: " + leftTalon.get() + " M Voltage: " + leftTalon.getOutputVoltage());
	}
	
	public void debug(String msg, int level){
		Debugger.println(msg, Robot.auton, level);
	}
	public void debug(String msg){
		Debugger.println(msg, Robot.auton, Debugger.debug2);
	}
	
}