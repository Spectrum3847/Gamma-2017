package org.spectrum3847.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Constants;
import org.spectrum3847.robot.Robot;
//import org.spectrum3847.robot.subsystems.Drive;

import com.ctre.CANTalon.TalonControlMode;

public class MoveFeet extends Command {

	private double targetFeet;
	private double target;
	double oldError;
	double kP;
	double kD;
	double error;
	double P;
	double D;
	
	public MoveFeet(double targetFeet)
	{
		requires(Robot.drive);
		this.targetFeet = targetFeet;
	}
	
	public void initialize()
	{
		//kP = 0.0004;
		//kD = 0.0006;
		System.out.print("Moving in Auto");
		//kP = SmartDashboard.getNumber("MoveFeet kP",  0.0004);
		//kD = SmartDashboard.getNumber("MoveFeet kD", 0.0006);
		target = targetFeet*12/(4*Math.PI);// Constants.TPR);
		Robot.leftDrive.getTalon().configEncoderCodesPerRev(120);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.Position);
		Robot.leftDrive.getTalon().reverseOutput(true);
		Robot.leftDrive.getTalon().reverseSensor(false);
		Robot.leftDrive.getTalon().configPeakOutputVoltage(+6f, 0f);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.Follower);
		Robot.rightDrive.set(Robot.leftDrive.getTalon().getDeviceID());
		Robot.rightDrive.getTalon().reverseOutput(false);
		Robot.leftDrive.getTalon().setPosition(0);
		//oldError = target - Robot.leftDrive.getTalon().getEncPosition();
		Robot.leftDrive.getTalon().setPID(SmartDashboard.getNumber("MoveFeet P",1000), SmartDashboard.getNumber("MoveFeet I", 0), SmartDashboard.getNumber("MoveFeet D", 0), SmartDashboard.getNumber("MoveFeet F", 1000), 0, 100, 0);
		Robot.leftDrive.getTalon().set(target);
		this.setTimeout(SmartDashboard.getNumber("MoveFeet Timeout", 5));
		Debugger.println("Initializing MoveFeet", Robot.commands,Debugger.fatal6);
		
	}
	
	public void execute()
	{
		
	}
	
	public boolean isFinished()
	{
		if(Math.abs(Robot.leftDrive.getTalon().getError()) <= 20 || isTimedOut())
		{
			Debugger.println("Finished MoveFeet", Robot.commands,Debugger.debug2);
			return true;
		} else return false;
	}
	
	public void end()
	{
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
		Robot.leftDrive.getTalon().configPeakOutputVoltage(+12f, -12f);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void interrupted()
	{
		end();
	}
}