package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.GearDeliveryButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Util.doubleSolenoid;

public class GearDelivery implements Loopable{
	private GearDeliveryButton [] gearDeliveryButtons;
	private doubleSolenoid gearDoubleSolenoid;
	private GearDeliveryStates tempState;
	private GearDeliveryStates state = GearDeliveryStates.Close;
	
	public GearDelivery(GearDeliveryButton [] gearDeliveryButtons, doubleSolenoid gearDoubleSolenoid){
		this.gearDeliveryButtons = gearDeliveryButtons;
		this.gearDoubleSolenoid = gearDoubleSolenoid;
	}
	
	public enum GearDeliveryStates{
		Open, Close;
	}
	
	public GearDeliveryStates buttonCheck(){
		for(int i = 0; i < gearDeliveryButtons.length; i++){
			if(Hardware.drivePad.getRawButton(gearDeliveryButtons[i].getButtonNumber())){
				return gearDeliveryButtons[i].getWantedState();
			}
		}
		
		return null;
	}

	@Override
	public void update() {
		GearDeliveryStates newState = state;
		
		switch(state){
		
			case Open:
				gearDoubleSolenoid.open();
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;

			case Close:
				gearDoubleSolenoid.close();
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;			
	
			default:
				newState = GearDeliveryStates.Close;
	break;
		}
		
		if(newState != state){
			state = newState;
		}
	}
}