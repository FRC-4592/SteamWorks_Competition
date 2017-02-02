package org.usfirst.frc.team4592.robot.Subsystems.Shooter;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.HoodButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;

import edu.wpi.first.wpilibj.CANTalon;

@SuppressWarnings("unused")
public class Hood implements Loopable{
	private HoodButton [] hoodButtons;	
	private CANTalon positionMotor;
	private HoodStates tempState;
	private HoodStates state = HoodStates.Start;
	
	public Hood(HoodButton [] hoodButtons, CANTalon positionMotor){
		this.hoodButtons = hoodButtons;
		this.positionMotor = positionMotor;
	}
	
	public enum HoodStates{
		Start, CenterGear, SideGear, Hopper, Boiler;
	}
	
	public HoodStates buttonCheck(){
		for(int i = 0; i < hoodButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(hoodButtons[i].getButtonNumber())){
				return hoodButtons[i].getWantedState();
			}
		}
		
		return null;
	}
	
	@Override
	public void update(){
		HoodStates newState = state;
		
		switch(state){
		
			case Start:
				//PID for start position
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			case CenterGear:
				//PID for center gear position
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case SideGear:
				//PID for side gear position
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Hopper:
				//PID for hopper position
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			default:
				newState = HoodStates.Start;
	break;
		}
		
		if(newState != state){
			state = newState;
		}
	}
}