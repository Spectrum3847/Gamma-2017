package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearSpear extends SolenoidSubsystem {

	public GearSpear(String n, SpectrumSolenoid e, SpectrumSolenoid r) {
		super(n, e, r);
		// TODO Auto-generated constructor stub
	}

	public GearSpear(String n, SpectrumSolenoid e) {
		super(n, e);
		// TODO Auto-generated constructor stub
	}

	public GearSpear(String n, int e, int r) {
		super(n, e, r);
		// TODO Auto-generated constructor stub
	}

	public GearSpear(String n, int e) {
		super(n, e);
		// TODO Auto-generated constructor stub
	}
	
	public void Toggle(){
		if (this.isExtened()){
			retract();
		} else {
			extend();
		}
		Debugger.println("Gear Spear Toggle",Robot.gear, Debugger.info3);
	}

}
