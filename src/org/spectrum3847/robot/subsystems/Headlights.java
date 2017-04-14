package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Headlights extends Subsystem{

	private SpectrumSolenoid headlightSol;
	
	public Headlights(SpectrumSolenoid sol) {
		// TODO Auto-generated constructor stub
		headlightSol = sol;
	}

	public void headlightsOn(){
		headlightSol.set(true);
	}
	
	public void headlightsOff(){
		headlightSol.set(false);
	}
	
	public boolean headlightsAreOn(){
		return headlightSol.get();
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
