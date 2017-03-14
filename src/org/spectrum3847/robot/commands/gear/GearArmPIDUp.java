package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;

public class GearArmPIDUp extends GearArmPIDCommand {

	public GearArmPIDUp() {
		super();
	}
	
	double getSetpoint() {
		return Robot.prefs.getNumber("G: Up Angle", .05);
	}

}
