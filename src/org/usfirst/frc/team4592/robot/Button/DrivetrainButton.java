package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain.DrivetrainStates;

public class DrivetrainButton extends Button{
	private DrivetrainStates wantedState;
	
	public DrivetrainButton(int buttonNumber, DrivetrainStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public DrivetrainStates getWantedState(){
		return wantedState;
	}
}
