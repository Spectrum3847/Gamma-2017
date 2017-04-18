package org.spectrum3847.robot.commands.shooter;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.leds.Green;
import org.spectrum3847.robot.commands.leds.Purple;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TowerOn extends Command{
	private double p = 0;
	private double i = 0;
	private double d = 0;
	private double f = 0;
	private double speed;
	
	public TowerOn(){
	
	}
	
	//called before first run
	protected void initialize(){
		Debugger.println("initializing TowerOn : Setting Tower PID", Robot.shooter, Debugger.info3);

    	Robot.shooterTower.getTalon().enableBrakeMode(false);
    	Robot.shooterTower.getTalon().changeControlMode(TalonControlMode.Speed);
		Robot.shooterTower.enable();
		
		speed = Robot.prefs.getNumber("S: Set Tower Speed", 12.2);

	
		p =   Robot.prefs.getNumber("S: Tower P", 3.6);  
		i =   Robot.prefs.getNumber("S: Tower I", 0);  
		d =   Robot.prefs.getNumber("S: Tower D", 0);  
		f =   Robot.prefs.getNumber("S: Tower F", 10);  
		
		Robot.shooterTower.setPID(p, i, d, f, 0, 100, 0);

		Robot.shooterTower.set(speed);
		
		Debugger.println("Tower PID Setpoint: " +Robot.shooterTower.getTalon().get() +
							" P: " + p + " D_front: " + d + " F_front: " + f + " \n",
							Robot.shooter, Debugger.info3);

		
	}

	@Override
	protected void execute() {
		Debugger.println("Tower Speed: " + Robot.shooterTower.getTalon().get(), Robot.shooter, Debugger.debug2);		
		new Green().start();
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Debugger.println("DISABLING SHOOTER TOWER", Robot.shooter, Debugger.info3);
		Robot.shooterTower.enableBrakeMode(true);
		new Purple().start();
		Robot.shooterTower.disable();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}
}