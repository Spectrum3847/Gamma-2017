package org.spectrum3847.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
    		SmartDashboard.putString("Drive Equation", "x^2");
    		
    		SmartDashboard.putNumber("Collector Speed", .75);
    		
    		SmartDashboard.putNumber("Belt Intake Speed", .75);
    		
    		SmartDashboard.putNumber("Tower Belt Speed", .75);
    		
    		SmartDashboard.putNumber("Shooter PID Front Speed", 7000);
    		SmartDashboard.putNumber("Shooter PID Back Speed",  7000);
    		
    		SmartDashboard.putNumber("Shooter P_front", 2.000);
    		SmartDashboard.putNumber("Shooter I_front", 0.000);
    		SmartDashboard.putNumber("Shooter D_front", 0.750);
    		SmartDashboard.putNumber("Shooter F_front", 3.000);
    		SmartDashboard.putNumber("Shooter P_back",  2.000);
    		SmartDashboard.putNumber("Shooter I_back",  0.000);
    		SmartDashboard.putNumber("Shooter D_back",  0.750);
    		SmartDashboard.putNumber("Shooter F_back",  3.000);
    		SmartDashboard.putBoolean("Gear Sensor", false);
    		
    		SmartDashboard.putNumber("Gear Arm Current Limit", 30);
    		SmartDashboard.putNumber("Gear Arm Current Limit Low Bound", 25);
    		SmartDashboard.putNumber("Gear Arm Deadband", .1);
    		SmartDashboard.putNumber("Gear Intake Speed", 1);
    		SmartDashboard.putNumber("Gear Outtake Speed", 1);
    		SmartDashboard.putNumber("Gear Arm Ramp Factor", .25);
    		SmartDashboard.putNumber("Gear Arm Down Angle", .71);
    		SmartDashboard.putNumber("Gear Arm Score Angle", 0.2);
    		SmartDashboard.putNumber("Gear Arm ScoreDown Angle", .5);
    		SmartDashboard.putNumber("Gear Arm Up Angle", .03);
  		
    		SmartDashboard.putNumber("Gear Arm kP",  1.8);
    		SmartDashboard.putNumber("Gear Arm kD", 16);
    	}
    }

    private static void updatePutShort() {
    	//SmartDashboard.putNumber("Motor 1", Motor_1.get());
    	//Robot.shooter.updateValuesToDashboard();
    	
    	SmartDashboard.putNumber("Drive LeftY: ", HW.Driver_Gamepad.getY(Hand.kLeft));
    	SmartDashboard.putNumber("Drive RightX: ", HW.Driver_Gamepad.getX(Hand.kRight));
    	SmartDashboard.putNumber("Drive Trigger Left: ", HW.Driver_Gamepad.getTriggerAxis(Hand.kLeft));
    	SmartDashboard.putNumber("Drive Trigger Right: ", HW.Driver_Gamepad.getTriggerAxis(Hand.kRight));
    	SmartDashboard.putNumber("Drive Left:", Robot.leftDrive.get());
    	SmartDashboard.putNumber("Drive Right:", Robot.rightDrive.get());

    	SmartDashboard.putNumber("Front Speed", Robot.shooterFront.getSpeed());
    	SmartDashboard.putNumber("Front Current Setpoint", Robot.shooterFront.getTalon().getSetpoint());
        SmartDashboard.putNumber("Front Error", Robot.shooterFront.getTalon().getError());
    	SmartDashboard.putBoolean("Front on Target", Robot.shooterFront.onTarget());
    	SmartDashboard.putNumber("Front Applied Voltage", Robot.shooterFront.getTalon().getOutputVoltage());
    	SmartDashboard.putNumber("Front Bus Voltage", Robot.shooterFront.getTalon().getBusVoltage());
    	SmartDashboard.putNumber("Front Left Shooter Current", HW.PDP.getCurrent(HW.SHOOTER_MOTOR_FRONT_LEFT_PDP));
    	SmartDashboard.putNumber("Front Right Shooter Current", HW.PDP.getCurrent(HW.SHOOTER_MOTOR_FRONT_RIGHT_PDP));
    	
    	
        SmartDashboard.putNumber("Back Speed", Robot.shooterBack.getSpeed());
    	SmartDashboard.putNumber("Back Current Setpoint", Robot.shooterBack.getTalon().getSetpoint());
    	SmartDashboard.putNumber("Back Error", Robot.shooterBack.getTalon().getError());
    	SmartDashboard.putBoolean("Back on Target", Robot.shooterBack.onTarget());
    	SmartDashboard.putNumber("Back Applied Voltage", Robot.shooterBack.getTalon().getOutputVoltage());
    	SmartDashboard.putNumber("Back Bus Voltage", Robot.shooterBack.getTalon().getBusVoltage());
    	SmartDashboard.putNumber("Back Left Shooter Current", HW.PDP.getCurrent(HW.SHOOTER_MOTOR_BACK_LEFT_PDP));
    	SmartDashboard.putNumber("Back Right Shooter Current", HW.PDP.getCurrent(HW.SHOOTER_MOTOR_BACK_RIGHT_PDP));
    	
    	SmartDashboard.putNumber("Gear Arm Encoder Value", Robot.gearIntake.getArmTalon().getPosition());
		SmartDashboard.putBoolean("Gear Sensor", Robot.gearIntake.getSensorOutput());
		SmartDashboard.putNumber("Gear Arm Motor Voltage", Robot.gearArmMotor.getTalon().getOutputVoltage());
		SmartDashboard.putNumber("Gear Setpoint", Robot.gearIntake.getArmTalon().getSetpoint());
		SmartDashboard.putNumber("Gear Error", Robot.gearIntake.getArmTalon().getError()/4096);
		SmartDashboard.putNumber("Gear Arm Current",  Robot.gearIntake.getArmTalon().getOutputCurrent());
		SmartDashboard.putNumber("Gear Intake Current",  Robot.gearIntake.getIntakeTalon().getOutputCurrent());
		SmartDashboard.putNumber("Gear Intake Current Limit", 12);


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
