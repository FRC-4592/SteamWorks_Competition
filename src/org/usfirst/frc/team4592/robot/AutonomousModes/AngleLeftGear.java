package org.usfirst.frc.team4592.robot.AutonomousModes;

import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain;
//import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery;

public class AngleLeftGear implements Loopable{
	private Drivetrain myDrive;
	//private GearDelivery gearDelivery;
	private int counter = 0;
	
	@Override
	public void update(){
		if(counter >= 0 && counter < 500){
			//gearDelivery.closeGear();
			myDrive.autoDrive(2);
		}else if(counter >= 600 && counter <= 760){
			myDrive.teleopSetupMotors();
			myDrive.autoTurn(45.92);
		}else if(counter >= 750 && counter <= 1050){
			myDrive.autoSetupMotors();
			myDrive.zeroEncoders();
			myDrive.autoDrive(2);
		}else if(counter >= 1060 && counter <= 1220){
			myDrive.auto_90_power();
			myDrive.autoDrive(0);
		}else if(counter >= 1220 && counter <= 1380){
			myDrive.teleopSetupMotors();
			myDrive.autoTurn(0);
		}else if(counter >= 1390){
			myDrive.autoSetupMotors();
			myDrive.zeroEncoders();
			myDrive.autoDrive(4);
		}
		
		counter++;
		System.out.println(counter);
		System.out.println(myDrive.get_GoalAngle());
	}

}
