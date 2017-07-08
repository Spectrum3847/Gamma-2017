package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualIntake extends Command {

	public ManualIntake() {
		requires(Robot.ballIntake);
		// TODO Auto-generated constructor stub
	}

	public void execute(){
		if(Math.abs(HW.Operator_Gamepad.getY(Hand.kRight)) > .3)
        	Robot.ballIntake.setIntake(HW.Operator_Gamepad.getY(Hand.kRight));
		else
			Robot.ballIntake.setIntake(0);
	}
;
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
