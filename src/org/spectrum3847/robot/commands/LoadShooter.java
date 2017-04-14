package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LoadShooter extends Command{

	
	public LoadShooter(){};
	
	@Override
	protected void initialize() {
		Robot.beltBed.getTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.beltBed.set(Robot.prefs.getNumber("S: Belt Bed Speed",-1));
		Debugger.println("Load Shooter Started", Robot.shooter, Debugger.verbose1);
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.beltBed.set(0);
		Debugger.println("Load Shooter Stopped", Robot.shooter, Debugger.verbose1);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
		
	}

}
