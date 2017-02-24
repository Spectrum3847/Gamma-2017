package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeGearUntilCurrent extends Command{

	private double speed;
	
	public IntakeGearUntilCurrent(){
	}
	
	
	@Override
	protected void initialize() {
		speed = SmartDashboard.getNumber("Gear Intake Speed", .75);
	}

	@Override
	protected void execute() {
		Robot.gearIntake.setIntake(speed);
	}

	@Override
	protected boolean isFinished() {
		//If output current of the intake motor is higher than the dashboard value end this command
		return Robot.gearIntake.getIntakeTalon().getOutputCurrent() > SmartDashboard.getNumber("Gear Intake Current Limit", 12);
	}

	@Override
	protected void end() {
		Robot.gearIntake.setIntake(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}

}
