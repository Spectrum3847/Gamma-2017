package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;

public class InPlaceTurn extends PIDCommand{

	private double targetAngle;
	private static double pGain = Robot.prefs.getNumber("A: Turn in Place P", 0.09);
	private static double iGain = Robot.prefs.getNumber("A: Turn in Place I", 0.00);
	private static double dGain = Robot.prefs.getNumber("A: Turn in Place D", 0.25);
	
	
	public InPlaceTurn(double targetAngle) {
		super(pGain, iGain, dGain);
		requires(Robot.drive);
		
		this.targetAngle = targetAngle;
	}

	public void initialize(){
		
		this.setTimeout(1.5);
		
		Robot.leftDrive.getTalon().changeControlMode(CANTalon.TalonControlMode.Voltage);
		Robot.rightDrive.getTalon().changeControlMode(CANTalon.TalonControlMode.Voltage);
		
		Robot.leftDrive.getTalon().configPeakOutputVoltage(-6f, 6f);
		Robot.rightDrive.getTalon().configPeakOutputVoltage(-6f, 6f);
		
		super.getPIDController().enable();
		this.setSetpoint(this.targetAngle);
		
	}
	
	public void execute(){
		//System.out.println("[InPlaceTurn] Setpoint: " + this.getSetpoint() + " Position: " + this.getPosition()  + " Error: " + (this.getSetpoint() - this.getPosition()));
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(isTimedOut()){//Math.abs(Robot.navX.getAngle() - this.targetAngle) < 1 || isTimedOut()){
			System.out.print("Done Turning in Auto");
			return true;
		}
		return false;
	}

	public void end(){
		Robot.leftDrive.getTalon().changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		Robot.rightDrive.getTalon().changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
		Robot.leftDrive.getTalon().configPeakOutputVoltage(+12f, -12f);
	}
	
	public void interrupted(){
		this.end();
	}
	
	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.navX.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
		
		Robot.leftDrive.getTalon().set(output * -6);
		Robot.rightDrive.getTalon().set(output * 6);
		System.out.println("TurnInPlace Right Setpoint" + Robot.rightDrive.getTalon().get());
	}

}
