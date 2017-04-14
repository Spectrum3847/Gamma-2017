package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class DriveUntilGearArmCurrent extends Command{

	private double currentTrigger;
	private double throttle;
	
	public DriveUntilGearArmCurrent() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		Debugger.println("Current Gear Auto Started", Robot.auton, Debugger.info3);
		Robot.leftDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.rightDrive.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		this.currentTrigger = Robot.prefs.getNumber("A: Gear Amps Trigger", .5);
		this.throttle = Robot.prefs.getNumber("A: Gear Throttle", .5);
	}
	
	public void execute() {
		Debugger.println("Gear Arm Current " + Robot.gearIntake.getArmTalon().getOutputCurrent(), Robot.auton, Debugger.debug2);
		Robot.drive.setOpenLoop(new DriveSignal(-throttle, -throttle));
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.gearIntake.getArmTalon().getOutputCurrent() > this.currentTrigger;
	}
	
	public void end(){
		Debugger.println("Current Auto Trigger Reached", Robot.auton, Debugger.info3);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void interrupted(){
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}

}
