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

public class CenterBackpackGearAutoPID extends CommandGroup{

	protected boolean isRight = true;
	protected boolean finishDrive = false;
	private double turnAngle = 60;
	
	public CenterBackpackGearAutoPID(boolean right, boolean finishDrive) {
		super();

		if(isRight){
			turnAngle = 60;
		}
		else{
			turnAngle = -60;
		}
		
		//Robot.navX.reset();
		
		this.addParallel(new ZeroGearArmCurrent());
		this.addSequential(new DriveUntilSpringSensor(), 4);
		this.addParallel(new ScoreGearBackPack());
		this.addSequential(new WaitCommand(.25));
		this.addSequential(new DriveDistance(Robot.prefs.getNumber("A: BP Gear Backup Distance", 36),30));
		if (finishDrive){
			System.out.println("Finishing Drive? " + finishDrive);
			this.addSequential(new InPlaceTurn(-1*turnAngle + Robot.prefs.getNumber("A: BP Turn away addition", 5) * Math.signum(turnAngle), 5));
			this.addSequential(new WaitCommand(Robot.prefs.getNumber("A: BP Wait for Drive", .25)));
			this.addSequential(new DriveDistance(Robot.prefs.getNumber("A: BP Center Gear Intermediate Drive Distance", -120),10));
			this.addSequential(new InPlaceTurn(turnAngle, 5));
			this.addSequential(new DriveDistance(Robot.prefs.getNumber("A: BP Center Gear Final drive Distance", -700),5));
		}
	}
	
	public void initialize(){
		this.isRight = Robot.prefs.getBoolean("A: On Right Side?", true);
		Robot.navX.zeroYaw();
		Robot.leftDrive.getTalon().setPosition(0);
		Robot.drive.talonBrakeMode(true);
	}

}
