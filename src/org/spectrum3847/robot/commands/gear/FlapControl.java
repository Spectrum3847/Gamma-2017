package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.leds.Red;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class FlapControl extends Command{
	Command red;

	public FlapControl() {
		// TODO Auto-generated constructor stub
		red = new Red();
	}

	protected void execute(){
		if(Math.abs(HW.Operator_Gamepad.getY(Hand.kLeft)) < .5){
			Robot.gearBackPack.ballFlapExtend();
			Robot.gearBackPack.gearFlapRetract();
			red.cancel();
		}
		else if(HW.Operator_Gamepad.getY(Hand.kLeft) > 0){
			Robot.gearBackPack.ballFlapExtend();
			Robot.gearBackPack.gearFlapExtend();
			red.cancel();
		}
		else if(HW.Operator_Gamepad.getY(Hand.kLeft) < 0){
			Robot.gearBackPack.ballFlapRetract();
			Robot.gearBackPack.gearFlapExtend();
			red.start();
		}
			
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
