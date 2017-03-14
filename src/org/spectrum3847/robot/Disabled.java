package org.spectrum3847.robot;

import org.spectrum3847.robot.commands.VibrateController;
import org.spectrum3847.robot.commands.leds.Purple;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author matthew
 */
public class Disabled {

    static int t = 0;
    static boolean b = true;

    public static void init() {
        Scheduler.getInstance().removeAll();
        //Init.sendCam.start();
        Robot.logger.close();
        new VibrateController(HW.Driver_Gamepad, .5, 2);
        new Purple().start();
    }

    //Periodic method called roughly once every 20ms
    public static void periodic() {
        //Flash a light on the dashboard while disabled, know that the dashboard is refreshing.
        if (t > 20) {
            t = 0;
            b = !b;
            SmartDashboard.putBoolean("Disabled Toggle", b);
        }
        t++;
        Scheduler.getInstance().run();
        
        Dashboard.updateDashboard();
        Timer.delay(0.001);
    }
}
