package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.Shooter.Turret.TurretStates;

public class TurretButton extends Button{
	private TurretStates wantedState;
	
	public TurretButton(int buttonNumber, TurretStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public TurretStates getWantedState(){
		return wantedState;
	}
}
