package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterOn extends Command{
	private double p = 0;
	private double i = 0;
	private double d = 0;
	private double f = 0;
	private double speed;
	
	public ShooterOn(){
	
	}
	
	//called before first run
	protected void initialize(){
		Debugger.println("initializing ShooterOn : Setting Shooter PID", Robot.shooter, Debugger.info3);

    	Robot.shooterWheel.getTalon().enableBrakeMode(false);
		Robot.shooterWheel.enable();
		//Robot.shooterBack.enable();
		
		speed = Robot.prefs.getNumber("S: Set Wheel Speed", 8000);

	
		p =   Robot.prefs.getNumber("S: Wheel P",3.6);  
		i =   Robot.prefs.getNumber("S: Wheel I",0);  
		d =   Robot.prefs.getNumber("S: Wheel D",0);  
		f =   Robot.prefs.getNumber("S: Wheel F",10);  
		
		Robot.shooterWheel.setPID(p, i, d, f, 0, 100, 0);
		//Robot.shooterBack.getTalon().setInverted(true);
		//Robot.shooterBack.getTalon().reverseOutput(true);
		//Robot.shooterback.getTalon().reverseSensor(true);
		Robot.shooterWheel.set(speed);
		
		Debugger.println("Wheel PID Setpoint: " +Robot.shooterWheel.getTalon().get() +
							" P: " + p + " D: " + d + " F: " + f + " \n",
							Robot.shooter, Debugger.info3);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Debugger.println("Wheel Speed: " + Robot.shooterWheel.getTalon().get(), Robot.shooter, Debugger.debug2);
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Debugger.println("DISABLING SHOOTER WHEEL", Robot.shooter, Debugger.info3);
		Robot.shooterWheel.disable();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}
}