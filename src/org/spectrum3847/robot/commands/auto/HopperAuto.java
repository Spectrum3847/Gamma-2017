package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;
import org.spectrum3847.robot.commands.shooter.LoadShooter;
import org.spectrum3847.robot.commands.shooter.ShooterOn;
import org.spectrum3847.robot.commands.shooter.TowerOn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HopperAuto extends CommandGroup{

	protected boolean isRight = true;
	protected double turnAngle = 90;
	
	public HopperAuto(boolean right) {
		super();
		this.requires(Robot.drive);

		isRight = right;
		
		if(isRight){
			turnAngle = -90;
		}
		else{
			turnAngle = 90;
		}
		
		this.addSequential(new DriveDistance(Robot.prefs.getNumber("A: Drive To Hopper Distance", -60),4));
		this.addParallel(new ZeroGearArmCurrent());
		this.addParallel(new LowerBallFlap());
		this.addSequential(new InPlaceTurn(turnAngle + Robot.prefs.getNumber("A: Hopper Turn away addition", 0), 5));// * Math.signum(turnAngle), 5));
		this.addSequential(new DriveUntilWall(), 4);
		this.addParallel(new ShooterOn()); //spin up shooter while loading balls
		this.addSequential(new WaitCommand(2.5));
		this.addSequential(new InPlaceTurn(-6 * Math.signum(turnAngle), false, 1));
		this.addParallel(new TowerOn()); //spin up tower while turning robot
		this.addParallel(new LoadShooter()); // start firing balls
		this.addSequential(new WaitCommand(3));
		this.addParallel(new RaiseBallFlap());
		this.addSequential(new WaitCommand(.5));
		this.addParallel(new LowerBallFlap());
		this.addSequential(new WaitCommand(2));
		this.addParallel(new RaiseBallFlap());
		this.addSequential(new WaitCommand(1));
		this.addParallel(new LowerBallFlap());
		this.addSequential(new WaitCommand(1));
		this.addParallel(new RaiseBallFlap());
		this.addSequential(new WaitCommand(1));
		this.addParallel(new LowerBallFlap());
		this.addSequential(new WaitCommand(1));
		this.addParallel(new RaiseBallFlap());
		this.addSequential(new WaitCommand(1));
		this.addParallel(new LowerBallFlap());
		
		
	}

}
