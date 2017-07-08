package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LowerBallFlap extends Command{

	public LowerBallFlap() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(){
		Robot.gearBackPack.gearFlapExtend();
		Robot.gearBackPack.ballFlapRetract();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
