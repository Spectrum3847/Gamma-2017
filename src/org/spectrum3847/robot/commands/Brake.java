package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Brake extends Command {

	public Brake() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		Robot.drive.extendBrakes();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public void end(){
		Robot.drive.retractBrakes();
	}
	
	public void interrupted(){
		end();
	}
}
