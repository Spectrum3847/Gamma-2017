package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;

public class GearArmPIDPreScore extends GearArmPIDCommand {

	public GearArmPIDPreScore() {
		super();
	}
	
	double getSetpoint() {
		return Robot.prefs.getNumber("G: PreScore Angle", .2);
	}
	
}
