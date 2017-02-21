package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Constants;
import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.FuelIntakeButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;

import edu.wpi.first.wpilibj.VictorSP;

public class FuelIntake extends SubsystemFramework{
	private VictorSP fuelIntakeMotor;
	private FuelIntakeStates state = FuelIntakeStates.Stop;
	
	public FuelIntake(VictorSP fuelIntakeMotor){
		this.fuelIntakeMotor = fuelIntakeMotor;
	}
	
	public enum FuelIntakeStates{
		PickUp, Reverse, Stop;
	}

	@Override
	public void update(){
		FuelIntakeStates newState = state;
		
		switch(state){
		
			case PickUp:
				fuelIntakeMotor.set(1);
				
				if(Hardware.driverPad.getRawButton(Constants.FUELDELIVERY_REVERSE)){
					newState = FuelIntakeStates.Reverse;
				}else if(Hardware.driverPad.getRawButton(Constants.FUELINTAKE_STOP)){
					newState = FuelIntakeStates.Stop;
				}
	break;
	
			case Reverse:
				fuelIntakeMotor.set(-0.75);
				
				if(Hardware.driverPad.getRawButton(Constants.FUELINTAKE_PICKUP)){
					newState = FuelIntakeStates.PickUp;
				}else if(Hardware.driverPad.getRawButton(Constants.FUELINTAKE_STOP)){
					newState = FuelIntakeStates.Stop;
				}
	break;
	
			case Stop:
				fuelIntakeMotor.set(0);
				
				if(Hardware.driverPad.getRawButton(Constants.FUELINTAKE_PICKUP)){
					newState = FuelIntakeStates.PickUp;
				}else if(Hardware.driverPad.getRawButton(Constants.FUELDELIVERY_REVERSE)){
					newState = FuelIntakeStates.Reverse;
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