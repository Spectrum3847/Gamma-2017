package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LoadTower extends Command{

	public LoadTower() {
	}
	
	public void initialize(){
		Robot.beltIntake.set(SmartDashboard.getNumber("Belt Intake Speed"));
	}
	
	public void end(){
		Robot.tower.set(0);
	}
	
	public void interrupted(){
		end();
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
