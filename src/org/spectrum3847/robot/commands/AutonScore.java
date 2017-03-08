package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.commands.gear.ScoreGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonScore extends CommandGroup{

	public AutonScore() {
		addSequential(new MoveFeet(3.8), 4);
		//addSequential(new ScoreGear());
		//addSequential(new MoveFeet(-1.5));
	}

}
