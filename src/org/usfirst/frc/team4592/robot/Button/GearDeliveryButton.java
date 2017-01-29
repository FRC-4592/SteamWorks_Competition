package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery.GearDeliveryStates;

public class GearDeliveryButton extends Button{
	private GearDeliveryStates wantedState;
	
	public GearDeliveryButton(int buttonNumber, GearDeliveryStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public GearDeliveryStates getWantedState(){
		return wantedState;
	}
}
