package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntakeOn extends Command{

	private double wheelSpeed;
	
	public GearIntakeOn(){}
	
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		wheelSpeed = SmartDashboard.getNumber("Gear Intake Speed");
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