package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.ClimberButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Util.bangBang;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.VictorSP;

public class Climber implements Loopable{
	private ClimberButton [] climberButtons;
	private CANTalon controlClimberMotor;
	private VictorSP climberMotor;
	private bangBang climb_bangBang;
	private ClimberStates tempState;
	private ClimberStates state = ClimberStates.Off;
	private double output;
	
	public Climber(ClimberButton [] climberButtons, CANTalon controlClimberMotor, VictorSP climberMotor){
		this.climberButtons = climberButtons;
		this.controlClimberMotor = controlClimberMotor;
		this.climberMotor = climberMotor;
		this.climb_bangBang = new bangBang(100);
	}
	
	public enum ClimberStates{
		Climb, On, Reverse, Off;
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
		
			case On:
				controlClimberMotor.set(0.75);
				climberMotor.set(0.75);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			case Off:
				controlClimberMotor.set(0);
				climberMotor.set(0);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Climb:
				output = climb_bangBang.getOutput(controlClimberMotor.getSpeed());
				
				controlClimberMotor.set(output);
				climberMotor.set(output);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Reverse:
				controlClimberMotor.set(-0.5);
				climberMotor.set(-0.5);
				
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
}
