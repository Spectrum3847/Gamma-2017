
package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.VibrateController;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeGearUntilCurrent extends Command{

	private double speed;
	
	public IntakeGearUntilCurrent(){
	}
	
	
	@Override
	protected void initialize() {
		speed = Robot.prefs.getNumber("G: Intake Speed", 1);
	}

	@Override
	protected void execute() {
		Robot.gearIntake.setIntake(speed);
	}

	@Override
	protected boolean isFinished() {
		//If output current of the intake motor is higher than the dashboard value end this command
		return Robot.gearIntake.getIntakeTalon().getOutputCurrent() > Robot.prefs.getNumber("G: In Amps Limit", 12);
	}

	@Override
	protected void end() {
		Robot.gearIntake.setIntake(0);
		new VibrateController(HW.Driver_Gamepad, 0.75, 1).start();
		new VibrateController(HW.Operator_Gamepad, 0.75, 1).start();
    	//HW.Driver_Gamepad.setRumble(RumbleType.kRightRumble, .7);
    	//HW.Operator_Gamepad.setRumble(RumbleType.kLeftRumble, .7);
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		Robot.gearIntake.setIntake(0);
	}

}
