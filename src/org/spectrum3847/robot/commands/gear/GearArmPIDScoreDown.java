package org.spectrum3847.robot.commands.gear;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearArmPIDScoreDown extends GearArmPIDCommand {

	public GearArmPIDScoreDown() {
		super();
	}
	
	double getSetpoint() {
		return SmartDashboard.getNumber("Gear Arm ScoreDown Angle", .5);
	}

}
