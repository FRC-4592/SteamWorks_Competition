package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.FuelIntakeButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Util.bangBang;

import edu.wpi.first.wpilibj.CANTalon;

public class FuelIntake implements Loopable{
	private FuelIntakeButton [] fuelIntakeButtons;
	private CANTalon fuelIntakeMotor;
	private bangBang pickUp_bangBang;
	private FuelIntakeStates tempState;
	private FuelIntakeStates state = FuelIntakeStates.Stop;
	
	
	public FuelIntake(FuelIntakeButton [] fuelIntakeButtons, CANTalon fuelIntakeMotor){
		this.fuelIntakeButtons = fuelIntakeButtons;
		this.fuelIntakeMotor = fuelIntakeMotor;
		this.pickUp_bangBang = new bangBang(1500);
	}
	
	public enum FuelIntakeStates{
		PickUp, Reverse, Stop;
	}
	
	public FuelIntakeStates buttonCheck(){
		for(int i = 0; i < fuelIntakeButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(fuelIntakeButtons[i].getButtonNumber())){
				return fuelIntakeButtons[i].getWantedState();
			}
		}
		
		return null;
	}

	@Override
	public void update(){
		FuelIntakeStates newState = state;
		
		switch(state){
		
			case PickUp:
				fuelIntakeMotor.set(
						pickUp_bangBang.getOutput(fuelIntakeMotor.getSpeed()));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Reverse:
				fuelIntakeMotor.set(-0.75);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Stop:
				fuelIntakeMotor.set(0);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			default:
				newState = FuelIntakeStates.Stop;
	break;
		}
		
		if(newState != state) {
	  		state = newState;
		}
	}
}