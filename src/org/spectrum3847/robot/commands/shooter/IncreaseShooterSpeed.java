
package org.spectrum3847.robot.commands.shooter;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class IncreaseShooterSpeed extends Command {
	double increment = 0;
	
    public IncreaseShooterSpeed() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.shooterWheel.isEnabled()){    	
    		increment = Robot.prefs.getNumber("S: Adjustment RPM", 50);
        	Robot.shooterWheel.adjustSpeed(increment);
        	Robot.shooterWheel.updateSpeed();
    	}
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
