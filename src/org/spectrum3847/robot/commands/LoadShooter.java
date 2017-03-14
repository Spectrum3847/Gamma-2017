package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LoadShooter extends Command{

	
	public LoadShooter(){};
	
	@Override
	protected void initialize() {
		Robot.beltBed.set(SmartDashboard.getNumber("Belt Bed Speed",-1));
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
		Robot.beltBed.set(0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
		
	}

}
