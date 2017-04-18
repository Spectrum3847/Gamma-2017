package org.spectrum3847.robot.commands.auto;

import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.Brake;
import org.spectrum3847.robot.commands.gear.DriveUntilGearArmCurrent;
import org.spectrum3847.robot.commands.gear.GearArmPIDPreScore;
import org.spectrum3847.robot.commands.gear.ScoreGear;
import org.spectrum3847.robot.commands.gear.ScoreGearBackPack;
import org.spectrum3847.robot.commands.gear.ZeroGearArmCurrent;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SideBackpackGearAutoPID extends CommandGroup{

	protected boolean isRight = true;
	protected boolean isClose = false;
	private double distanceFromDS = 0;
	private double turnAngle = 60;
	
	public SideBackpackGearAutoPID(boolean right, boolean close) {
		super();
		
		isRight = right;
		isClose = close;
		
		if(isRight){
			turnAngle = 60;
		}
		else{
			turnAngle = -60;
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
		this.addSequential(new DriveDistance(Robot.prefs.getNumber("A: Side Drive Distance", -68), 6.0));//Robot.prefs.getNumber("A: Distance from DS Corner", 0)),7);
		this.addSequential(new WaitCommand(.5));
		this.addSequential(new InPlaceTurn(turnAngle, 5));
		this.addSequential(new DriveUntilSpringSensor(), 2);
		this.addParallel(new ScoreGearBackPack());
		this.addSequential(new WaitCommand(.25));
		this.addSequential(new DriveDistance(Robot.prefs.getNumber("A: BP Gear Backup Distance", 36),3));
		this.addSequential(new InPlaceTurn(-1*turnAngle + Robot.prefs.getNumber("A: BP Turn away addition", 5) * Math.signum(turnAngle), 5));
		this.addSequential(new WaitCommand(Robot.prefs.getNumber("A: BP Wait for Drive", 1)));
		this.addSequential(new DriveDistance(Robot.prefs.getNumber("A: BP Gear End Drive Distance", -48),10));
	}
	
	public void initialize(){
		this.isRight = SmartDashboard.getBoolean("Auton On Right Side?", true);
	}

}
