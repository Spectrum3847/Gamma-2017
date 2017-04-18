package org.spectrum3847.robot.commands.leds;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Red extends Command {

	public Red() {
		// TODO Auto-generated constructor stub
		requires(Robot.leds);
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.leds.red();
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
    	Robot.leds.purple();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
