package org.usfirst.frc.team4592.robot;

import org.usfirst.frc.team4592.robot.Lib.Loopable;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashBoard extends SmartDashboard implements Loopable{
	private SendableChooser chooser = new SendableChooser();
	@SuppressWarnings("unused")
	private int autoSelected;
	
	public void auto(){
		chooser.addDefault("NOTHING", 1);
		//chooser.addObject("", object);
	}
	@Override
	public void update(){
		
	}

}
