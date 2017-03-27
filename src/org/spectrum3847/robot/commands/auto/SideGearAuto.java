package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.Brake;
import org.spectrum3847.robot.commands.gear.DriveUntilGearArmCurrent;
import org.spectrum3847.robot.commands.gear.GearArmPIDPreScore;
import org.spectrum3847.robot.commands.gear.ScoreGear;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SideGearAuto extends CommandGroup{

	protected boolean isRight = true;
	protected boolean isClose = false;
	private double distanceFromDS = 0;
	private double turnAngle = 60;
	
	public SideGearAuto(boolean right, boolean close) {
		super();
		
		isRight = right;
		isClose = close;
		
		if(isRight){
			turnAngle = -60;
		}
		else{
			turnAngle = 60;
		}
		
		if(this.isClose)
			distanceFromDS = 41;
		else
			distanceFromDS = 0;
		
		//Robot.navX.reset();
		Robot.navX.zeroYaw();
		Robot.leftDrive.getTalon().setPosition(0);
		Robot.drive.talonBrakeMode(true);
		
		this.addParallel(new ZeroGearArmCurrent());
		this.addParallel(new GearArmPIDPreScore());
		this.addSequential(new DriveToGearLine(distanceFromDS), 7);//Robot.prefs.getNumber("A: Distance from DS Corner", 0)),7);
		this.addSequential(new Brake(), .5);
		Robot.drive.talonBrakeMode(true);
		this.addSequential(new InPlaceTurn(turnAngle));
		Robot.drive.talonBrakeMode(false);
		this.addSequential(new DriveUntilGearArmCurrent(), 6);
		this.addParallel(new DriveForTime(Robot.prefs.getNumber("A: Gear Reverse Time", 1), Robot.prefs.getNumber("A: Gear Reverse Throttle",-.3)));
		this.addSequential(new ScoreGear());
	}
	
	public void initialize(){
		this.isRight = SmartDashboard.getBoolean("Auton On Right Side?", true);
	}

}
