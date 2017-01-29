package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.FuelIntake.FuelIntakeStates;

public class FuelIntakeButton extends Button{
	private FuelIntakeStates wantedState;
	
	public FuelIntakeButton(int buttonNumber, FuelIntakeStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public FuelIntakeStates getWantedState(){
		return wantedState;
	}
}
