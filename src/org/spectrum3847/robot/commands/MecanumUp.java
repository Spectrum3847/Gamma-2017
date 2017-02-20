package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumUp extends Command{

	public MecanumUp() {
		// TODO Auto-generated constructor stub
		requires(Robot.mecanumCollector);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		System.out.println("MECANUM UP" + " is not extended: " + !Robot.mecanumCollector.isExtended() + " is not collecting: " + !Robot.mecanumCollector.isCollecting());
		if(!Robot.mecanumCollector.isExtended() && !Robot.mecanumCollector.isCollecting())
			Robot.mecanumCollector.extend();
		
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		//Robot.mecanumCollector.retract();
	}

}
