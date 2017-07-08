package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RaiseBallFlap extends Command{

	public RaiseBallFlap() {
		// TODO Auto-generated constructor stub
	}

	public void initialize(){
		Robot.gearBackPack.gearFlapRetract();
		Robot.gearBackPack.ballFlapExtend();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
