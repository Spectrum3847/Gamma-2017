package org.spectrum3847.robot;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.commands.TurnPID;
import org.spectrum3847.robot.commands.auto.AutonScore;
import org.spectrum3847.robot.commands.auto.AutonomousCommand;
import org.spectrum3847.robot.commands.auto.CurrentStopGearAuto;
import org.spectrum3847.robot.commands.auto.DriveForTime;
import org.spectrum3847.robot.commands.auto.Fire10Balls;
import org.spectrum3847.robot.commands.auto.MoveFeet;
import org.spectrum3847.robot.commands.auto.MoveTurnMove;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Autonomous {
	@SuppressWarnings("rawtypes")
	public static SendableChooser autonChooser;
	static Command AutonCommand;

    public static void init() {
    	AutonCommand = (Command) autonChooser.getSelected();
    	if (SmartDashboard.getBoolean("Autonomous ENABLED", true)){
    			AutonCommand.start();
    	}
    	Debugger.println("Auto Init is working", Robot.auton, Debugger.info3);
    }

    //Periodic method called roughly once every 20ms
    public static void periodic() {
    	//System.out.println("Auto is working");
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createChooser(){
    	autonChooser = new SendableChooser();
    	autonChooser.addDefault("Score Gear", new CurrentStopGearAuto());
    	autonChooser.addObject("Drive Straight", new CurrentStopGearAuto(false));
    	autonChooser.addObject("Fire 10 First", new Fire10Balls());
    	SmartDashboard.putData("Auton Choose", autonChooser);
    }
}
