package org.spectrum3847.robot.commands.gear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreGear extends CommandGroup {

	public ScoreGear() {
		this("ScoreGear");
	}

	public ScoreGear(String name) {
		super(name);
		this.addParallel(new GearArmPIDScoreDown());
		this.addSequential(new GearIntakeOn(false),1);
		this.addSequential(new GearArmPIDUp());
		// TODO Auto-generated constructor stub
	}

}
