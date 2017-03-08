package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArcadeDrive extends Command{

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.drive.arcadeDrive(HW.Driver_Gamepad.getY(Hand.kLeft), HW.Driver_Gamepad.getX(Hand.kRight), SmartDashboard.getNumber("Drive Deadband", .15), SmartDashboard.getBoolean("Drive Squared Inputs", false));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}
}
