package org.usfirst.frc.team4592.robot.Subsystems.Shooter;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.ShooterWheelButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Subsystems.Shooter.Hood.HoodStates;

import edu.wpi.first.wpilibj.CANTalon;

@SuppressWarnings("unused")
public class ShooterWheel implements Loopable{
	private ShooterWheelButton [] shooterWheelButtons;
	private CANTalon shooterWheelMotor;
	private ShooterWheelStates tempState;
	private ShooterWheelStates state = ShooterWheelStates.Stop;
	
	public enum ShooterWheelStates{
		Stop, CenterGear, SideGear, Hooper, Boiler;
	}
	
	public ShooterWheelStates buttonCheck(){
		for(int i = 0; i < shooterWheelButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(shooterWheelButtons[i].getButtonNumber())){
				return shooterWheelButtons[i].getWantedState();
			}
		}
		
		return null;
	}
	
	@Override
	public void update(){
		ShooterWheelStates newState = state;
		
		switch(state){
			case Stop:
				//Stop wheel
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case CenterGear:
				//spin wheel at center gear rpm
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case SideGear:
				//spin wheel at side gear rpm
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Hooper:
				//spin wheel at hooper rpm
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Boiler:
				//spin wheel at boiler rpm
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			default:
				newState = ShooterWheelStates.Stop;
	break;
		}
		
		if(newState != state){
			state = newState;
		}
	}
}