package org.usfirst.frc.team4592.robot.Button;

import org.usfirst.frc.team4592.robot.Subsystems.FuelSorter.FuelSorterStates;

public class FuelSorterButton extends Button{
	private FuelSorterStates wantedState;
	
	public FuelSorterButton(int buttonNumber, FuelSorterStates wantedState){
		this.buttonNumber = buttonNumber;
		this.wantedState = wantedState;
	}
	
	public FuelSorterStates getWantedState(){
		return wantedState;
	}
}
