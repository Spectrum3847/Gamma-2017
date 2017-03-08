package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ZeroGearArmCurrent extends Command{

	public ZeroGearArmCurrent(){
		requires(Robot.gearIntake);
	}
	
	@Override
	protected void initialize() {
		Robot.gearIntake.getArmTalon().changeControlMode(TalonControlMode.Voltage);
		Robot.gearIntake.printDebug("Arm Talon Zero gear Arm command Intialized");
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		Robot.gearIntake.setArmMotor(-2.5);
		Robot.gearIntake.printDebug("Current motor: " + Robot.gearIntake.getArmTalon().get());
		Robot.gearIntake.printDebug("Arm Talon Zero gear Arm command running");
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Math.abs(Robot.gearIntake.getArmTalon().getOutputCurrent()) > 1.75;
	}

	@Override
	protected void end() {
		Robot.gearIntake.getArmTalon().changeControlMode(TalonControlMode.Position);
		Robot.gearIntake.getArmTalon().setPosition(-0.08);
		Robot.gearIntake.getArmTalon().changeControlMode(TalonControlMode.PercentVbus);
		Robot.gearIntake.setArmMotor(0);
	}

	@Override
	protected void interrupted() {
		Robot.gearIntake.setArmMotor(0);
	}

}
