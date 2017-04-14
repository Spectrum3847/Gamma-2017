package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class HeadlightsOn extends Command{

	public HeadlightsOn() {
		// TODO Auto-generated constructor stub
	}
	
	protected void execute(){
		Robot.headlights.headlightsOn();
	}
	
	protected void end(){
		Robot.headlights.headlightsOff();
	}
	
	protected void interrupted(){
		this.end();
	}
	
	protected boolean isFinished(){
		return false;
	}

}
