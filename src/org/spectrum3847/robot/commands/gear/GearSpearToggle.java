package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearSpearToggle extends Command {

	public GearSpearToggle() {
		// TODO Auto-generated constructor stub
	}


	public GearSpearToggle(double timeout) {
		super(timeout);
		// TODO Auto-generated constructor stub
	}

	public GearSpearToggle(String name, double timeout) {
		super(name, timeout);
		// TODO Auto-generated constructor stub
	}
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearSpear.Toggle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
