package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.FuelSorterButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;

import edu.wpi.first.wpilibj.CANTalon;

public class FuelSorter implements Loopable{
	private FuelSorterButton [] fuelSorterButtons;
	private CANTalon sorterMotor;
	private FuelSorterStates tempState;
	private FuelSorterStates state = FuelSorterStates.Stop;
	
	public FuelSorter(FuelSorterButton [] fuelSorterButtons, CANTalon sorterMotor){
		this.fuelSorterButtons = fuelSorterButtons;
		this.sorterMotor = sorterMotor;
	}
	
	public enum FuelSorterStates{
		Forward, Reverse, Stop;
	}
	
	public FuelSorterStates buttonCheck(){
		for(int i = 0; i < fuelSorterButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(fuelSorterButtons[i].getButtonNumber())){
				return fuelSorterButtons[i].getWantedState();
			}
		}
		
		return null;
	}
	
	@Override
	public void update() {
		FuelSorterStates newState = state;
		
		switch(state){
		
			case Forward:
				sorterMotor.set(0.85);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
		
			case Reverse:
				sorterMotor.set(-0.85);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Stop:
				sorterMotor.set(0);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			default:
				newState = FuelSorterStates.Stop;
	break;
		}
		
		if(newState != state){
			state = newState;
		}
	}

}