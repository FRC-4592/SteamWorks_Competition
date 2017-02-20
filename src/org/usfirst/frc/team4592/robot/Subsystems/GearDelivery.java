package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Constants;
import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.GearDeliveryButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;
import org.usfirst.frc.team4592.robot.Util.doubleSolenoid;

public class GearDelivery extends SubsystemFramework{
	private doubleSolenoid gearDoubleSolenoid;
	private GearDeliveryStates state = GearDeliveryStates.Close;
	
	public GearDelivery(doubleSolenoid gearDoubleSolenoid){
		this.gearDoubleSolenoid = gearDoubleSolenoid;
	}
	
	public enum GearDeliveryStates{
		Open, Close;
	}

	@Override
	public void update() {
		GearDeliveryStates newState = state;
		
		switch(state){
		
			case Open:
				gearDoubleSolenoid.open();
				
				if(Hardware.driverPad.getRawButton(Constants.GEARDELIVERY_CLOSE)){
					newState = GearDeliveryStates.Close;
				}
	break;

			case Close:
				gearDoubleSolenoid.close();
				
				if(Hardware.driverPad.getRawButton(Constants.GEARDELIVERY_OPEN)){
					newState = GearDeliveryStates.Open;
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

	@Override
	public void outputToSmartDashboard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupSensors() {
		// TODO Auto-generated method stub
		
	}
}