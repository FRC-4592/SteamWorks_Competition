package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.Shooter.Hood.HoodStates;

public class HoodButton extends Button{
	private HoodStates wantedState;
	
	public HoodButton(int buttonNumber, HoodStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public HoodStates getWantedState(){
		return wantedState;
	}
}
