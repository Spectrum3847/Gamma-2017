package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeON extends Command{

	private double beltSpeed;
	
	public IntakeON(){}
	
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		beltSpeed = SmartDashboard.getNumber("Belt Intake Speed");
		Robot.beltIntake.set(beltSpeed);
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
		Robot.beltIntake.set(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}

}
