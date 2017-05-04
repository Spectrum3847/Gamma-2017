package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.commands.gear.GearArmPIDDown;
import org.spectrum3847.robot.commands.gear.IntakeGearUntilCurrent;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExampleCommandGroup extends CommandGroup {

	public ExampleCommandGroup() {
		// TODO Auto-generated constructor stub
	}

	public ExampleCommandGroup(String name) {
		super(name);
		/*this.addParallel(new GearArmPIDDown());
		this.addParallel(new IntakeOn());
		this.addSequential(new IntakeGearUntilCurrent());
		*/
	}

}
