
package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class DriveAtVoltage extends Command {

	private CANTalon talon;
	private double voltage;
    public DriveAtVoltage(CANTalon t) {
        requires(Robot.drive);
        talon = t;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	talon.changeControlMode(TalonControlMode.Voltage);
    	voltage = Robot.prefs.addNumber("D: DriveAtVoltage",  6);
    	talon.set(voltage);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	talon.changeControlMode(TalonControlMode.PercentVbus);
    	talon.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
