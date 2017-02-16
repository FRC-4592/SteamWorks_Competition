package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.FuelIntakeButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;
import org.usfirst.frc.team4592.robot.Util.bangBang;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

public class FuelIntake extends SubsystemFramework{
	private FuelIntakeButton [] fuelIntakeButtons;
	private CANTalon fuelIntakeMotor;
	private FuelIntakeStates tempState;
	private FuelIntakeStates state = FuelIntakeStates.Stop;
	
	
	public FuelIntake(FuelIntakeButton [] fuelIntakeButtons, CANTalon fuelIntakeMotor){
		this.fuelIntakeButtons = fuelIntakeButtons;
		this.fuelIntakeMotor = fuelIntakeMotor;
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
				fuelIntakeMotor.set(1);
				
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

	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupSensors() {	
	}
}