package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class SetClimber extends Command{

	private double spd = 1;
	public SetClimber(double speed) {
		// TODO Auto-generated constructor stub
		spd = speed;
	}
	public void initialize(){
		Debugger.println("Starting Climber", Robot.climb, Debugger.info3);
		Robot.climber.set(spd);
		Robot.compressor.stop();
	}
	

	public void execute(){
		if (Robot.climber.getCurrent() > 40){
			Robot.climber.set(1);
		}
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end(){
		Robot.climber.set(0);
		Robot.compressor.start();
	}
	
	protected void interrupted(){
		this.end();
	}

}
