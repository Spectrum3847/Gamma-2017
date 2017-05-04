package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.commands.IntakeOn;
import org.spectrum3847.robot.commands.VibrateController;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeGear extends CommandGroup {

	public IntakeGear() {
		this("IntakeGear");
	}

	public IntakeGear(String name) {
		super(name);
		this.addParallel(new GearArmPIDDown());
		this.addParallel(new IntakeOn());
		this.addSequential(new IntakeGearUntilCurrent());
		//this.addSequential(new VibrateController("Gear finished Operator", HW.Driver_Gamepad, 1, 1));
		this.addParallel(new GearIntakeOn(true), 1);
		this.addSequential(new GearArmPIDUp(),1);
		this.addSequential(new GearArmPIDUp());
		// TODO Auto-generated constructor stub
	}

}
