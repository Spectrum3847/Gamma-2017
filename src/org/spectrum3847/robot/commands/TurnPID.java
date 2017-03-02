package org.spectrum3847.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.ChezyMath;
import org.spectrum3847.robot.Robot;

public class TurnPID extends Command{
	
	
	private double target ;
	private double kP;
	private double kD;
	private double error;
	private double lastError;
	//private double sumError;
	
	public TurnPID(double angle)
	{
		requires(Robot.drive);
		target = angle;
	}
	
	protected void initialize()
	{
		Robot.navX.reset();
		lastError = ChezyMath.boundAngleNeg180to180Degrees(target - Robot.navX.getYaw());//Set OG lastError to regular error so that D at first will be 0
		//sumError = 0;
		System.out.println("Initializing. Yaw = " + Robot.navX.getYaw());
		System.out.println("Angle = " + Robot.navX.getAngle());
	}
	
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{		
		
		kP = SmartDashboard.getNumber("TurnPID kP", 0.015);
		kD = SmartDashboard.getNumber("TurnPID kD", 0.003);
		
		error = ChezyMath.boundAngleNeg180to180Degrees(target - Robot.navX.getYaw());
		SmartDashboard.putNumber("Error",  error);
		double P = error*kP;
		double D = (error - lastError) * kD;
	  //double I = sumError * kI;
		SmartDashboard.putNumber("D", D);
		SmartDashboard.putNumber("P", P);
		double out = Math.max(-0.5, Math.min(0.5, P+D));
		System.out.println("Error: " + error);
		SmartDashboard.putNumber("out", out);
		Robot.drive.setOpenLoop(new DriveSignal(out, -out));
		
		lastError = error;
		//sumError += error;		
	}
	
	protected boolean isFinished()
	{
		if(Math.abs(error) < 3)
		{
			System.out.println("Finished Turning");
			return true;
		} else return false;
	}
	protected void end()
	{
		Robot.navX.zeroYaw();
		System.out.println(Robot.navX.getYaw());
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	protected void interrupted()
	{
		end();
	}
}
