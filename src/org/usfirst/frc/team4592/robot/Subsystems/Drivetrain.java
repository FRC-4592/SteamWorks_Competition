package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.DrivetrainButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;
import org.usfirst.frc.team4592.robot.Util.PID;
import org.usfirst.frc.team4592.robot.Util.PixyCam;
import org.usfirst.frc.team4592.robot.Util.doubleSolenoid;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

@SuppressWarnings("unused")
public class Drivetrain extends SubsystemFramework{
	private DrivetrainButton [] drivetrainButtons;
	private RobotDrive myRobot;
	private CANTalon leftCANMotor;
	private CANTalon rightCANMotor;
	private doubleSolenoid leftShifter;
	private doubleSolenoid rightShifter;
	private PixyCam pegCam;
	private ADXRS450_Gyro SpartanBoard;
	private double Average_RPM_Per_Meter;
	private PID Drive_Angle_PI;
	private PID Drive_PI;
	private double goal_RPM;
	private double goal_Angle = 0;
	private double goal_RPM_Error;
	private double goal_Angle_Error;
	private DrivetrainStates tempState;
	private DrivetrainStates state = DrivetrainStates.LowGear;
	
	public Drivetrain(DrivetrainButton [] drivetrainButtons, VictorSP leftMotor, CANTalon leftCANMotor, 
					VictorSP rightMotor, CANTalon rightCANMotor, doubleSolenoid leftShifter, doubleSolenoid rightShifter,
					PixyCam pegCam, ADXRS450_Gyro SpartanBoard){
		myRobot = new RobotDrive(leftMotor, leftCANMotor, rightMotor, rightCANMotor);
		this.drivetrainButtons = drivetrainButtons;
		this.leftCANMotor = leftCANMotor;
		this.rightCANMotor = rightCANMotor;
		this.leftShifter = leftShifter;
		this.rightShifter = rightShifter;
		this.pegCam = pegCam;
		this.SpartanBoard = SpartanBoard;
	}
	
	public Drivetrain(DrivetrainButton [] drivetrainButtons, VictorSP leftMotor, CANTalon leftCANMotor, 
				VictorSP rightMotor, CANTalon rightCANMotor, doubleSolenoid leftShifter, doubleSolenoid rightShifter, 
				PixyCam pegCam,	ADXRS450_Gyro SpartanBoard,	double Average_RPM_Per_Meter, 
				double Drive_Angle_Kp, double Drive_Angle_Ki, double Drive_Kp, double Drive_Ki){
		myRobot = new RobotDrive(leftMotor, leftCANMotor, rightMotor, rightCANMotor);
		this.drivetrainButtons = drivetrainButtons;
		this.leftCANMotor = leftCANMotor;
		this.rightCANMotor = rightCANMotor;
		this.leftShifter = leftShifter;
		this.rightShifter = rightShifter;
		this.pegCam = pegCam;
		this.SpartanBoard = SpartanBoard;
		this.Average_RPM_Per_Meter = Average_RPM_Per_Meter;
		this.Drive_Angle_PI = new PID(Drive_Angle_Kp, Drive_Angle_Kp);
		this.Drive_PI = new PID(Drive_Kp, Drive_Ki); 
	}
	
	public enum DrivetrainStates{
		LowGear, HighGear, Visioning;
	}
	
	//method is called by auto modes to tell the robot how far to drive
	public double autoDrive(double amtToDrive){
		goal_RPM = amtToDrive * Average_RPM_Per_Meter;
		
		double currentRPM = getRPM();
		
		goal_RPM_Error = goal_RPM - currentRPM;
		goal_Angle_Error = goal_Angle - SpartanBoard.getAngle();
		
		myRobot.arcadeDrive(Drive_PI.getOutputP(goal_RPM_Error), Drive_Angle_PI.getOutputP(goal_Angle_Error));		
		
		//need to subtract returned value from amtToDrive,
		//maybe done from this method or classes calling this method
		return currentRPM/Average_RPM_Per_Meter;
	}
	
	//method is called by auto modes to tell the robot to turn to a certain degree
	public void autoTurn(int wantedDegree){
		goal_Angle = wantedDegree;
		
		goal_Angle_Error = goal_Angle - SpartanBoard.getAngle();
		
		myRobot.arcadeDrive(0, Drive_Angle_PI.getOutputP(goal_Angle_Error));
	}
	
	public void autoTurn(double wantedDegree){
		goal_Angle = wantedDegree;
		
		goal_Angle_Error = goal_Angle - SpartanBoard.getAngle();
		
		myRobot.arcadeDrive(0, Drive_Angle_PI.getOutputP(goal_Angle_Error));
	}
	
	public double getRPM(){
		return ((leftCANMotor.getSpeed() + rightCANMotor.getSpeed()) / 2);
	}
	
	public DrivetrainStates buttonCheck(){
		for(int i = 0; i < drivetrainButtons.length; i++){
			if(Hardware.operatorPad.getRawButton(drivetrainButtons[i].getButtonNumber())){
				return drivetrainButtons[i].getWantedState();
			}
		}
		
		return null;
	}
	
	@Override
	public void update(){
		DrivetrainStates newState = state;
		
		switch(state){
			case LowGear:
				leftShifter.close();
				rightShifter.close();
				
				myRobot.arcadeDrive(Hardware.driverPad.getY(), (Hardware.driverPad.getRawAxis(4))*-1, false);
				
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case HighGear:
				leftShifter.open();
				rightShifter.open();
				
				myRobot.arcadeDrive(Hardware.driverPad.getY(), (Hardware.driverPad.getRawAxis(4))*-1, false);
	
				tempState = buttonCheck();
				
				if(tempState != null || tempState != newState){
					newState = tempState;
				}
	break;
	
			case Visioning:
				leftShifter.close();
				rightShifter.close();
				
				myRobot.arcadeDrive(0, (pegCam.getAnalogOutput(SpartanBoard.getAngle())));
	break;
	
			default:
				newState = DrivetrainStates.LowGear;
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
		leftCANMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightCANMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	}
}