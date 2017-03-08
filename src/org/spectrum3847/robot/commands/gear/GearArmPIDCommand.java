package org.spectrum3847.robot.commands.gear;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Constants;
import org.spectrum3847.robot.HW;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.Utilities;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class GearArmPIDCommand extends Command{

	CANTalon arm;
	double target;
	double kP;
	double kD;
	
	
	public GearArmPIDCommand() {
		requires(Robot.gearIntake);
	
	}
	
	public void initialize(){
		target = getSetpoint();
		if (target < -0.2){
			target = 0;
		}else if (target > .73){
			target = .73;
		}
		arm = Robot.gearIntake.getArmTalon();
		kP = SmartDashboard.getNumber("Gear Arm kP", 1.8);
		kD = SmartDashboard.getNumber("Gear Arm kD", 16);
		arm.changeControlMode(TalonControlMode.Position);
		arm.setP(kP);
		arm.setD(kD);
		Robot.gearIntake.printDebug("PID Gear Arm Starting");
		Robot.gearIntake.printDebug("Setpoint = " + target + ", kP set to: " + kP + ", kD set to: " + kD);
	}
	
	public void execute(){
		arm.set(target);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearIntake.printDebug("Gear arm PID ended, switch to manual control");
    	arm.changeControlMode(TalonControlMode.PercentVbus);
    	arm.set(0);
    	//HW.Driver_Gamepad.setRumble(RumbleType.kRightRumble, .7);
    	//HW.Operator_Gamepad.setRumble(RumbleType.kLeftRumble, .7);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    abstract double getSetpoint();

}
