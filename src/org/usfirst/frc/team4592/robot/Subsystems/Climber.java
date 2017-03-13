package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Constants;
import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;

import edu.wpi.first.wpilibj.VictorSP;

public class Climber extends SubsystemFramework{
	//Hardware
	private VictorSP rightClimberMotor;
	private VictorSP leftClimberMotor;
	
	//Climber State
	private ClimberStates state = ClimberStates.Off;
	
	//Climber Constructor
	public Climber(VictorSP rightClimberMotor, VictorSP leftClimberMotor){
		//VictorSP
		this.rightClimberMotor = rightClimberMotor;
		this.leftClimberMotor = leftClimberMotor;
	}
	
	//Climber States
	//Off Tells VictorSP To Provide 0 Power;
	//Due to 300:1 Ratio Climber Keeps Going For Awhile
	//Climb Tells VictorSP To Provide Full Power
	public enum ClimberStates{
		Off, Climb;
	}
	
	//Teleop Control
	@Override
	public void update() {
		ClimberStates newState = state;
		
		switch(state){
			
			case Off:
				//Tell Motor Controllers To Give 0 Power
				rightClimberMotor.set(0);
				leftClimberMotor.set(0);
				
				//Checks For Button Press To Switch To Climb State
				if(Hardware.driverPad.getRawButton(Constants.CLIMB)){
					newState = ClimberStates.Climb;
				}
	break;
	
			case Climb:
				//Tell Motor Controllers To Give Full Power
				rightClimberMotor.set(-1);
				leftClimberMotor.set(1);
				
				//Checks That Climb Button Isn't Being Pressed To Shut Off Climber
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
