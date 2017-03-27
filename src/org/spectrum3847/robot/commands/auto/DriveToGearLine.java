package org.spectrum3847.robot.commands.auto;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Constants;
import org.spectrum3847.robot.Robot;
//import org.spectrum3847.robot.subsystems.Drive;

import com.ctre.CANTalon.TalonControlMode;

public class DriveToGearLine extends Command {

	private double inchesFromDS;
	private double inchesToDrive;
	private double target;
	
	public DriveToGearLine(double inchesFromDS)
	{
		requires(Robot.drive);
		//14.5 is a placeholder for the distance from the center of the robot to the point on the robot we measure from
		this.inchesFromDS = inchesFromDS + 17.5;
		
	}
	
	public void initialize()
	{
		//kP = 0.0004;
		//kD = 0.0006;
		System.out.print("Moving in Auto");
		//kP = SmartDashboard.getNumber("MoveFeet kP",  0.0004);
		//kD = SmartDashboard.getNumber("MoveFeet kD", 0.0006);
		
		//17.5 is the distance from the back of the robot to the turning circle's center
		this.inchesToDrive = (131.35 * (this.inchesFromDS + 133.42)/227.51) - 17.25;
		target = this.inchesToDrive/(4.25*Math.PI);// Constants.TPR);
		Robot.leftDrive.getTalon().configEncoderCodesPerRev(120);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.Position);
		Robot.leftDrive.getTalon().reverseOutput(true);
		Robot.leftDrive.getTalon().reverseSensor(false);
		Robot.leftDrive.getTalon().configPeakOutputVoltage(+0f, -6f);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.Follower);
		Robot.rightDrive.set(Robot.leftDrive.getTalon().getDeviceID());
		Robot.rightDrive.getTalon().reverseOutput(true);
		Robot.leftDrive.getTalon().setPosition(0);
		//oldError = target - Robot.leftDrive.getTalon().getEncPosition();
		Robot.leftDrive.getTalon().setPID(Robot.prefs.getNumber("A: DriveToGearLine P", 0),Robot.prefs.getNumber("A: DriveToGearLine I", 0),Robot.prefs.getNumber("A: DriveToGearLine D", 0),Robot.prefs.getNumber("A: DriveToGearLine F", 1),3000,100,0);
		Robot.leftDrive.getTalon().set(target);
		System.out.println("Drive To Gear Line Setpoint Set: " + this.target);
		this.setTimeout(10);//SmartDashboard.getNumber("MoveFeet Timeout", 10));
		Debugger.println("Initializing MoveFeet, Setpoint: " + Robot.leftDrive.getTalon().getSetpoint(), Robot.commands,Debugger.fatal6);
	}
	
	public void execute()
	{
		System.out.println("[DriveToGearLine] Setpoint: " + Robot.leftDrive.getTalon().getSetpoint() + " Position: " + Robot.leftDrive.getTalon().getPosition() + " Error: " + (this.getError()));
	}
	
	public boolean isFinished()
	{
		if(this.getError() <= .025 || isTimedOut())
		{
			Debugger.println("Finished MoveFeet", Robot.commands,Debugger.debug2);
			return true;
		} else return false;
	}
	
	public double getError(){
		return Robot.leftDrive.getTalon().getSetpoint() - Robot.leftDrive.getTalon().getPosition();
	}
	
	public void end()
	{
		System.out.println("[DriveToGearLine] Finished @ Position " + Robot.leftDrive.getTalon().getPosition() + "Error: " + this.getError() +  " Timed Out? " + isTimedOut());
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