package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveUntilGearArmCurrent extends Command{

	private double currentTrigger;
	private double throttle;
	
	public DriveUntilGearArmCurrent() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		this.currentTrigger = SmartDashboard.getNumber("Current Gear Auto Current Trigger", .1);
		this.throttle = SmartDashboard.getNumber("Current Gear Auto Throttle", .5);
	}
	
	public void execute() {
		System.out.println("Gear Arm Current " + Robot.gearIntake.getArmTalon().getOutputCurrent());
		Robot.drive.setOpenLoop(new DriveSignal(-throttle, -throttle));
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.gearIntake.getArmTalon().getOutputCurrent() > this.currentTrigger;
	}
	
	public void end(){
		System.out.print("Current Auto Trigger Reached");
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void interrupted(){
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}

}
