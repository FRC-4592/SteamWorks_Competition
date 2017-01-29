package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.FuelDelivery.FuelDeliveryStates;

public class FuelDeliveryButton extends Button{
	private FuelDeliveryStates wantedState;
	
	public FuelDeliveryButton(int buttonNumber, FuelDeliveryStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public FuelDeliveryStates getWantedState(){
		return wantedState;
	}
}
