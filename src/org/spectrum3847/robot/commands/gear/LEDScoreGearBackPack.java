package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.commands.leds.Yellow;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LEDScoreGearBackPack extends CommandGroup {

	public LEDScoreGearBackPack() {
		this.addParallel(new Yellow());
		this.addSequential(new ScoreGearBackPack());
	}

	public LEDScoreGearBackPack(String name) {
		super(name);
		this.addParallel(new Yellow());
		this.addSequential(new ScoreGearBackPack());
	}
}
