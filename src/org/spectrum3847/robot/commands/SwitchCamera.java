
package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.subsystems.Cameras;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class SwitchCamera extends Command {

	Cameras c ;
    public SwitchCamera(Cameras cam) {
        // Use requires() here to declare subsystem dependencies
    	c = cam;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	c.intailizeCamera1();
    	Robot.leds.blue();
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
    	c.intailizeCamera0();
    	Robot.leds.purple();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
