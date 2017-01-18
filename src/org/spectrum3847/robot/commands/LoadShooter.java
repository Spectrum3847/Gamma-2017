package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LoadShooter extends Command{

	private double beltSpeed;
	
	public LoadShooter(){};
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		beltSpeed = SmartDashboard.getNumber("Tower Belt Speed");
		Robot.tower.set(beltSpeed);
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
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
