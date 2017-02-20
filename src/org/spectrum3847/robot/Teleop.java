package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.robot.commands.ArcadeDrive;
import org.spectrum3847.robot.commands.GearArmDrive;
import org.spectrum3847.robot.commands.IntakeOn;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Scheduler;


/**
 * The Driver Control period of the competition
 */
public class Teleop {
	
    public static void init() {
        Scheduler.getInstance().removeAll();
        Robot.compressor.start();
        Robot.compressor.setClosedLoopControl(true);
	
        ArcadeDrive arcadeDrive = new ArcadeDrive();
        arcadeDrive.start();
        
        GearArmDrive gearArmDrive = new GearArmDrive();
        gearArmDrive.start();
        
        IntakeOn beltIntakeOn = new IntakeOn(false);
        beltIntakeOn.start();
        
        //Robot.mecanumCollector.extend();
        
        //SpectrumSolenoid mecanum_sol = new SpectrumSolenoid(HW.MECANUM_RETRACT_SOL);
        //mecanum_sol.set(true);
        
        //Robot.logger.openFile();
    }

    public static void periodic() {
    	System.out.println("Telop Peripdic Function");
    	Dashboard.updateDashboard();
        Scheduler.getInstance().run();
        
        //Tank Drive
        //Robot.drive.setOpenLoop(new DriveSignal(HW.Driver_Gamepad.getLeftY(), HW.Driver_Gamepad.getRightY()));
        
	//Robot.logger.logAll();
    }

    public static void cancel() {
    	System.out.println("Cancel");
        Scheduler.getInstance().removeAll();
    }
}
