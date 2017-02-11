package org.usfirst.frc.team4592.robot.Subsystems.Shooter;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.TurretButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Util.PID;

import com.ctre.CANTalon;

@SuppressWarnings("unused")
public class Turret implements Loopable{
	private TurretButton [] turretButtons;
	private CANTalon turretMotor;
	private PID Turret_P;
	private double goal;
	private double error;
	private TurretStates tempState;
	private TurretStates state = TurretStates.Start;
	
	public Turret(TurretButton [] turretButtons, CANTalon turretMotor, double Turret_Kp){
		this.turretButtons = turretButtons;
		this.turretMotor = turretMotor;
		this.Turret_P = new PID(Turret_Kp);
		
		
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
				goal = 0;
				error = goal - (turretMotor.getPosition()*(360/3.14));
				
				turretMotor.set(Turret_P.getOutputP(error));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			case CenterGear:
				goal = 10;
				error = goal - (turretMotor.getPosition()*(360/3.14));
				
				turretMotor.set(Turret_P.getOutputP(error));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case SideGear:
				goal = 20;
				error = goal - (turretMotor.getPosition()*(360/3.14));
				
				turretMotor.set(Turret_P.getOutputP(error));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Hopper:
				goal = 30;
				error = goal - (turretMotor.getPosition()*(360/3.14));
				
				turretMotor.set(Turret_P.getOutputP(error));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			case Boiler:
				goal = 40;
				error = goal - (turretMotor.getPosition()*(360/3.14));
				
				turretMotor.set(Turret_P.getOutputP(error));
				
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