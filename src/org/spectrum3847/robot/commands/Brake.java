package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Brake extends Command {

	public Brake() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(){
		Robot.drive.extendBrakes();
		Robot.left_drive_talon_1.enableBrakeMode(true);
		Robot.left_drive_talon_2.enableBrakeMode(true);
		Robot.left_drive_talon_3.enableBrakeMode(true);
		Robot.right_drive_talon_1.enableBrakeMode(true);
		Robot.right_drive_talon_2.enableBrakeMode(true);
		Robot.right_drive_talon_3.enableBrakeMode(true);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public void end(){
		Robot.drive.retractBrakes();
		Robot.left_drive_talon_1.enableBrakeMode(false);
		Robot.left_drive_talon_2.enableBrakeMode(false);
		Robot.left_drive_talon_3.enableBrakeMode(false);
		Robot.right_drive_talon_1.enableBrakeMode(false);
		Robot.right_drive_talon_2.enableBrakeMode(false);
		Robot.right_drive_talon_3.enableBrakeMode(false);
	}
	
	public void interrupted(){
		end();
	}
}
