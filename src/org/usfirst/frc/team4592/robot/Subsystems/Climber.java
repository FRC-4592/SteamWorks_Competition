package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.ClimberButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;
import org.usfirst.frc.team4592.robot.Util.bangBang;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.VictorSP;

public class Climber extends SubsystemFramework{
	private ClimberButton [] climberButtons;
	private CANTalon controlClimberMotor;
	private VictorSP climberMotor;
	private ClimberStates tempState;
	private ClimberStates state = ClimberStates.Off;
	
	public Climber(ClimberButton [] climberButtons, CANTalon controlClimberMotor, VictorSP climberMotor){
		this.climberButtons = climberButtons;
		this.controlClimberMotor = controlClimberMotor;
		this.climberMotor = climberMotor;
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
				controlClimberMotor.set(0);
				climberMotor.set(0);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Climb:
				
				controlClimberMotor.set(1);
				climberMotor.set(1);
				
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
