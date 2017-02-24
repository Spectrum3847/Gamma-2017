package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearArmPIDDown extends GearArmPIDCommand {

	public GearArmPIDDown() {
		super();
	}
	
	double getSetpoint() {
		return SmartDashboard.getNumber("Gear Arm Down Angle", .7);
	}
	
/*	 // Called once after isFinished returns true
    protected void end() {
    	new GearArmPIDUp().start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
*/

}
