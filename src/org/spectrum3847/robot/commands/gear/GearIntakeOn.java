package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntakeOn extends Command{

	private double wheelSpeed;
	private boolean intakeDir;
	public GearIntakeOn(boolean in){
		intakeDir = in;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		if(intakeDir)
			wheelSpeed = SmartDashboard.getNumber("Gear Intake Speed");
		else
			wheelSpeed = -1 * SmartDashboard.getNumber("Gear Outtake Speed");
		
		Robot.gearIntake.setIntake(wheelSpeed);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.gearIntake.setIntake(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}

}