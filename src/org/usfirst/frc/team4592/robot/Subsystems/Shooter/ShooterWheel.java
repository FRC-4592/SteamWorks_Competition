package org.usfirst.frc.team4592.robot.Subsystems.Shooter;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.ShooterWheelButton;
import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

@SuppressWarnings("unused")
public class ShooterWheel extends SubsystemFramework{
	private ShooterWheelButton [] shooterWheelButtons;
	private CANTalon shooterWheelMotor;
	private ShooterWheelStates tempState;
	private ShooterWheelStates state = ShooterWheelStates.Stop;
	
	public ShooterWheel(ShooterWheelButton [] shooterWheelButtons, CANTalon shooterWheelMotor,
			double ShooterWheel_Kf, double ShooterWheel_Kp, double ShooterWheel_Ki, double ShooterWheel_Kd){
		this.shooterWheelButtons = shooterWheelButtons;
		this.shooterWheelMotor = shooterWheelMotor;
		
		shooterWheelMotor.configNominalOutputVoltage(+0.0f, -0.0f);
		shooterWheelMotor.configPeakOutputVoltage(+12.0f, -12.0f);
		
		shooterWheelMotor.setProfile(0);
		shooterWheelMotor.setF(ShooterWheel_Kf);
		shooterWheelMotor.setP(ShooterWheel_Kp);
		shooterWheelMotor.setI(ShooterWheel_Ki);
		shooterWheelMotor.setD(ShooterWheel_Kd);
	}
	
	public enum ShooterWheelStates{
		Stop, CenterGear, SideGear, Hooper, Boiler;
	}
	
	public ShooterWheelStates buttonCheck(){
		for(int i = 0; i < shooterWheelButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(shooterWheelButtons[i].getButtonNumber())){
				return shooterWheelButtons[i].getWantedState();
			}
		}
		
		return null;
	}
	
	@Override
	public void update(){
		ShooterWheelStates newState = state;
		
		switch(state){
			case Stop:
				//Stop wheel
				shooterWheelMotor.changeControlMode(TalonControlMode.PercentVbus);
				shooterWheelMotor.set(0);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case CenterGear:
				//spin wheel at center gear rpm
				shooterWheelMotor.changeControlMode(TalonControlMode.Speed);
				shooterWheelMotor.set(4000);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case SideGear:
				//spin wheel at side gear rpm
				shooterWheelMotor.changeControlMode(TalonControlMode.Speed);
				shooterWheelMotor.set(4000);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Hooper:
				//spin wheel at hooper rpm
				shooterWheelMotor.changeControlMode(TalonControlMode.Speed);
				shooterWheelMotor.set(4000);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Boiler:
				//spin wheel at boiler rpm
				shooterWheelMotor.changeControlMode(TalonControlMode.Speed);
				shooterWheelMotor.set(4000);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			default:
				newState = ShooterWheelStates.Stop;
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