package org.spectrum3847.robot.commands.auto;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.shooter.LoadShooter;
import org.spectrum3847.robot.commands.shooter.ShooterAndTowerOff;
import org.spectrum3847.robot.commands.shooter.ShooterOn;
import org.spectrum3847.robot.commands.shooter.TowerOn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class Fire10BallsAndGear extends CommandGroup {
	
	public Fire10BallsAndGear() {
		super();
		
		this.addParallel(new ShooterOn());
		this.addParallel(new TowerOn());
		this.addSequential(new WaitCommand(1));
		this.addSequential(new LoadShooter(), 2);
		this.addParallel(new ShooterAndTowerOff(),.1);
		this.addSequential(new DriveDistance(20,.5),.5);
		this.addSequential(new SideBackpackGearAutoPID(Robot.prefs.getBoolean("1A: On Right Side", false), false));
	}
	
	protected void initialize(){
		Debugger.println("Fire Balls Started", Robot.auton, Debugger.info3);
	}
	
	protected void end() {
		Debugger.println("Fire Balls Ended", Robot.auton, Debugger.info3);
	}

}
