package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;

public class GearArmPIDScoreDown extends GearArmPIDCommand {

	public GearArmPIDScoreDown() {
		super();
	}
	
	double getSetpoint() {
		return Robot.prefs.getNumber("G: ScoreDown Angle", .3);
	}

}
