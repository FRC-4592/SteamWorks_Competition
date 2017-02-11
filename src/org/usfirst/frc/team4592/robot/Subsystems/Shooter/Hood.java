package org.usfirst.frc.team4592.robot.Subsystems.Shooter;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.HoodButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Util.PID;

import com.ctre.CANTalon;

@SuppressWarnings("unused")
public class Hood implements Loopable{
	private HoodButton [] hoodButtons;	
	private CANTalon hoodPositionMotor;
	private PID Hood_P;
	private double goal;
	private double error;
	private HoodStates tempState;
	private HoodStates state = HoodStates.Start;
	
	public Hood(HoodButton [] hoodButtons, CANTalon hoodPositionMotor, double Hood_Kp){
		this.hoodButtons = hoodButtons;
		this.hoodPositionMotor = hoodPositionMotor;
		this.Hood_P = new PID(Hood_Kp);
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
				goal = 0;
				error = goal - (hoodPositionMotor.getPosition()*(360/3.14));
				
				hoodPositionMotor.set(Hood_P.getOutputP(error));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			case CenterGear:
				goal = 0;
				error = goal - (hoodPositionMotor.getPosition()*(360/3.14));
				
				hoodPositionMotor.set(Hood_P.getOutputP(error));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case SideGear:
				goal = 0;
				error = goal - (hoodPositionMotor.getPosition()*(360/3.14));
				
				hoodPositionMotor.set(Hood_P.getOutputP(error));//PID for side gear position
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Hopper:
				//wanted launch angle is 60, 
				//so may be 30 degrees or 60 degrees that motor goes to
				goal = 40;
				error = goal - (hoodPositionMotor.getPosition()*(360/3.14));
				
				hoodPositionMotor.set(Hood_P.getOutputP(error));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Boiler:
				//wanted launch angle is 53, 
				//so may be 37 degrees or 53 degrees that motor goes to
				goal = 37;
				error = goal - (hoodPositionMotor.getPosition()*(360/3.14));
				
				hoodPositionMotor.set(Hood_P.getOutputP(error));
				
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