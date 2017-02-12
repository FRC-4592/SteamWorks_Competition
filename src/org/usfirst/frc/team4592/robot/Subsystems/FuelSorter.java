package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.FuelSorterButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;
import org.usfirst.frc.team4592.robot.Util.bangBang;

import com.ctre.CANTalon;

public class FuelSorter extends SubsystemFramework{
	private FuelSorterButton [] fuelSorterButtons;
	private CANTalon sorterMotor;
	private bangBang sorter_bangBang;
	private FuelSorterStates tempState;
	private FuelSorterStates state = FuelSorterStates.Stop;
	
	public FuelSorter(FuelSorterButton [] fuelSorterButtons, CANTalon sorterMotor){
		this.fuelSorterButtons = fuelSorterButtons;
		this.sorterMotor = sorterMotor;
		this.sorter_bangBang = new bangBang(1250);
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
				sorterMotor.set(sorter_bangBang.getOutput(sorterMotor.getSpeed()));
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
		
			case Reverse:
				sorterMotor.set(-0.35);
				
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

	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
		
	}

}