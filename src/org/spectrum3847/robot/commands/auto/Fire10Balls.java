package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.shooter.LoadShooter;
import org.spectrum3847.robot.commands.shooter.ShooterAndTowerOff;
import org.spectrum3847.robot.commands.shooter.ShooterOn;
import org.spectrum3847.robot.commands.shooter.TowerOn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Fire10Balls extends CommandGroup{

	private boolean isRed;
	
	public Fire10Balls(boolean red) {
		super();
		isRed = red;
		
		this.addParallel(new ShooterOn());
		this.addParallel(new TowerOn());
		this.addSequential(new WaitCommand(2));
		this.addSequential(new LoadShooter(), 2);
		this.addParallel(new ShooterAndTowerOff(), .1);
		if(isRed)
			this.addSequential(new SteerForTime(.5, -.5, 0), .5);
		else
			this.addSequential(new SteerForTime(.5, 0, -.5), .5);
		this.addSequential(new DriveForTime(1.5, .6));
	}
	
	protected void initialize(){
		Debugger.println("Fire Balls Started", Robot.auton, Debugger.info3);
	}
	
	protected void end() {
		Debugger.println("Fire Balls Ended", Robot.auton, Debugger.info3);
	}

}
