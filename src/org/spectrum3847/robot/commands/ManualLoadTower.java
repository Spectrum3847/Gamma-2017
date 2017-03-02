package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManualLoadTower extends Command{

	public ManualLoadTower() {
		requires(Robot.tower);
		requires(Robot.beltIntake);
		
	}
	
	public void execute(){
		if(Math.abs(HW.Operator_Gamepad.getY(Hand.kRight)) > SmartDashboard.getNumber("Operator Elevator Deadpan", 0.1)){
			Robot.tower.set(HW.Operator_Gamepad.getY(Hand.kRight)*SmartDashboard.getNumber("Elevator Ramp Rate", 0.25));
			Robot.beltIntake.set(HW.Operator_Gamepad.getY(Hand.kRight)*SmartDashboard.getNumber("Intake Ramp Rate", 0.25));
		}
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	public void end(){
		Robot.tower.set(0);
		Robot.beltIntake.set(0);
	}
	

}
