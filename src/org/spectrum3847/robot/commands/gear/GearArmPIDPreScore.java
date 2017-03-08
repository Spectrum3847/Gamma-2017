package org.spectrum3847.robot.commands.gear;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearArmPIDPreScore extends GearArmPIDCommand {

	public GearArmPIDPreScore() {
		super();
	}
	
	double getSetpoint() {
		return SmartDashboard.getNumber("Gear Arm Score Angle", .2);
	}
	
}
