package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collect extends Command{
	
	private double collectSpeed;
	
	public void Collect(){
		
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		collectSpeed = SmartDashboard.getNumber("Collector Speed");
		Robot.collector.set(collectSpeed);
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
		Robot.collector.set(0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}

}
