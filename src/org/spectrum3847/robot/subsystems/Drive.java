package org.spectrum3847.robot.subsystems;


import java.math.BigDecimal;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.trajectory.Path;
import org.spectrum3847.lib.util.Pose;
import org.spectrum3847.lib.util.StateHolder;
import org.spectrum3847.lib.util.Util;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Expression;
import org.spectrum3847.robot.Constants;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.commands.ArcadeDrive;
import org.spectrum3847.robot.subsystems.controllers.DriveFinishLineController;
import org.spectrum3847.robot.subsystems.controllers.DrivePathController;
import org.spectrum3847.robot.subsystems.controllers.DriveStraightController;
import org.spectrum3847.robot.subsystems.controllers.TurnInPlaceController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {

    public interface DriveController {
        DriveSignal update(Pose pose);
        Pose getCurrentSetpoint();
        public boolean onTarget();

    }
    
    
    public SpectrumSpeedControllerCAN m_left_motor;
    public SpectrumSpeedControllerCAN m_right_motor;
    public Encoder m_left_encoder;
    public Encoder m_right_encoder;
    //public GyroThread m_gyro; Will Replace with NavX
    private DriveController m_controller = null;
    private SpectrumSolenoid brakes;

    protected final double m_inches_per_tick = Constants.kDriveWheelSizeInches
            * Math.PI / Constants.kDriveEncoderCountsPerRev;
    protected final double m_wheelbase_width = 26.0; // Get from CAD
    protected final double m_turn_slip_factor = 1.2; // Measure empirically
    private Pose m_cached_pose = new Pose(0, 0, 0, 0, 0, 0); // Don't allocate poses at 200Hz!

    double forwardThrottle = 0;
	//double kPgain = 0.04; /* percent throttle per degree of error */
	//double kDgain = 0.0004; /* percent throttle per angular velocity dps */
	double kMaxCorrectionRatio = 0.30; /* cap corrective turning throttle to 30 percent of forward throttle */
	/** holds the current angle to servo to */
	double targetAngle;
	double turnThrottle;
	
	double currentAngle;
	double currentAngularRate;
	double sidePIDThrottle;
	double driveStraightPrintLoops = 0;
    
    //Unused methods - would need to be updated to use CANTalon, but not needed for current state of the practice robot.
    /*
    public Drive(String name, SpectrumSpeedController left_drive,
    			SpectrumSpeedController right_drive, Encoder left_encoder,
                 Encoder right_encoder) {
        super(name);
        this.m_left_motor = left_drive;
        this.m_right_motor = right_drive;
        this.m_left_encoder = left_encoder;
        this.m_right_encoder = right_encoder;
        this.m_left_encoder.setDistancePerPulse(m_inches_per_tick);
        this.m_right_encoder.setDistancePerPulse(m_inches_per_tick);
        //this.m_gyro = gyro;
    }
    
    public Drive(String name, int left_drive, int left_drive_PDP,
    			int right_drive, int right_drive_PDP, Encoder left_encoder,
                 Encoder right_encoder){
    	this(name, new SpectrumSpeedController(new Talon(left_drive), left_drive_PDP), 
    			new SpectrumSpeedController(new Talon(right_drive), right_drive_PDP), 
    			left_encoder, right_encoder);
    }
    */
    public Drive(String name, SpectrumSpeedControllerCAN left_drive, SpectrumSpeedControllerCAN right_drive, SpectrumSolenoid breakSol){
    	this.m_left_motor = left_drive;
    	this.m_right_motor = right_drive;
    	brakes= breakSol;

    }

    public void setOpenLoop(DriveSignal signal) {
        m_controller = null;
        setDriveOutputs(signal);
    }

    public void arcadeDrive(double throttle, double turnPower, double deadband, boolean squaredInputs){
    	double leftMotorSpeed;
        double rightMotorSpeed;
        
        throttle = Util.limit(throttle, 1);
        turnPower = Util.limit(turnPower, 1);
        
        if (Math.abs(throttle) < deadband){
      	  throttle = 0;
        } else if (throttle < 0){
            throttle = (throttle + deadband) / (1-deadband);
        } else {
        	throttle = (throttle - deadband) / (1-deadband);
        }
        
        if (Math.abs(turnPower) < deadband){
      	  turnPower = 0;
        }
        /*} else if (turnPower < 0){
        	turnPower = (turnPower + deadband) / (1-deadband);
        } else {
        	turnPower = (turnPower - deadband) / (1-deadband);
        }*/
        
        
        if (squaredInputs) {
          // square the inputs (while preserving the sign) to increase fine control
          // while permitting full power
          /*if (throttle >= 0.0) {
            throttle = (throttle * throttle);
          } else {
            throttle = -(throttle * throttle);
          }
          */
          if (turnPower >= 0.0) {
            turnPower = (turnPower * turnPower);
          } else {
            turnPower = -(turnPower * turnPower);
          }
        }
        
        //Positive Turn Power turns left
        if (throttle > 0.0) {
            if (turnPower > 0.0) {
              leftMotorSpeed = throttle - turnPower;
              rightMotorSpeed = Math.max(throttle, turnPower);
            } else {
              leftMotorSpeed = Math.max(throttle, -turnPower);
              rightMotorSpeed = throttle + turnPower;
            }
          } else {
            if (turnPower > 0.0) {
              leftMotorSpeed = -Math.max(-throttle, turnPower);
              rightMotorSpeed = throttle + turnPower;
            } else {
              leftMotorSpeed = throttle - turnPower;
              rightMotorSpeed = -Math.max(-throttle, -turnPower);
            }
          }
        
        
          this.setOpenLoop(new DriveSignal(leftMotorSpeed, rightMotorSpeed));
    	
          //System.out.println("ARCADE DRIVE");
          
    }
    
    public void stopDrive(){
    	this.setOpenLoop(new DriveSignal(0,0));
    }
    
    public void setDistanceSetpoint(double distance) {
        setDistanceSetpoint(distance, Constants.kDriveMaxSpeedInchesPerSec);
    }

    public void setDistanceSetpoint(double distance, double velocity) {
        // 0 < vel < max_vel
        double vel_to_use = Math.min(Constants.kDriveMaxSpeedInchesPerSec, Math.max(velocity, 0));
        m_controller = new DriveStraightController(
                getPoseToContinueFrom(false),
                distance,
                vel_to_use);
    }

    public void setTurnSetPoint(double heading) {
        setTurnSetPoint(heading, Constants.kTurnMaxSpeedRadsPerSec);
    }

    public void setTurnSetPoint(double heading, double velocity) {
        velocity = Math.min(Constants.kTurnMaxSpeedRadsPerSec, Math.max(velocity, 0));
        m_controller = new TurnInPlaceController(getPoseToContinueFrom(true), heading, velocity);
    }

    public void reset() {
        m_left_encoder.reset();
        m_right_encoder.reset();
    }

    public void setPathSetpoint(Path path) {
        reset();
        m_controller = new DrivePathController(path);
    }

    public void setFinishLineSetpoint(double distance, double heading) {
        reset();
        m_controller = new DriveFinishLineController(distance, heading, 1.0);
    }

    public void getState(StateHolder states) {
        //states.put("gyro_angle", m_gyro.getAngle());
        states.put("left_encoder", m_left_encoder.getDistance());
        states.put("left_encoder_rate", m_left_encoder.getRate());
        states.put("right_encoder_rate", m_right_encoder.getRate());
        states.put("right_encoder", m_right_encoder.getDistance());

        Pose setPointPose = m_controller == null
                ? getPhysicalPose()
                : m_controller.getCurrentSetpoint();
        states.put(
                "drive_set_point_pos",
                DriveStraightController.encoderDistance(setPointPose));
        states.put("turn_set_point_pos", setPointPose.getHeading());
        states.put("left_signal", m_left_motor.get());
        states.put("right_signal", m_right_motor.get());
        states.put("on_target", (m_controller != null && m_controller.onTarget()) ? 1.0 : 0.0);
    }

    public void update() {
        if (m_controller == null) {
            return;
        }
        setDriveOutputs(m_controller.update(getPhysicalPose()));
    }

    private void setDriveOutputs(DriveSignal signal) {
    	/*
    	boolean leftNegativeFlag;
        boolean rightNegativeFlag;
        
        if (signal.leftMotor < 0)
        	leftNegativeFlag = true;
        else
        	leftNegativeFlag = false;
        
        if (signal.rightMotor < 0)
        	rightNegativeFlag = true;
        else
        	rightNegativeFlag = false;
        
        
        signal.leftMotor =  ( new Expression(SmartDashboard.getString("Drive Equation", "x")).with("x",new BigDecimal(signal.leftMotor)).eval() ).doubleValue();
        signal.rightMotor = ( new Expression(SmartDashboard.getString("Drive Equation", "x")).with("x",new BigDecimal(signal.rightMotor)).eval() ).doubleValue();
        
        if(! (leftNegativeFlag ^ (signal.leftMotor < 0)))
        	signal.leftMotor *= -1;
        
        if(! (rightNegativeFlag ^ (signal.rightMotor < 0)))
        	signal.rightMotor *= -1;
        */
    	
    	m_left_motor.set(signal.leftMotor);
        m_right_motor.set(-signal.rightMotor);
    }

    private Pose getPoseToContinueFrom(boolean for_turn_controller) {
        if (!for_turn_controller && m_controller instanceof TurnInPlaceController) {
            Pose pose_to_use = getPhysicalPose();
            pose_to_use.m_heading = ((TurnInPlaceController) m_controller).getHeadingGoal();
            pose_to_use.m_heading_velocity = 0;
            return pose_to_use;
        } else if (m_controller == null || (m_controller instanceof DriveStraightController && for_turn_controller)) {
            return getPhysicalPose();
        } else if (m_controller instanceof DriveFinishLineController) {
            return getPhysicalPose();
        } else if (m_controller.onTarget()) {
            return m_controller.getCurrentSetpoint();
        } else {
            return getPhysicalPose();
        }
    }

    /**
     * @return The pose according to the current sensor state
     */
    public Pose getPhysicalPose() {
        m_cached_pose.reset(
                m_left_encoder.getDistance(),
                m_right_encoder.getDistance(),
                m_left_encoder.getRate(),
                m_right_encoder.getRate(),
                0,0); //m_gyro.getAngle(),
                //m_gyro.getRate());
        return m_cached_pose;
    }

    public Drive.DriveController getController() {
        return m_controller;
    }
    
    public void extendBrakes(){
    	brakes.set(true);
    	System.out.println("EXTEND");
    }
    
    public void retractBrakes(){
    	System.out.println("RETRACT");
    	brakes.set(false);
    }

    public void reloadConstants() {
        // TODO Auto-generated method stub

    }

    public boolean controllerOnTarget() {
        return m_controller != null && m_controller.onTarget();
    }

	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new ArcadeDrive());	
	}

	public void talonBrakeMode(boolean brakeMode) {
		if(brakeMode){
			Robot.left_drive_talon_1.enableBrakeMode(true);
			Robot.left_drive_talon_2.enableBrakeMode(true);
			Robot.left_drive_talon_3.enableBrakeMode(true);
			Robot.right_drive_talon_1.enableBrakeMode(true);
			Robot.right_drive_talon_2.enableBrakeMode(true);
			Robot.right_drive_talon_3.enableBrakeMode(true);
		}
		else{
			Robot.left_drive_talon_1.enableBrakeMode(false);
			Robot.left_drive_talon_2.enableBrakeMode(false);
			Robot.left_drive_talon_3.enableBrakeMode(false);
			Robot.right_drive_talon_1.enableBrakeMode(false);
			Robot.right_drive_talon_2.enableBrakeMode(false);
			Robot.right_drive_talon_3.enableBrakeMode(false);
		}
		
	}
	
	/** @param value to cap.
	 * @param peak positive double representing the maximum (peak) value.
	 * @return a capped value.
	 */
	public double Cap(double value, double peak) {
		if (value < -peak)
			return -peak;
		if (value > +peak)
			return +peak;
		return value;
	}

	/**
	 * Given the robot forward throttle and ratio, return the max
	 * corrective turning throttle to adjust for heading.  This is
	 * a simple method of avoiding using different gains for
	 * low speed, high speed, and no-speed (zero turns).
	 */
	public double MaxCorrection(double forwardThrot, double scalor) {
		/* make it positive */
		if(forwardThrot < 0) {forwardThrot = -forwardThrot;}
		/* max correction is the current forward throttle scaled down */
		forwardThrot *= scalor;
		/* ensure caller is allowed at least 10% throttle,
		 * regardless of forward throttle */
		if(forwardThrot < 0.10)
			return 0.10;
		return forwardThrot;
	}
	
	public double getSideThrottlePID(double angle, double throttle, double kP, double kD){
		forwardThrottle = throttle;
		double kPgain = kP;//Robot.prefs.getNumber("D: Straight P", 0.04); /* percent throttle per degree of error */
		double kDgain = kD;//Robot.prefs.getNumber("D: Straight D", 0.0004); /* percent throttle per angular velocity dps */
		kMaxCorrectionRatio = Robot.prefs.getNumber("D: MaxCorrectRatio",0.30 ); /* cap corrective turning throttle to 30 percent of forward throttle */
		/** holds the current angle to servo to */
		targetAngle = angle;

		if (Robot.navX_READY){
			
			currentAngle = Robot.navX.getAngle();
	    	currentAngularRate = Robot.navX.getRate();
	
			turnThrottle = (targetAngle - currentAngle) * kPgain - (currentAngularRate) * kDgain;
			//Debugger.println("Turn Throttle: " + turnThrottle, Robot.drivetrain, Debugger.debug2);
			
			/* the max correction is the forward throttle times a scalar,
			 * This can be done a number of ways but basically only apply small turning correction when we are moving slow
			 * and larger correction the faster we move.  Otherwise you may need stiffer pgain at higher velocities. */
			double maxThrot = MaxCorrection(forwardThrottle, kMaxCorrectionRatio);
			turnThrottle = Cap(turnThrottle, maxThrot);
	
			//debugDriveStraight();
			
			/* positive turnThrottle means turn to the left, this can be replaced with ArcadeDrive object, or teams drivetrain object */
			sidePIDThrottle = forwardThrottle + turnThrottle;
			sidePIDThrottle = Cap(sidePIDThrottle, 1.0);
			return sidePIDThrottle;
		} else {
			//IF NAVX NOT PRESENT JUST RETURN THROTTLE
			Debugger.println("NO NAV-X", Robot.drivetrain, Debugger.debug2);
			debug("NAV X NOT READY: NO DRIVE STRAIGHT");
			return throttle;
		}
	}
	
	public double getTurnThrottlePID(double angle, double kP, double kD){
		forwardThrottle = 0;
		double kPgain = kP;//Robot.prefs.getNumber("D: Straight P", 0.04); /* percent throttle per degree of error */
		double kDgain = kD;//Robot.prefs.getNumber("D: Straight D", 0.0004); /* percent throttle per angular velocity dps */
		kMaxCorrectionRatio = Robot.prefs.getNumber("D: MaxCorrectRatio",0.30 ); /* cap corrective turning throttle to 30 percent of forward throttle */
		/** holds the current angle to servo to */
		targetAngle = angle;

		//Debugger.println("Turning", Robot.drivetrain, Debugger.debug2);
		
		if (Robot.navX_READY){
			
			currentAngle = -1*Robot.navX.getYaw();
	    	currentAngularRate = Robot.navX.getRate();
	
			turnThrottle = (targetAngle - currentAngle) * kPgain - (currentAngularRate) * kDgain;
			//Debugger.println("Turn Throttle: " + turnThrottle, Robot.drivetrain, Debugger.debug2);
	
			debugDriveStraight();
			
			/* positive turnThrottle means turn to the left, this can be replaced with ArcadeDrive object, or teams drivetrain object */
			sidePIDThrottle = turnThrottle;
			sidePIDThrottle = Cap(sidePIDThrottle, 1.0);
			return sidePIDThrottle;
		} else {
			//IF NAVX NOT PRESENT JUST RETURN THROTTLE
			Debugger.println("NO NAV-X", Robot.drivetrain, Debugger.debug2);
			debug("NAV X NOT READY: NO DRIVE STRAIGHT");
			return 0;
		}
	}	
	public void debug(String msg, int level){
		Debugger.println(msg, Robot.drivetrain, level);
	}
	public void debug(String msg){
		Debugger.println(msg, Robot.drivetrain, Debugger.debug2);
	}
	
	public void debugDriveStraight(){
		if (driveStraightPrintLoops > 50){
			driveStraightPrintLoops = 0;
			debug("------------------------------------------");
			debug("error: " + (targetAngle - currentAngle));
			debug("angle: "+ currentAngle);
			debug("rate: "+ currentAngularRate);
			debug("Right Straight Output:" + sidePIDThrottle);
			debug("------------------------------------------");
		}
		driveStraightPrintLoops++;
	}
}
