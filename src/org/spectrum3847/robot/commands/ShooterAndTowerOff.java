package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterAndTowerOff extends Command{
	private double p = 0;
	private double i = 0;
	private double d = 0;
	private double f = 0;
	private double speed;
	
	public ShooterAndTowerOff(){
		this.requires(Robot.shooterTower);
		this.requires(Robot.shooterWheel);
	
	}
	
	//called before first run
	protected void initialize(){
		Debugger.println("Turning Shooter and Tower Off", Robot.shooter, Debugger.info3);

		Robot.shooterTower.disable();
    	Robot.shooterTower.getTalon().enableBrakeMode(true);
		Robot.shooterWheel.disable();
    	Robot.shooterWheel.getTalon().enableBrakeMode(true);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Debugger.println("DISABLING SHOOTER WHEEL + TOWER", Robot.shooter, Debugger.info3);
		Robot.shooterTower.disable();
		Robot.shooterWheel.disable();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}
}