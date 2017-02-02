package org.usfirst.frc.team4592.robot.Subsystems.Shooter;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.TurretButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Subsystems.Shooter.Hood.HoodStates;

import edu.wpi.first.wpilibj.CANTalon;

@SuppressWarnings("unused")
public class Turret implements Loopable{
	private TurretButton [] turretButtons;
	private CANTalon turretMotor;
	private TurretStates tempState;
	private TurretStates state = TurretStates.Start;
	
	public Turret(TurretButton [] turretButtons, CANTalon turretMotor){
		this.turretButtons = turretButtons;
		this.turretMotor = turretMotor;
	}
	
	public enum TurretStates{
		Start, CenterGear, SideGear, Hopper, Boiler;
	}
	
	public TurretStates buttonCheck(){
		for(int i = 0; i < turretButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(turretButtons[i].getButtonNumber())){
				return turretButtons[i].getWantedState();
			}
		}
		
		return null;
	}
	
	@Override
	public void update(){
		TurretStates newState = state;
		
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
				newState = TurretStates.Start;
	break;
		}
	
		if(newState != state){
			state = newState;
		}
	}
}