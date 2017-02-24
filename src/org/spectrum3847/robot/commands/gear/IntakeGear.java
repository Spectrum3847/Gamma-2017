package org.spectrum3847.robot.commands.gear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeGear extends CommandGroup {

	public IntakeGear() {
		this("IntakeGear");
	}

	public IntakeGear(String name) {
		super(name);
		this.addParallel(new GearArmPIDDown());
		this.addSequential(new IntakeGearUntilCurrent());
		this.addSequential(new GearArmPIDUp());
		// TODO Auto-generated constructor stub
	}

}
