package org.spectrum3847.robot;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.commands.TurnPID;
import org.spectrum3847.robot.commands.auto.AutonScore;
import org.spectrum3847.robot.commands.auto.AutonomousCommand;
import org.spectrum3847.robot.commands.auto.CurrentStopGearAuto;
import org.spectrum3847.robot.commands.auto.DriveDistance;
import org.spectrum3847.robot.commands.auto.DriveForTime;
import org.spectrum3847.robot.commands.auto.Fire10Balls;
import org.spectrum3847.robot.commands.auto.Fire10BallsAndGear;
import org.spectrum3847.robot.commands.auto.DriveToGearLine;
import org.spectrum3847.robot.commands.auto.MoveTurnMove;
import org.spectrum3847.robot.commands.auto.SideGearAuto;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Autonomous {
	@SuppressWarnings("rawtypes")
	public static SendableChooser autonChooser;
	static Command AutonCommand;

    public static void init() {
    	AutonCommand = new DriveDistance(10);//(Command) autonChooser.getSelected();
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
    	
    	
    	autonChooser.addObject("Score Gear", new CurrentStopGearAuto());
    	autonChooser.addDefault("Drive Distance", new DriveDistance(10));
    	autonChooser.addObject("Drive Straight", new CurrentStopGearAuto(false));
    	autonChooser.addObject("Fire 10 then Gear RED", new Fire10BallsAndGear(true));
    	autonChooser.addObject("Fire 10 then Gear BLUE", new Fire10BallsAndGear(false));
    	autonChooser.addObject("Fire 10 ONLY Red", new Fire10Balls(true));
    	autonChooser.addObject("Fire 10 ONLY Blue", new Fire10Balls(false));
    	
    	autonChooser.addObject("Side Gear Right Far", new SideGearAuto(true, false));
    	autonChooser.addObject("Side Gear Right Close", new SideGearAuto(true, true));
    	autonChooser.addObject("Side Gear Left Far", new SideGearAuto(false, false));
    	autonChooser.addObject("Side Gear Left Close", new SideGearAuto(false, true));
    	SmartDashboard.putData("AutonChooser", new SendableChooser());
    	SmartDashboard.putData("AutonChooser", autonChooser);
    }
}
