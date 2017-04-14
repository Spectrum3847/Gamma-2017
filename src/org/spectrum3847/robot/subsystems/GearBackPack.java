package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GearBackPack extends Subsystem{

	private SpectrumSolenoid gearSol;
	private SpectrumSolenoid gearFlapSol;
	private SpectrumSolenoid ballFlapSol;
	
	public GearBackPack(SpectrumSolenoid g, SpectrumSolenoid gF, SpectrumSolenoid bF) {
		gearSol = g;
		gearFlapSol = gF;
		ballFlapSol = bF;
	}

	public void gearSolExtend(){
		gearSol.set(true);
	}
	
	public void gearSolRetract(){
		gearSol.set(false);
	}
	
	public void gearFlapExtend(){
		gearFlapSol.set(true);
	}
	
	public void gearFlapRetract(){
		gearFlapSol.set(false);
	}
	
	public void ballFlapExtend(){
		ballFlapSol.set(false);
	}
	
	public void ballFlapRetract(){
		ballFlapSol.set(true);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
