package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ScoreGearBackPack extends Command{

	public ScoreGearBackPack() {
		// TODO Auto-generated constructor stub

	}

	protected void initialize() {
		this.setTimeout(3);
		Robot.gearBackPack.gearSolExtend();
		Debugger.println("Gearback Pack Score", Robot.commands, Debugger.debug2);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}
	
	protected void end(){
		Robot.gearBackPack.gearSolRetract();
		Debugger.println("End: Gearbackpack Score", Robot.commands, Debugger.debug2);
	}
	
	protected void interrupted(){
		this.end();
	}

}
