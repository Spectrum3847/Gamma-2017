package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeOn extends Command {

	public IntakeOn() {
		requires(Robot.ballIntake);
		// TODO Auto-generated constructor stub
	}

	public void initialize(){
		Debugger.println("Starting Intake", Robot.intake, Debugger.info3);
		Robot.ballIntake.setIntake(1.0);
	}
	
	public void execute(){
	}
;
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Debugger.println("DISABLING Intake", Robot.intake, Debugger.info3);
		Robot.ballIntake.setIntake(0);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}

}
