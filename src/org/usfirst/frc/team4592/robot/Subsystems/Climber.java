package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.ClimberButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;

import edu.wpi.first.wpilibj.VictorSP;

public class Climber extends SubsystemFramework{
	private ClimberButton [] climberButtons;
	private VictorSP rightClimberMotor;
	private VictorSP leftClimberMotor;
	private ClimberStates tempState;
	private ClimberStates state = ClimberStates.Off;
	
	public Climber(ClimberButton [] climberButtons, VictorSP rightClimberMotor, VictorSP leftClimberMotor){
		this.climberButtons = climberButtons;
		this.rightClimberMotor = rightClimberMotor;
		this.leftClimberMotor = leftClimberMotor;
	}
	
	public enum ClimberStates{
		Climb, Off;
	}
	
	public ClimberStates buttonCheck(){
		for(int i = 0; i < climberButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(climberButtons[i].getButtonNumber())){
				return climberButtons[i].getWantedState();
			}
		}
		
		return null;
	}
	
	@Override
	public void update() {
		ClimberStates newState = state;
		
		switch(state){
			
			case Off:
				rightClimberMotor.set(0);
				leftClimberMotor.set(0);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Climb:
				//one of these will have to be negative
				rightClimberMotor.set(1);
				leftClimberMotor.set(1);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			default:
				newState = ClimberStates.Off;
	break;
		}
		
		if(newState != state){
			state = newState;
		}
	}

	@Override
	public void outputToSmartDashboard(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupSensors(){		
	}
}
