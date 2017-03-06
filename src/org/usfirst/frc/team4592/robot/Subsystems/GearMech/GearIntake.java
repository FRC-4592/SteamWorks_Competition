package org.usfirst.frc.team4592.robot.Subsystems.GearMech;

import org.usfirst.frc.team4592.robot.Constants;
import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;

import edu.wpi.first.wpilibj.VictorSP;

public class GearIntake extends SubsystemFramework{
	private VictorSP gearIntakeMotor;
	private GearIntakeState state = GearIntakeState.Off;
	
	public GearIntake(VictorSP gearIntakeMotor){
		this.gearIntakeMotor = gearIntakeMotor;
	}
	
	public enum GearIntakeState{
		Off, Intake, Reverse;
	}
	
	//Auto Control
	public void GearIntake_Off(){
		gearIntakeMotor.set(0);
	}
	
	public void GearIntake_Intake(){
		gearIntakeMotor.set(-1);
	}
	
	public void GearIntake_Reverse(){
		gearIntakeMotor.set(1);
	}
	
	//Telop Control
	@Override
	public void update(){
		GearIntakeState newState = state;
		
		switch(state){
			case Off:
				gearIntakeMotor.set(0);
				
				if(Hardware.driverPad.getRawAxis(Constants.GEARINTAKE_INTAKE) > 0.2){
					newState = GearIntakeState.Intake;
				}else if(Hardware.driverPad.getRawButton(Constants.GEARINTAKE_REVERSE)){
					newState = GearIntakeState.Reverse;
				}
				
				break;
			
			case Intake:
				gearIntakeMotor.set(0.6);
				
				if(Hardware.driverPad.getRawAxis(Constants.GEARINTAKE_INTAKE) <= 0.2){
					newState = GearIntakeState.Off;
				}
				
				break;
			
			case Reverse:
				gearIntakeMotor.set(-1);
				
				if(!Hardware.driverPad.getRawButton(Constants.GEARINTAKE_REVERSE)){
					newState = GearIntakeState.Off;
				}
				
				break;
			
			default:
				newState = GearIntakeState.Off;
				break;
		}
		
		if(newState != state){
			state = newState;
		}
	}

	@Override
	public void outputToSmartDashboard(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupSensors(){
		// TODO Auto-generated method stub
		
	}

}
