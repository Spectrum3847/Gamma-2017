package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveTurnMove extends CommandGroup{

	public MoveTurnMove() {
		addSequential(new MoveFeet(2));
		addSequential(new TurnPID(90));
		addSequential(new MoveFeet(2));
	}

}
