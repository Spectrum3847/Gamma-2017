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
	
	public DriveDistance(double to)
	{
		requires(Robot.drive);
		timeout = to;
		leftTalon = Robot.leftDrive.getTalon();
		rightTalon = Robot.rightDrive.getTalon();
	}
	
	public void initialize()
	{
		target = Robot.prefs.addNumber("A: MMDistance", 3);// / (wheelDiameter*Math.PI);
		rightTalon.changeControlMode(TalonControlMode.PercentVbus);
		
		//17.5 is the distance from the back of the robot to the turning circle's center
		leftTalon.changeControlMode(TalonControlMode.MotionMagic);
		leftTalon.setF(Robot.prefs.getNumber("DA: MM F",0));
		leftTalon.setP(Robot.prefs.getNumber("DA: MM P",0));
		leftTalon.setI(Robot.prefs.getNumber("DA: MM I",0));
		leftTalon.setD(Robot.prefs.getNumber("DA: MM D",0));
		leftTalon.setMotionMagicCruiseVelocity(Robot.prefs.getNumber("DA: MM CV", 0));
		leftTalon.setMotionMagicAcceleration(Robot.prefs.getNumber("DA: MM CA", 0));
		leftTalon.setPosition(0);
		leftTalon.enable();
		leftTalon.set(target);

		this.setTimeout(timeout);
		debug("Initializing MoveDistance, Setpoint: " + Robot.leftDrive.getTalon().getSetpoint());
		yaw = Robot.navX.getYaw();
	}
	
	public void execute()
	{
		debugMotionMagic();
		double rightDrive = -1* Robot.drive.getRightDriveStraight(yaw, leftTalon.get());
		rightTalon.set(rightDrive);
	}
	
	public boolean isFinished()
	{
		if(this.getInError() <= .1 || isTimedOut())
		{
			Debugger.println("Finished Drive Distance", Robot.commands,Debugger.debug2);
			return false;
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