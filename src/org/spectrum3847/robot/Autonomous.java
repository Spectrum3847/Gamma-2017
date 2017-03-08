package org.spectrum3847.robot;

import org.spectrum3847.robot.commands.AutonScore;
import org.spectrum3847.robot.commands.CurrentStopGearAuto;
import org.spectrum3847.robot.commands.DriveForTime;
import org.spectrum3847.robot.commands.MoveFeet;
import org.spectrum3847.robot.commands.MoveTurnMove;
import org.spectrum3847.robot.commands.TurnPID;
import org.spectrum3847.robot.commands.gear.InitializeGearSensor;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Autonomous {

    public static void init() {
    	//Robot.mecanumCollector.extend();
    	//new AutonScore().start();
    	//new DriveForTime().start();
    	//new MoveFeet(1).start();
    	if(SmartDashboard.getBoolean("Autonomous ENABLED", true)){
    		new CurrentStopGearAuto().start();
    	}
    	System.out.println("Auto Init is working");
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
}
