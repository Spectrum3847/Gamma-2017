package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearIntakeOn extends Command{

	private double wheelSpeed;
	private boolean intakeDir;
	public GearIntakeOn(boolean in){
		intakeDir = in;
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		if(intakeDir)
			wheelSpeed = Robot.prefs.getNumber("G: Intake Speed",1);
		else
			wheelSpeed = -1 * Robot.prefs.getNumber("G: Outtake Speed",1);
		
		Robot.gearIntake.setIntake(wheelSpeed);
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
		Robot.gearIntake.setIntake(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}

}