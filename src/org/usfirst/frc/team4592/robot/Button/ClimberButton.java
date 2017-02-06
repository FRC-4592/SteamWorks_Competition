package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.Climber.ClimberStates;

public class ClimberButton extends Button{
	private ClimberStates wantedState;
	
	public ClimberButton(ClimberStates wantedState){
		this.wantedState = wantedState;
	}
	
	public ClimberStates getWantedState(){
		return wantedState;
	}
}
