package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.subsystems.GearIntake;

import edu.wpi.first.wpilibj.command.Command;

public class InitializeGearSensor extends Command {

	boolean reached;
	
	public InitializeGearSensor() {
		requires(Robot.gearIntake);
		requires(Robot.gearIntake);
		reached = false;
	}
	
	public void initialize(){
		Robot.gearIntake.setArmMotor(0.2);
		
	}
	
	public void execute(){		
		
		if(Robot.gearIntake.getSensorOutput() && !reached){
			System.out.println("Reached");
			reached = true;
		}
		
		if(reached && !Robot.gearIntake.getSensorOutput()){
			Robot.gearIntake.setArmMotor(-0.2);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.gearIntake.getSensorOutput() && reached;
	}

	public void end(){
		Robot.gearIntake.setArmMotor(0);
		Robot.gearIntake.setUpPos(Robot.gearIntake.getEncPosition());
	}
	
	public void interrupted(){
		Robot.gearIntake.setArmMotor(0);
	}
}
