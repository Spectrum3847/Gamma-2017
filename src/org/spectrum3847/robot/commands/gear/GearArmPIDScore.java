package org.spectrum3847.robot.commands.gear;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearArmPIDScore extends GearArmPIDCommand {

	public GearArmPIDScore() {
		super();
	}
	
	double getSetpoint() {
		return SmartDashboard.getNumber("Gear Arm Score Angle", .2);
	}
	
}
