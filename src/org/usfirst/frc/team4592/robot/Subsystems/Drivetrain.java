package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Constants;
import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Button.DrivetrainButton;
import org.usfirst.frc.team4592.robot.Lib.SubsystemFramework;
import org.usfirst.frc.team4592.robot.Util.PID;
import org.usfirst.frc.team4592.robot.Util.PixyCam;
import org.usfirst.frc.team4592.robot.Util.doubleSolenoid;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")
public class Drivetrain extends SubsystemFramework{
	private RobotDrive myRobot;
	private CANTalon rightMasterMotor;
	private CANTalon rightSlaveMotor;
	private CANTalon leftMasterMotor;
	private CANTalon leftSlaveMotor;
	private doubleSolenoid shifter;
	private ADXRS450_Gyro SpartanBoard;
	private double Average_Counts_Per_Meter;
	private PID Drive_Angle_PI;
	private PID Drive_PI;
	private double goal_RPM;
	private double goal_Angle = 0;
	private double goal_RPM_Error;
	private double goal_Angle_Error;
	//private double angle;
	private DrivetrainStates state = DrivetrainStates.LowGear;
	
	//VictorSP and Talon SRX drivetrain
	public Drivetrain(VictorSP rightMotor, CANTalon rightMasterMotor,
					VictorSP leftMotor, CANTalon leftMasterMotor, doubleSolenoid shifter,
					ADXRS450_Gyro SpartanBoard){
		myRobot = new RobotDrive(leftMotor, leftMasterMotor, rightMotor, rightMasterMotor);
		this.rightMasterMotor = rightMasterMotor;
		this.leftMasterMotor = leftMasterMotor;
		this.shifter = shifter;
		this.SpartanBoard = SpartanBoard;
	}
	
	public Drivetrain(CANTalon rightMasterMotor, CANTalon rightSlaveMotor,
					CANTalon leftMasterMotor, CANTalon leftSlaveMotor, doubleSolenoid shifter,
					ADXRS450_Gyro SpartanBoard){
		myRobot = new RobotDrive(leftMasterMotor, leftSlaveMotor, rightMasterMotor, rightSlaveMotor);
		this.rightMasterMotor = rightMasterMotor;
		this.rightSlaveMotor = rightSlaveMotor;
		this.leftMasterMotor = leftMasterMotor;
		this.leftSlaveMotor = leftSlaveMotor;
		this.shifter = shifter;
		this.SpartanBoard = SpartanBoard;
	}
	
	/*public Drivetrain(DrivetrainButton [] drivetrainButtons, VictorSP rightMotor, CANTalon rightCANMotor,
				VictorSP leftMotor, CANTalon leftCANMotor, doubleSolenoid shifter, 
				PixyCam pegCam,	ADXRS450_Gyro SpartanBoard,	double Average_RPM_Per_Meter, 
				double Drive_Angle_Kp, double Drive_Angle_Ki, double Drive_Kp, double Drive_Ki){
		myRobot = new RobotDrive(leftMotor, leftCANMotor, rightMotor, rightCANMotor);
		this.drivetrainButtons = drivetrainButtons;
		this.rightCANMotor = rightCANMotor;
		this.leftCANMotor = leftCANMotor;
		this.shifter = shifter;
		this.pegCam = pegCam;
		this.SpartanBoard = SpartanBoard;
		this.Average_Counts_Per_Meter = Average_RPM_Per_Meter;
		this.Drive_Angle_PI = new PID(Drive_Angle_Kp, Drive_Angle_Kp);
		this.Drive_PI = new PID(Drive_Kp, Drive_Ki); 
	}*/
	
	public enum DrivetrainStates{
		LowGear, HighGear;
	}
	
	//method is called by auto modes to tell the robot how far to drive
	//public double autoDrive(double amtToDrive){
		
		/*goal_RPM = amtToDrive * Average_Counts_Per_Meter;
		
		double currentPosition = getPosition();
		
		goal_RPM_Error = goal_RPM - currentPosition;
		goal_Angle_Error = goal_Angle - SpartanBoard.getAngle();
		
		myRobot.arcadeDrive(Drive_PI.getOutputP(goal_RPM_Error), Drive_Angle_PI.getOutputP(0));		
		
		//need to subtract returned value from amtToDrive,
		//maybe done from this method or classes calling this method
		return currentPosition/Average_Counts_Per_Meter;*/
	//}
	
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
	
	public double getPosition(){
		return ((rightMasterMotor.getPosition() + leftMasterMotor.getPosition()) / 2);
	}
	
	@Override
	public void update(){
		DrivetrainStates newState = state;
		//angle = SpartanBoard.getAngle();
		
		switch(state){
			case LowGear:
				shifter.close();
				
				myRobot.arcadeDrive(Hardware.driverPad.getRawAxis(1)*-1, (Hardware.driverPad.getRawAxis(4))*-1, false);
				
				if(Hardware.driverPad.getRawButton(Constants.DRIVETRAIN_HIGHGEAR)){
					newState = DrivetrainStates.HighGear;
				}
				break;
	
			case HighGear:
				shifter.open();
				
				myRobot.arcadeDrive(Hardware.driverPad.getRawAxis(1)*-1, (Hardware.driverPad.getRawAxis(4))*-1, false);
	
				if(Hardware.driverPad.getRawButton(Constants.DRIVETRAIN_LOWGEAR)){
					newState = DrivetrainStates.LowGear;
				}
				break;
	
			default:
				newState = DrivetrainStates.LowGear;
				break;
		}
		
		if(newState != state){
			state = newState;
		}
		
		outputToSmartDashboard();
	}

	@Override
	public void outputToSmartDashboard() {
		
		SmartDashboard.putNumber("Angle", SpartanBoard.getAngle());
		SmartDashboard.putNumber("Right Position", rightMasterMotor.getPosition());
		SmartDashboard.putNumber("Left Position", leftMasterMotor.getPosition());
		System.out.println("Right Position: " + rightMasterMotor.getPosition());
		System.out.println("Left Position: " + leftMasterMotor.getPosition());
	}

	@Override
	public void setupSensors() {
		myRobot.setSafetyEnabled(false);
		rightMasterMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftMasterMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		}
}