package org.spectrum3847.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Constants;
import org.spectrum3847.robot.Robot;
//import org.spectrum3847.robot.subsystems.Drive;

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
		
		kP = SmartDashboard.getNumber("MoveFeet kP",  0.0004);
		kD = SmartDashboard.getNumber("MoveFeet kD", 0.0006);
		target = (targetFeet*12/(4*Math.PI) * Constants.TPR);
		Robot.leftDrive.getTalon().setPosition(0);
		oldError = target - Robot.leftDrive.getTalon().getEncPosition();
        //Robot.leftDrive.getTalon().setPosition(0);
		Debugger.println("Initializing MoveFeet", Robot.commands,Debugger.debug2);

	}
	
	public void execute()
	{
		//kP = SmartDashboard.getNumber("kP");
		//kD = SmartDashboard.getNumber("kD");
		System.out.println(error = target - Robot.leftDrive.getTalon().getEncPosition());
		
		P = error * kP;
		D = (error - oldError)*kD;
		double out = Math.max(Math.min(0.5,  P+D), - 0.5);
		Robot.drive.setOpenLoop(new DriveSignal (out, out));
		
		oldError = error;
		
	}
	
	public boolean isFinished()
	{
		if(Math.abs(error) <= 20 )
		{
			Debugger.println("Finished MoveFeet", Robot.commands,Debugger.debug2);
			return true;
		} else return false;
	}
	
	public void end()
	{
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void interrupted()
	{
		end();
	}
}