package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.subsystems.ShooterWheel;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterOn extends Command{
	private double p_front = 0;
	private double i_front = 0;
	private double d_front = 0;
	private double f_front = 0;
	private double p_back = 0;
	private double i_back = 0;
	private double d_back = 0;
	private double f_back = 0;
	private double front_speed;
	private double back_speed;
	
	
	public void ShooterON(){
		
	}
	
	//called before first run
	protected void initialize(){
		System.out.println("initializing ShooterOn : Setting Shooter PID");
		
		Robot.shooterFront.enable();
		Robot.shooterBack.enable();
		
		front_speed = SmartDashboard.getNumber("Shooter PID Front Speed");
		back_speed = SmartDashboard.getNumber("Shooter PID Back Speed");

	
		p_front =   SmartDashboard.getNumber("Shooter P_tilt",0);  
		i_front =   SmartDashboard.getNumber("Shooter I_tilt",0);  
		d_front =   SmartDashboard.getNumber("Shooter D_tilt",0);  
		f_front =   SmartDashboard.getNumber("Shooter F_tilt",0);  
		p_back =   SmartDashboard.getNumber("Shooter P_flat",0);  
		i_back =   SmartDashboard.getNumber("Shooter I_flat",0);  
		d_back =   SmartDashboard.getNumber("Shooter D_flat",0);  
		f_back =   SmartDashboard.getNumber("Shooter F_flat",0);
		
		Robot.shooterBack.setPID(p_front, i_front, d_front, f_front, 0, 100, 0);
		//Robot.shooterBack.getTalon().setInverted(true);
		//Robot.shooterBack.getTalon().reverseOutput(true);
		//Robot.shooterback.getTalon().reverseSensor(true);
		Robot.shooterBack.set(front_speed);
		
		Robot.shooterBack.setPID(p_back, i_back, d_back, f_back, 0, 100, 0);
		//Robot.shooterFrontFlat.setInverted(true);
		//Robot.shooterBack.getTalon().reverseSensor(true);
		Robot.shooterBack.set(back_speed);

		
		Debugger.println("Front PID Setpoint: " +Robot.shooterFront.getSpeed() +
							"Back PID Setpoint: " + Robot.shooterBack.getSpeed() +
							" P_tilt: " + p_front + " D_tilt: " + d_front + " F_tilt: " + f_front + " \n"+
							" P_flat: " + p_back + " D_flat: " + d_back + " F_flat: " + f_back + " \n",
							Robot.commands, Debugger.warning4);
		
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
		System.out.println("DISABLING SHOOTER WHEELS");
		Robot.shooterFront.disable();
		
		Robot.shooterBack.disable();

		
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}
}