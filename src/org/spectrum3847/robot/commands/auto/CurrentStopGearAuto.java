package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.gear.DriveUntilGearArmCurrent;
import org.spectrum3847.robot.commands.gear.GearArmPIDPreScore;
import org.spectrum3847.robot.commands.gear.ScoreGear;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CurrentStopGearAuto extends CommandGroup{

	boolean scoreGear = true;
	
	public CurrentStopGearAuto() {
		// TODO Auto-generated constructor stub
		this(true);
	}
	
	public CurrentStopGearAuto(boolean score){
		super();
		scoreGear = score;
		this.addSequential(new ZeroGearArmCurrent());
		this.addParallel(new GearArmPIDPreScore());
		this.addSequential(new DriveForTime(.75,.45));
		this.addSequential(new DriveUntilGearArmCurrent(), 6);
		if(scoreGear){
			this.addParallel(new DriveForTime(Robot.prefs.getNumber("A: Gear Reverse Time", 1), Robot.prefs.getNumber("A: Gear Reverse Throttle",-.3)));
			this.addSequential(new ScoreGear());
		}
	}

}
