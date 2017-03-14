package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualIntake extends Command {

	public ManualIntake() {
		requires(Robot.climber);
		// TODO Auto-generated constructor stub
	}

	public void execute(){
		if(Math.abs(HW.Operator_Gamepad.getTriggerAxis(Hand.kRight)-HW.Operator_Gamepad.getTriggerAxis(Hand.kLeft)) > SmartDashboard.getNumber("Operator Intake Deadpan", 0.1))
        	Robot.climber.set(HW.Operator_Gamepad.getTriggerAxis(Hand.kRight)-HW.Operator_Gamepad.getTriggerAxis(Hand.kLeft));
	}
;
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
