package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climb extends Command{

	public Climb() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(){
		Robot.drive.setClimber(SmartDashboard.getNumber("Climber Speed", .75));
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void end(){
		Robot.drive.setClimber(0);
	}
	
	public void interrupted(){
		end();
	}

}
