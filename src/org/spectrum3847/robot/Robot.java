
package org.spectrum3847.robot;


import org.spectrum3847.lib.drivers.SpecAHRS;
import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Logger;
import org.spectrum3847.lib.util.SpectrumPreferences;
import org.spectrum3847.robot.subsystems.Drive;
import org.spectrum3847.robot.subsystems.GearIntake;
import org.spectrum3847.robot.subsystems.GearSpear;
import org.spectrum3847.robot.subsystems.LEDs;
import org.spectrum3847.robot.subsystems.Climber;
import org.spectrum3847.robot.subsystems.ShooterWheel;
import org.spectrum3847.robot.commands.leds.Purple;
import org.spectrum3847.robot.subsystems.BeltBed;

import com.ctre.CANTalon;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	//logger
	public static Logger logger;
    //Add Debug flags
	//You can have a flag for each subsystem, etc
	public static final String output = "OUT";
	public static final String input = "IN";
	public static final String controls = "CONTROL";
	public static final String general = "GENERAL";
	public static final String auton = "AUTON";
	public static final String commands = "COMMAND";
	public static final String gear = "GEAR";
	public static final String shooter = "SHOOTER";
	public static final String climb = "CLIMB";
	public static final String intake = "INTAKE";
	
	// Create a single static instance of all of your subsystems
    // This MUST be here. If the OI creates Commands (which it very likely
    // will), constructing it during the construction of CommandBase (from
    // which commands extend), subsystems are not guaranteed to be
    // yet. Thus, their requires() statements may grab null pointers. Bad
    // news. Don't move it.
	
	public static Drive drive; 
	public static SpectrumSpeedControllerCAN leftDrive;
	public static SpectrumSpeedControllerCAN rightDrive;

	public static CANTalon left_drive_talon_1;
	public static CANTalon left_drive_talon_2;
	public static CANTalon left_drive_talon_3;
	
	public static CANTalon right_drive_talon_1;
	public static CANTalon right_drive_talon_2;
	public static CANTalon right_drive_talon_3;
	
	
	public static SpectrumSolenoid brakes;
	
	public static ShooterWheel shooterWheel;
	public static ShooterWheel shooterTower;
	public static SpectrumSpeedControllerCAN shooterWheelMotor;
	public static SpectrumSpeedControllerCAN shooterTowerMotor;
	
	public static BeltBed beltBed;
	public static SpectrumSpeedControllerCAN beltBedMotors;

	public static Climber climber;
	public static SpectrumSpeedControllerCAN climberMotors;
	
	public static GearIntake gearIntake;
	public static SpectrumSpeedControllerCAN gearIntakeMotor;
	public static SpectrumSpeedControllerCAN gearArmMotor;
	public static GearSpear gearSpear;
	
	public static Compressor compressor;
	
	//public static SpecAHRS navX;
	
	public static SpectrumPreferences prefs;
	
	public static LEDs leds;
	
	
    public static void setupSubsystems(){
    	prefs = SpectrumPreferences.getInstance();
    	
    	compressor = new Compressor(0);
    	
    	//CameraServer.getInstance().startAutomaticCapture("Gear Cam", (int) SmartDashboard.getNumber("Gear Cam USB ID", 2));
    	
    	//DRIVETRAIN
    	double vRamp = Robot.prefs.getNumber("D: Voltage Ramp", 60);
    	
    	left_drive_talon_1 = new CANTalon(HW.LEFT_DRIVE_BACK_MOTOR);
    	left_drive_talon_2 = new CANTalon(HW.LEFT_DRIVE_MIDDLE_MOTOR);
    	left_drive_talon_3 = new CANTalon(HW.LEFT_DRIVE_FRONT_MOTOR);
    	left_drive_talon_1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	left_drive_talon_1.setVoltageRampRate(vRamp);
    	left_drive_talon_1.enableBrakeMode(false);
    	left_drive_talon_2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	left_drive_talon_2.set(left_drive_talon_1.getDeviceID());
    	left_drive_talon_2.setVoltageRampRate(vRamp);
    	left_drive_talon_2.enableBrakeMode(false);
    	left_drive_talon_3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	left_drive_talon_3.set(left_drive_talon_1.getDeviceID());
    	left_drive_talon_3.setVoltageRampRate(vRamp);
    	left_drive_talon_3.enableBrakeMode(false);
    	
    	right_drive_talon_1 = new CANTalon(HW.RIGHT_DRIVE_BACK_MOTOR);
    	right_drive_talon_2 = new CANTalon(HW.RIGHT_DRIVE_MIDDLE_MOTOR);
    	right_drive_talon_3 = new CANTalon(HW.RIGHT_DRIVE_FRONT_MOTOR);
    	right_drive_talon_1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	right_drive_talon_1.setVoltageRampRate(vRamp);
    	right_drive_talon_1.enableBrakeMode(false);
    	right_drive_talon_2.changeControlMode(CANTalon.TalonControlMode.Follower);
    	right_drive_talon_2.set(right_drive_talon_1.getDeviceID());
    	right_drive_talon_2.setVoltageRampRate(vRamp);
    	right_drive_talon_2.enableBrakeMode(false);
    	right_drive_talon_3.changeControlMode(CANTalon.TalonControlMode.Follower);
    	right_drive_talon_3.set(right_drive_talon_1.getDeviceID());
    	right_drive_talon_3.setVoltageRampRate(vRamp);
    	right_drive_talon_3.enableBrakeMode(false);
    	
    	leftDrive = new SpectrumSpeedControllerCAN(
    				new CANTalon[] {left_drive_talon_1, left_drive_talon_2, left_drive_talon_3},
    				new int[] {HW.LEFT_DRIVE_BACK_MOTOR_PDP, HW.LEFT_DRIVE_MIDDLE_MOTOR_PDP, HW.LEFT_DRIVE_FRONT_MOTOR_PDP}
    			);
    	
    	rightDrive = new SpectrumSpeedControllerCAN(
    				new CANTalon[] {right_drive_talon_1, right_drive_talon_2, right_drive_talon_3},
    				new int[] {HW.RIGHT_DRIVE_BACK_MOTOR_PDP, HW.RIGHT_DRIVE_MIDDLE_MOTOR_PDP, HW.RIGHT_DRIVE_FRONT_MOTOR_PDP}
    			);
    	brakes = new SpectrumSolenoid(HW.BRAKE_SOL);
    	
    	drive = new Drive("defaultDrive", leftDrive, rightDrive, brakes);

    	//Climbers
    	CANTalon climber_left_talon = new CANTalon(HW.CLIMBER_LEFT_MOTOR);
    	climber_left_talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	climber_left_talon.setInverted(true);
    	climber_left_talon.reverseOutput(false);
    	climber_left_talon.enableBrakeMode(true);
    	
    	CANTalon climber_right_talon = new CANTalon(HW.CLIMBER_RIGHT_MOTOR);
    	climber_right_talon.changeControlMode(CANTalon.TalonControlMode.Follower);
    	climber_right_talon.set(climber_left_talon.getDeviceID());
    	climber_right_talon.reverseOutput(true);
    	climber_right_talon.enableBrakeMode(true);

    	climberMotors = new SpectrumSpeedControllerCAN(
    			new CANTalon[] {climber_left_talon, climber_right_talon},
    			new int[] {HW.CLIMBER_LEFT_MOTOR_PDP, HW.CLIMBER_RIGHT_MOTOR_PDP}
    		);

    	climber = new Climber("Climber", climberMotors); 			
    	

    	//Shooter
    	CANTalon shooter_wheel_talon = new CANTalon(HW.SHOOTER_WHEEL);
    	shooter_wheel_talon.changeControlMode(CANTalon.TalonControlMode.Speed);
    	shooter_wheel_talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	shooter_wheel_talon.setInverted(false);
    	shooter_wheel_talon.reverseSensor(false);
    	shooter_wheel_talon.reverseOutput(false);
    	shooter_wheel_talon.enableBrakeMode(false);

    	//Shooter Tower
    	CANTalon tower_talon = new CANTalon(HW.SHOOTER_TOWER);
    	tower_talon.changeControlMode(CANTalon.TalonControlMode.Speed);
    	tower_talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	tower_talon.setInverted(false);
    	tower_talon.reverseSensor(true);
    	tower_talon.reverseOutput(true);
    	tower_talon.enableBrakeMode(false);
    	
    	//Belt Bed
    	CANTalon belt_bed_talon = new CANTalon(HW.BELT_BED);
    	belt_bed_talon.changeControlMode(CANTalon.TalonControlMode.Voltage);
    	belt_bed_talon.enableBrakeMode(false);
    	
    	shooterWheelMotor = new SpectrumSpeedControllerCAN(
    						shooter_wheel_talon,
    						HW.SHOOTER_WHEEL_MOTOR_PDP
    						);
    	
    	shooterTowerMotor = new SpectrumSpeedControllerCAN(
				tower_talon,
				HW.SHOOTER_WHEEL_MOTOR_PDP
				);
    	
    	shooterWheel = new ShooterWheel("Shooter Wheel", shooterWheelMotor);
    	shooterTower = new ShooterWheel("Shooter Tower", shooterTowerMotor);
    	
    	beltBedMotors = new SpectrumSpeedControllerCAN(
    					HW.BELT_BED, HW.BELT_BED_MOTOR_PDP);
    	
    	beltBed = new BeltBed("Tower", beltBedMotors);
    	
    	//Gear Intake
    	CANTalon gear_intake_talon = new CANTalon(HW.GEAR_INTAKE_MOTOR);
    	gear_intake_talon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    	gear_intake_talon.enableBrakeMode(false);
    	
    	gearIntakeMotor = new SpectrumSpeedControllerCAN(
    			new CANTalon[] {gear_intake_talon},
    			new int[] {HW.GEAR_INTAKE_MOTOR_PDP});
    	
    	CANTalon gear_arm_talon = new CANTalon(HW.GEAR_ARM_MOTOR);
    	gear_arm_talon.changeControlMode(CANTalon.TalonControlMode.Position);
    	gear_arm_talon.enableBrakeMode(true);
    	
    	gearArmMotor = new SpectrumSpeedControllerCAN(
    			new CANTalon[] {gear_arm_talon},
    			new int[] {HW.GEAR_ARM_MOTOR_PDP});
    	
    	gearIntake = new GearIntake(gearIntakeMotor, gearArmMotor);
    	
    	SpectrumSolenoid gear_spear_extend_sol = new SpectrumSolenoid(HW.GEAR_SPEAR_EXTEND_SOL);
    	SpectrumSolenoid gear_spear_retract_sol = new SpectrumSolenoid(HW.GEAR_SPEAR_RETRACT_SOL);
    	
    	gearSpear = new GearSpear("Gear Spear", gear_spear_extend_sol, gear_spear_retract_sol);
    
    	leds = new LEDs();
    	//navX = new SpecAHRS(SPI.Port.kMXP);

    	
    	
    }
    
    //Used to keep track of the robot current state easily
    public enum RobotState {
        DISABLED, AUTONOMOUS, TELEOP
    }

    public static RobotState s_robot_state = RobotState.DISABLED;

    public static RobotState getState() {
        return s_robot_state;
    }

    public static void setState(RobotState state) {
        s_robot_state = state;
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	initDebugger();
    	printGeneralInfo("Start robotInit()");
    	setupSubsystems(); //This has to be before the OI is created on the next line
		HW.oi = new OI();
        Dashboard.intializeDashboard();
        //UsbCamera cam0 = new UsbCamera("cam0", "/dev/video0");
        //cam0.setResolution(160, 70);
		//cam0.setFPS(15);
		//cam0.setVideoMode(PixelFormat.kMJPEG, 128, 72, 10); //PixelFormat, height, width, fps
		//cam0.setExposureHoldCurrent();
        //MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
        //mjpegServer1.setSource(cam0);
        //cam0.setResolution(320, 240);
        //cam0.setFPS(10);
        //cam0.setExposureAuto();
		//CameraServer.getInstance().startAutomaticCapture(cam0);
		//CameraServer.getInstance().startAutomaticCapture();
		UsbCamera cam0 = CameraServer.getInstance().startAutomaticCapture();
        //cam0.setResolution(160, 120);
        cam0.setExposureAuto();
        Autonomous.createChooser();
        new Purple().start();
		logger = Logger.getInstance();
    }
    
    public void robotPeriodic(){
    	
    }
    
    private static void initDebugger(){
    	Debugger.setLevel(Debugger.debug2); //Set the initial Debugger Level
    	Debugger.flagOn(general); //Set all the flags on, comment out ones you want off
    	Debugger.flagOn(controls);
    	Debugger.flagOn(input);
    	Debugger.flagOn(output);
    	Debugger.flagOn(auton);
    	Debugger.flagOn(commands);
    	Debugger.flagOn(intake);
    	Debugger.flagOff(shooter);
    	Debugger.flagOn(gear);
    }
    /**
     * Initialization code for test mode should go here.
     *
     * Users should override this method for initialization code which will be called each time
     * the robot enters test mode.
     */
    public void testInit() {
    	//compressor.startLiveWindowMode();
    	//compressor.setClosedLoopControl(false);
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        setState(RobotState.DISABLED);
        printGeneralInfo("Start disabledInit()");
        Disabled.init();
        printGeneralInfo("End disableInit()");
    }
    /**
     * This function is called while in disabled mode.
     */    
    public void disabledPeriodic(){
    	Disabled.periodic();
    }


    public void autonomousInit() {
    	setState(RobotState.AUTONOMOUS);
    	printGeneralInfo("Start autonomousInit()");
        Autonomous.init();
        printGeneralInfo("End autonomousInit()");
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Autonomous.periodic();
    }

    public void teleopInit() {
    	setState(RobotState.TELEOP);
    	printGeneralInfo("Start teleopInit()");
        Teleop.init();
        printGeneralInfo("End teleopInit()");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Teleop.periodic();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public static void printGeneralInfo(String msg){
    	Debugger.println(msg, general, Debugger.info3);
    }
}
