package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

public class ArcTurn extends Command{

	private Boolean isRight;
	private Boolean isBoth;
	
	public ArcTurn(Side side) {
		// TODO Auto-generated constructor stub
		isRight = side.value;
	}
	
	public ArcTurn(){
		isBoth = true;
	}

	public enum Side{
		Right(true), Left(false);
		
		public final Boolean value;
		
		private Side(Boolean right){
			this.value = right;
		}
	}
	
	public void execute(){
		if(this.isBoth){
			Robot.drive.setOpenLoop(new DriveSignal(HW.Driver_Gamepad.getTriggerAxis(GenericHID.Hand.kRight),HW.Driver_Gamepad.getTriggerAxis(GenericHID.Hand.kLeft)));
		}
		/*	
		else if(this.isRight){
			Robot.drive.setOpenLoop(new DriveSignal(HW.Driver_Gamepad.getTriggerAxis(GenericHID.Hand.kRight),0));
		}
		else if (!this.isRight){
			Robot.drive.setOpenLoop(new DriveSignal(0,HW.Driver_Gamepad.getTriggerAxis(GenericHID.Hand.kLeft)));
		}
		*/
	}
	
	public void end(){
		Robot.drive.setOpenLoop(new DriveSignal(0,0));
	}
	
	public void interrupted(){
		this.end();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
