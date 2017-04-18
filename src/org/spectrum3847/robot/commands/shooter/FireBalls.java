package org.spectrum3847.robot.commands.shooter;

import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.commands.VibrateController;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FireBalls extends CommandGroup{
	
	public FireBalls() {

		//Robot.navX.reset();
		
		this.addSequential(new WaitForShooterSpeed()); //Wait for shooter speed but timeout if it's not up to speed
		this.addParallel(new VibrateController(HW.Operator_Gamepad, 0, .2));
		this.addSequential(new LoadShooter());
	}
	
	public void initialize(){
		
	}

}
