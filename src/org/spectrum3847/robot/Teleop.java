package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.robot.commands.ArcadeDrive;
//import org.spectrum3847.robot.commands.GearArmDrive;
import org.spectrum3847.robot.commands.IntakeOn;
import org.spectrum3847.robot.commands.gear.GearArmDrive;
import org.spectrum3847.robot.subsystems.GearIntake;

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
        
        IntakeOn beltIntakeOn = new IntakeOn(false);
        beltIntakeOn.start();
        Robot.gearIntake.getArmTalon().setPosition(0);
        
        //Robot.mecanumCollector.extend();
        
        //SpectrumSolenoid mecanum_sol = new SpectrumSolenoid(HW.MECANUM_RETRACT_SOL);
        //mecanum_sol.set(true);
        
        //Robot.logger.openFile();
    }

    public static void periodic() {
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
