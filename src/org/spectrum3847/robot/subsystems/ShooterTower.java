package org.spectrum3847.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterTower extends Subsystem{

	SpectrumSpeedControllerCAN frontRoller;
	SpectrumSpeedControllerCAN backRoller;
	
	SpectrumSpeedController towerMotors;
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
