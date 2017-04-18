package org.spectrum3847.lib.drivers;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class SpectrumAxisButton extends Button {

	XboxController controller;
	int axis;
	double value;
	boolean greater;
	
	public SpectrumAxisButton(XboxController cont, XboxAxis axisNum, double val, boolean greatThan){
		controller = cont;
		axis = axisNum.value;
		value = val;
		greater = greatThan;
		
	}
	
	public enum XboxAxis{
		RightX(4), RightY(5), LeftX(0), LeftY(1), RightTrigger(3), LeftTrigger(2);
	
		public final int value;
		
		private XboxAxis(int value){
			this.value = value;
		}
	}
	
	
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		if (greater){
			if (controller.getRawAxis(axis) > value){
				Debugger.println("Axis: )" + axis + " value: " + value + " greater: " + greater, Robot.commands, Debugger.verbose1);
				return true;
			}
		} 
		else if (!greater && controller.getRawAxis(axis) < value){
			return true;
		} 
		return false;
	}

}
