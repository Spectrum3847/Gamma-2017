package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command{
	
    public ArcadeDrive() {
        requires(Robot.drive);
    }

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(isNotDeadband(HW.Driver_Gamepad.getY(Hand.kLeft)) || isNotDeadband(HW.Driver_Gamepad.getX(Hand.kRight))){
			Robot.drive.arcadeDrive(HW.Driver_Gamepad.getY(Hand.kLeft), HW.Driver_Gamepad.getX(Hand.kRight), Robot.prefs.getNumber("D: Deadband", .15), Robot.prefs.getBoolean("D: Squared Inputs", true));
		}
		else if(isNotDeadband(HW.Driver_Gamepad.getTriggerAxis(Hand.kLeft)) || isNotDeadband(HW.Driver_Gamepad.getTriggerAxis(Hand.kRight))){
			Robot.drive.setOpenLoop(new DriveSignal(-HW.Driver_Gamepad.getTriggerAxis(Hand.kRight), -HW.Driver_Gamepad.getTriggerAxis(Hand.kLeft)));
		}
		else {
			Robot.drive.setOpenLoop(new DriveSignal(0,0));
		}
	}
	
	protected boolean isNotDeadband(double signal){
		if (Math.abs(signal) > Robot.prefs.getNumber("D: Deadband", .15)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		this.end();
	}
}
