package org.spectrum3847.robot.commands.leds;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;
import java.util.concurrent.TimeUnit;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.spectrum3847.robot.subsystems.LEDs;

public class LEDController extends Command{

	public LEDController(String c, double seconds, boolean blink, double spd) {
		requires(Robot.leds);
		
		Robot.leds.timeToDisplay = seconds;
		Robot.leds.tempColor = c;
		Robot.leds.isBlinking = blink;
		Robot.leds.spd = spd;
	}
	
	public LEDController(String c, boolean blink, double spd) {
		requires(Robot.leds);
		

		Robot.leds.tempColor = c;
		Robot.leds.isBlinking = blink;
		Robot.leds.spd = spd;
	}
	
	public LEDController(String c) {
		requires(Robot.leds);
		
		Robot.leds.tempColor = c;
		Robot.leds.isBlinking = false;
		Robot.leds.spd = 1;
	}
	
	public LEDController() {
		requires(Robot.leds);
		
		Robot.leds.tempColor = "purple";
		Robot.leds.isBlinking = false;
		Robot.leds.spd = 1;
	}
	
	// Called once right before the Command runs for the first time
	protected void initialize() {
		//displayColor(color);
		System.out.println("Lights are a go.");
	}
	
	// Called repeatedly when the Command is scheduled to run
	protected void execute() {
		
		// Checks if the operator wishes to blink the lights
		if(Robot.leds.isBlinking) {
		
			Robot.leds.blinkTime = Timer.getFPGATimestamp();
    		boolean counter = (Robot.leds.blinkTime % (Robot.leds.spd * 2)) < Robot.leds.spd;
    		if (counter){
    			displayColor();
    		} else{
    			Robot.leds.off();
    		}
		}
		
		else if(Robot.leds.timeToDisplay == 0 || ((Timer.getFPGATimestamp() - Robot.leds.startTime) < Robot.leds.timeToDisplay)){
			displayColor();
			//System.out.println("This time is: " + ((Timer.getFPGATimestamp() - Robot.leds.startTime) < Robot.leds.timeToDisplay));
		}
		else {
			Robot.leds.tempColor = Robot.leds.defaultColor;
			Robot.leds.timeToDisplay = 0;
			displayColor();
		}
	}	
	
	// Called when isFinished() returns true
	protected void end() {
		Robot.leds.off();
	}
	
	// Called if the Command is interrupted at any point
	protected void interrupt() {
		 end();	
	}
	
	// Will return true when this Command no longer needs to run execute()
	// In this case, the command will run continuously when the robot is on
	protected boolean isFinished() {
		return false;
	}

	private void displayColor() {
		//System.out.println("going to display " + Robot.leds.tempColor);
		switch(Robot.leds.tempColor) {
			case "purple":{
				Robot.leds.purple();
				break;
			}
			case "red":{
				Robot.leds.red();
				break;
			}
			case "blue":{
				Robot.leds.blue();
				break;
			}
			case "yellow":{
				Robot.leds.yellow();
				break;
			}
			case "green":{
				Robot.leds.green();
				break;
			}
			default:{
				Robot.leds.off();
			}
		
		}
		
	}

}
