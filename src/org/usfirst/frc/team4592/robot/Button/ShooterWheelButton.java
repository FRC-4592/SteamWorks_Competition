package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.Shooter.ShooterWheel.ShooterWheelStates;

public class ShooterWheelButton extends Button{
	private ShooterWheelStates wantedState;
	
	public ShooterWheelButton(int buttonNumber, ShooterWheelStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public ShooterWheelStates getWantedState(){
		return wantedState;
	}
}
