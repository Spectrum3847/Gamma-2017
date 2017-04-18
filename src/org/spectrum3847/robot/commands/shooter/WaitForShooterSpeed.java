
package org.spectrum3847.robot.commands.shooter;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/*
 *
 */
public class WaitForShooterSpeed extends Command {

    public WaitForShooterSpeed() {
        // Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 Debugger.println("Shooter SetPoint Wheel: " + Robot.shooterWheel.getTalon().getSetpoint() + " Tower: " + Robot.shooterTower.getTalon().getSetpoint(), Robot.shooter, Debugger.info3);
         setTimeout(Robot.prefs.getNumber("S: Speed Timeout", 1.5));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || Robot.shooterWheel.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Debugger.println("Shooter On Target Error:" + Robot.shooterWheel.getError() + " Wheel: " + Robot.shooterWheel.getSpeed() + " Tower: " + Robot.shooterTower.getSpeed(), Robot.shooter, Debugger.info3);
    	Debugger.println("Shooter On Target FINISHED", Robot.shooter, Debugger.info3);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Debugger.println("Shooter On Target Error:" + Robot.shooterWheel.getError() + " Wheel: " + Robot.shooterWheel.getSpeed() + " Tower: " + Robot.shooterTower.getSpeed(), Robot.shooter, Debugger.info3);
    	Debugger.println("Shooter On Target Interrupted", Robot.shooter, Debugger.info3);

    }
}
