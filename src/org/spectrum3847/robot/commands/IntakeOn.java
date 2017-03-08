package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeOn extends Command{

	private double beltSpeed;
	private Boolean buttonControl;
	
	public IntakeOn(Boolean button){
		buttonControl = button;
	}
	
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		beltSpeed = SmartDashboard.getNumber("Belt Intake Speed", .75);
		if(buttonControl)
			Robot.beltIntake.set(beltSpeed);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(!buttonControl && HW.Operator_Gamepad.getY(Hand.kLeft) > .1)
			Robot.beltIntake.set(HW.Operator_Gamepad.getY(Hand.kLeft));
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
