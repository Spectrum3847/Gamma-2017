package org.spectrum3847.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand() {
		// TODO Auto-generated constructor stub
		if(SmartDashboard.getBoolean("Autonomous ENABLED", true)){
    		if(SmartDashboard.getBoolean("Fire Balls", false)){
    			this.addSequential(new Fire10Balls());
    		} else
    		{    		
    			this.addSequential(new CurrentStopGearAuto());
    		}
    	}
	}

	public AutonomousCommand(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	

}
