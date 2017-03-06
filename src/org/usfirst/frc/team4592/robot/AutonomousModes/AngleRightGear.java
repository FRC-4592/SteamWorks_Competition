package org.usfirst.frc.team4592.robot.AutonomousModes;

import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain;
//import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery;

public class AngleRightGear implements Loopable{
	private Drivetrain myDrive;
	//private GearDelivery gearDelivery;
	private int counter = 0;
	
	public AngleRightGear(Drivetrain myDrive){//, GearDelivery gearDelivery){
		this.myDrive = myDrive;
		//this.gearDelivery = gearDelivery;
	}
	
	@Override
	public void update() {
		if(counter >= 0 && counter < 500){
			//gearDelivery.closeGear();
			myDrive.autoDrive(2);
		}else if(counter >= 600 && counter <= 700){
			//gearDelivery.openGear();
		}else if(counter >= 750 && counter <= 950){
			myDrive.auto_90_power();
			myDrive.autoDrive(0.2);
		}else if(counter >= 980 && counter <= 1140){
			myDrive.teleopSetupMotors();
			myDrive.autoTurn(45.92);
		}else if(counter >= 1150 && counter <= 1160){
			myDrive.autoSetupMotors();
			myDrive.zeroEncoders();
		}else if(counter >= 1170 && counter <= 1560){
			myDrive.autoDrive(4);
		}
		
		counter++;
		System.out.println(counter);
		System.out.println(myDrive.get_GoalAngle());
	}

}
