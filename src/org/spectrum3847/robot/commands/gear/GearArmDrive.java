package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearArmDrive extends Command{

	public GearArmDrive(){
		requires(Robot.gearIntake);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		double throttle;
		if ( Math.abs(HW.Driver_Gamepad.getTriggerAxis(Hand.kRight) - HW.Driver_Gamepad.getTriggerAxis(Hand.kLeft)) > SmartDashboard.getNumber("Gear Arm Deadband", .1) ){
			throttle = HW.Driver_Gamepad.getTriggerAxis(Hand.kRight) - HW.Driver_Gamepad.getTriggerAxis(Hand.kLeft);
		}
		else 
			throttle = HW.Operator_Gamepad.getTriggerAxis(Hand.kRight) - HW.Operator_Gamepad.getTriggerAxis(Hand.kLeft);
		Robot.gearIntake.setArmMotor(throttle * SmartDashboard.getNumber("Gear Arm Ramp Factor", .25));
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.gearIntake.setArmMotor(0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}

}
