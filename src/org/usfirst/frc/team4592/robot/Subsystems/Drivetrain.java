package org.usfirst.frc.team4592.robot.Subsystems;

import org.usfirst.frc.team4592.robot.Hardware;
import org.usfirst.frc.team4592.robot.Lib.Loopable;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;


public class Drivetrain implements Loopable{
	private RobotDrive myRobot;
	private AnalogInput pegCam;
	
	public Drivetrain(VictorSP leftMotor, CANTalon leftCANMotor, 
						VictorSP rightMotor, CANTalon rightCANMotor, AnalogInput pegCam){
		myRobot = new RobotDrive(leftMotor, leftCANMotor, rightMotor, rightCANMotor);
		this.pegCam = pegCam;
	}
	
	//method is called by auto modes to tell the robot how far to drive
	public void autoDrive(int amtToDrive){
		
	}
	
	//method is called by auto modes to tell the robot to turn to a certain degreee
	public void autoTurn(int wantedDegree){
		
	}
	
	@Override
	public void update() {
		myRobot.arcadeDrive(Hardware.drivePad.getY(), (Hardware.drivePad.getRawAxis(4))*-1, false);
	}
}