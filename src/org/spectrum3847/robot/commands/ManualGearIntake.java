package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ManualGearIntake extends Command{

	public ManualGearIntake() {
		// TODO Auto-generated constructor stub
	}

	protected void execute(){
		if (HW.Driver_Gamepad.getTriggerAxis(Hand.kLeft) > .2)
			Robot.gearIntake.setIntake(HW.Driver_Gamepad.getTriggerAxis(Hand.kLeft));
		else if (HW.Operator_Gamepad.getTriggerAxis(Hand.kLeft) > .2)
			Robot.gearIntake.setIntake(HW.Operator_Gamepad.getTriggerAxis(Hand.kLeft));
		else
			Robot.gearIntake.setIntake(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
