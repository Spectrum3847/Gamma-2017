package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MecanumDown extends Command{

	public MecanumDown() {
		// TODO Auto-generated constructor stub
		//requires(Robot.mecanumCollector);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		System.out.println("MECANUM DOWN " + " is extended: " + Robot.mecanumCollector.isExtended());
		//if(Robot.mecanumCollector.isExtended())
		Robot.mecanumCollector.retract();
		
		
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
		Robot.mecanumCollector.extend();
		System.out.println("MECANUM UP");
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		//Robot.mecanumCollector.extend();
		this.end();
	}

}
