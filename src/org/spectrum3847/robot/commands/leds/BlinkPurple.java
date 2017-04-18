package org.spectrum3847.robot.commands.leds;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class BlinkPurple extends Command {

	double time = 0;
	double spd = 0;
	public BlinkPurple(double speed) {
		// TODO Auto-generated constructor stub
		spd = speed;
		requires(Robot.leds);
	}

	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.leds.purple();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time = Timer.getFPGATimestamp();
    	boolean blink = (time % (spd * 2)) < spd;
    	if (blink){
    		Robot.leds.purple();
    	} else{
    		Robot.leds.off();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leds.off();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
