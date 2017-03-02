package org.spectrum3847.robot;

import org.spectrum3847.robot.commands.AutonScore;
import org.spectrum3847.robot.commands.MoveTurnMove;
import org.spectrum3847.robot.commands.TurnPID;
import org.spectrum3847.robot.commands.gear.InitializeGearSensor;

import edu.wpi.first.wpilibj.command.Scheduler;


public class Autonomous {

    public static void init() {
    	Robot.mecanumCollector.extend();
    	new InitializeGearSensor().start();
    	new AutonScore().start();
    }

    //Periodic method called roughly once every 20ms
    public static void periodic() {
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
}
