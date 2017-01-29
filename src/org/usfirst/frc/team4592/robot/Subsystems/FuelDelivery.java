package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.FuelDeliveryButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Util.bangBang;

import edu.wpi.first.wpilibj.CANTalon;

public class FuelDelivery implements Loopable{
	private FuelDeliveryButton [] fuelDeliveryButtons; 
	private CANTalon fuelDeliveryMotor;
	private bangBang delivery_bangBang;
	private FuelDeliveryStates tempState;
	private FuelDeliveryStates state = FuelDeliveryStates.Stop;
	
	public FuelDelivery(FuelDeliveryButton [] fuelDeliveryButtons, CANTalon fuelDeliveryMotor){
		this.fuelDeliveryButtons = fuelDeliveryButtons;
		this.fuelDeliveryMotor = fuelDeliveryMotor;
		this.delivery_bangBang = new bangBang(1500);
	}
	
	public enum FuelDeliveryStates{
		PickUp, Reverse, Stop;
	}
	
	public FuelDeliveryStates buttonCheck(){
		for(int i = 0; i < fuelDeliveryButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(fuelDeliveryButtons[i].getButtonNumber())){
				return fuelDeliveryButtons[i].getWantedState();
			}
		}
		
		return null;
	}

	@Override
	public void update() {
		FuelDeliveryStates newState = state;
		
		switch(state){
		
			case PickUp:
				fuelDeliveryMotor.set(
						delivery_bangBang.getOutput(fuelDeliveryMotor.getSpeed()));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			case Reverse:
				fuelDeliveryMotor.set(-0.75);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
			
			case Stop:
				fuelDeliveryMotor.set(0);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			default:
				newState = FuelDeliveryStates.Stop;
	break;
		}
		
		if(newState != state){
			state = newState;
		}
	}
}