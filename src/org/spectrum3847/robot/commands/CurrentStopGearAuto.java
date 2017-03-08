package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.commands.gear.DriveUntilGearArmCurrent;
import org.spectrum3847.robot.commands.gear.GearArmPIDPreScore;
import org.spectrum3847.robot.commands.gear.ScoreGear;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CurrentStopGearAuto extends CommandGroup{

	private double currentTrigger;
	private double throttle;
	
	public CurrentStopGearAuto() {
		// TODO Auto-generated constructor stub
		this("Current Stop Gear Auto");
	}
	
	public CurrentStopGearAuto(String name){
		super(name);
		this.addSequential(new ZeroGearArmCurrent());
		this.addParallel(new GearArmPIDPreScore());
		this.addSequential(new DriveForTime(1,.6));
		this.addSequential(new IntakeOn(true),.25);
		if(SmartDashboard.getBoolean("Auto Score Gear Bool", true)){
			this.addSequential(new DriveUntilGearArmCurrent(), 5);
			this.addParallel(new DriveForTime(SmartDashboard.getNumber("Current Gear Auto Reverse Time", 1), SmartDashboard.getNumber("Current Gear Auto Reverse Throttle",-.4)));
			this.addSequential(new ScoreGear());
		}
	}

}
