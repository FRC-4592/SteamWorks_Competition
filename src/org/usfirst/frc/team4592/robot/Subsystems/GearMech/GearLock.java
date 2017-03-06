package org.usfirst.frc.team4592.robot.Subsystems.GearMech;

import org.usfirst.frc.team4592.robot.Constants;
import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;
import org.usfirst.frc.team4592.robot.Util.doubleSolenoid;

public class GearLock extends SubsystemFramework{
	private doubleSolenoid gearLockPiston;
	private GearLockState state = GearLockState.Lock;
	
	public GearLock(doubleSolenoid gearLockPiston){
		this.gearLockPiston = gearLockPiston;
	}
	
	public enum GearLockState{
		Unlock, Lock;
	}
	
	//Auto Control
	public void GearLock_Unlock(){
		gearLockPiston.open();
	}
	
	public void GearLock_Lock(){
		gearLockPiston.close();
	}
	
	//Telop Control	
	@Override
	public void update() {
		GearLockState newState = state;
		
		switch(state){	
			case Unlock:
				gearLockPiston.open();
				
				if(Hardware.driverPad.getRawAxis(Constants.GEARLOCK) <= 0.2){
					newState = GearLockState.Lock;
				}
				
				break;
			
			case Lock:
				gearLockPiston.close();
				
				if(Hardware.driverPad.getRawAxis(Constants.GEARLOCK) > 0.2){
					newState = GearLockState.Unlock;
				}
				
				break;
				
			default:
				newState = GearLockState.Lock;
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