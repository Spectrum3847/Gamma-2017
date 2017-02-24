package org.spectrum3847.robot.commands.gear;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearArmPIDUp extends GearArmPIDCommand {

	public GearArmPIDUp() {
		super();
	}
	
	double getSetpoint() {
		return SmartDashboard.getNumber("Gear Arm Up Angle", .05);
	}

}
