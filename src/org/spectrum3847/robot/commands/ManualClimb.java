package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ManualClimb extends Command{

	public ManualClimb() {
		// TODO Auto-generated constructor stub
	}

	public void execute(){
		//System.out.println("Operator Trigger Right, Driver Trigger Right" + HW.Operator_Gamepad.getTriggerAxis(Hand.kRight) + ", " + HW.Driver_Gamepad.getTriggerAxis(Hand.kRight));
		if (HW.Operator_Gamepad.getTriggerAxis(Hand.kRight) > .5){
			Robot.climber.set(Math.pow((HW.Operator_Gamepad.getTriggerAxis(Hand.kRight)),2));
			if(Robot.compressor.enabled())
				Robot.compressor.stop();
		}
		else if (HW.Driver_Gamepad.getTriggerAxis(Hand.kRight) > .5){
			Robot.climber.set(Math.pow(HW.Driver_Gamepad.getTriggerAxis(Hand.kRight),2));
			if(Robot.compressor.enabled())
				Robot.compressor.stop();
		}
		else if (HW.Operator_Gamepad.getTriggerAxis(Hand.kRight) > .2){
			Robot.climber.set(Robot.prefs.getNumber("C: Climber Creep Throttle", .1));
			if(Robot.compressor.enabled())
				Robot.compressor.stop();
		}
		else{
			Robot.climber.set(0);
			if(!Robot.compressor.enabled())
				Robot.compressor.start();
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end(){
		Robot.climber.set(0);
	}
	
	protected void interrupted(){
		this.end();
	}

}
