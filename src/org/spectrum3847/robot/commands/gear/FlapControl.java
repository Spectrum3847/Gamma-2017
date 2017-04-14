package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class FlapControl extends Command{

	public FlapControl() {
		// TODO Auto-generated constructor stub
	}

	protected void execute(){
		if(Math.abs(HW.Operator_Gamepad.getY(Hand.kLeft)) < .5){
			Robot.gearBackPack.ballFlapExtend();
			Robot.gearBackPack.gearFlapRetract();
		}
		else if(HW.Operator_Gamepad.getY(Hand.kLeft) > 0){
			Robot.gearBackPack.ballFlapExtend();
			Robot.gearBackPack.gearFlapExtend();
		}
		else if(HW.Operator_Gamepad.getY(Hand.kLeft) < 0){
			Robot.gearBackPack.ballFlapRetract();
			Robot.gearBackPack.gearFlapExtend();
		}
			
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
