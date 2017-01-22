package org.spectrum3847.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author matthew, JAG
 */
public class Dashboard {
	
	public static final boolean ENABLE_DASHBOARD = true;
	
	
	static final double SHORT_DELAY = .015;
    static final double LONG_DELAY = 1;
    
    static double shortOldTime = 0.0;
    static double longOldTime = 0.0;


    public static void intializeDashboard() {
    	if(ENABLE_DASHBOARD){
    		//SmartDashboard.putBoolean("Relay", false);
    		//SmartDashboard.putNumber("Motor 1", 0);
        	//Robot.shooter.putCompleteOnDashboard();
    		
    		SmartDashboard.putNumber("Drive Deadband", .1);
    		SmartDashboard.putBoolean("Drive Squared Inputs", false);
    		
    		SmartDashboard.putNumber("Collector Speed", .5);
    		
    		SmartDashboard.putNumber("Belt Intake Speed", .5);
    		
    		SmartDashboard.putNumber("Tower Belt Speed", .5);
    		
    		SmartDashboard.putNumber("Shooter PID Front Speed", 6000);
    		SmartDashboard.putNumber("Shooter PID Back Speed",  6000);
    		
    		SmartDashboard.putNumber("Shooter P_front", 0.042);
    		SmartDashboard.putNumber("Shooter I_front", 0.0);
    		SmartDashboard.putNumber("Shooter D_front", 0.015);
    		SmartDashboard.putNumber("Shooter F_front", 0.142);
    		SmartDashboard.putNumber("Shooter P_back",  0.042);
    		SmartDashboard.putNumber("Shooter I_back",  0.0);
    		SmartDashboard.putNumber("Shooter D_back",  0.015);
    		SmartDashboard.putNumber("Shooter F_back",  0.142);
    	}
    }

    private static void updatePutShort() {
    	//SmartDashboard.putNumber("Motor 1", Motor_1.get());
    	//Robot.shooter.updateValuesToDashboard();
    	
    	SmartDashboard.putNumber("Drive LeftY: ", HW.Driver_Gamepad.getLeftY());
    	SmartDashboard.putNumber("Drive RightX: ", HW.Driver_Gamepad.getRightX());
    	SmartDashboard.putNumber("Drive Trigger Left: ", HW.Driver_Gamepad.getLeftTrigger());
    	SmartDashboard.putNumber("Drive Trigger Right: ", HW.Driver_Gamepad.getRightTrigger());
    	SmartDashboard.putNumber("Drive Left:", Robot.leftDrive.get());
    	SmartDashboard.putNumber("Drive Right:", Robot.rightDrive.get());

    	SmartDashboard.putNumber("Front Speed", Robot.shooterFront.getSpeed());
    	SmartDashboard.putNumber("Front Current Setpoint", Robot.shooterFront.getTalon().getSetpoint());
        SmartDashboard.putNumber("Front Error", Robot.shooterFront.getTalon().getError());
    	SmartDashboard.putBoolean("Front on Target", Robot.shooterFront.onTarget());
    	
        SmartDashboard.putNumber("Back Speed", Robot.shooterBack.getSpeed());
    	SmartDashboard.putNumber("Back Current Setpoint", Robot.shooterBack.getTalon().getSetpoint());
    	SmartDashboard.putNumber("Back Error", Robot.shooterBack.getTalon().getError());
    	SmartDashboard.putBoolean("Back on Target", Robot.shooterBack.onTarget());
    }
    
    private static void updatePutLong(){
    	//SmartDashboard.putBoolean("Compressor", Compressor.enabled());
    }

    
    public static void updateDashboard() {
    	if (ENABLE_DASHBOARD) {
            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                shortOldTime = Timer.getFPGATimestamp();
                updatePutShort();
            }
            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                longOldTime = Timer.getFPGATimestamp();
                updatePutLong();
            }
        }
    }
}
