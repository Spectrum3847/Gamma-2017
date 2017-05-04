package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoScoreGearBackPack extends Command{

	public AutoScoreGearBackPack() {
	}

	protected void initialize() {
		
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.gearBackPack.getSoringSensor();
	}
	
	protected void end(){
		Debugger.println("End: Gearbackpack Auto Score", Robot.commands, Debugger.debug2);
		//new LEDScoreGearBackPack().start();
		new ScoreGearBackPack().start();
	}
	
	protected void interrupted(){
	}

}
