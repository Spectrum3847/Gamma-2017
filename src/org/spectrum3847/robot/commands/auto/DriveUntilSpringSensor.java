package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilSpringSensor extends Command{

	private double currentTrigger;
	private double throttle;
	
	public DriveUntilSpringSensor() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		Debugger.println("Backpack Gear Auto Started", Robot.auton, Debugger.info3);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		this.throttle = Robot.prefs.getNumber("A: BP Gear Throttle", .4);
	}
	
	public void execute() {
		Robot.drive.arcadeDrive(-throttle, 0, 0, false);
		System.out.println("Drive until spring: " + this.throttle);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.gearBackPack.getSoringSensor();
	}
	
	public void end(){
		Debugger.println("Spring Auto Trigger Reached", Robot.auton, Debugger.info3);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void interrupted(){
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}

}
