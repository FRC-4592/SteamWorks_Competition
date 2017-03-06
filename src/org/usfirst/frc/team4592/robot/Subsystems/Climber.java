package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Constants;
import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;

import edu.wpi.first.wpilibj.VictorSP;

public class Climber extends SubsystemFramework{
	private VictorSP rightClimberMotor;
	private VictorSP leftClimberMotor;
	private ClimberStates state = ClimberStates.Off;
	
	public Climber(VictorSP rightClimberMotor, VictorSP leftClimberMotor){
		this.rightClimberMotor = rightClimberMotor;
		this.leftClimberMotor = leftClimberMotor;
	}
	
	public enum ClimberStates{
		Climb, Off;
	}
	
	@Override
	public void update() {
		ClimberStates newState = state;
		
		switch(state){
			
			case Off:
				rightClimberMotor.set(0);
				leftClimberMotor.set(0);
				
				if(Hardware.driverPad.getRawButton(Constants.CLIMB)){
					newState = ClimberStates.Climb;
				}
	break;
	
			case Climb:
				//one of these will have to be negative
				rightClimberMotor.set(-1);
				leftClimberMotor.set(1);
				
				if(!Hardware.driverPad.getRawButton(Constants.CLIMB)){
					newState = ClimberStates.Off;
				}
	break;
	
			default:
				newState = ClimberStates.Off;
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
	}
}
