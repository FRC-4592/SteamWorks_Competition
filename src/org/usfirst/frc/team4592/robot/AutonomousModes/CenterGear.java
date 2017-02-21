package org.usfirst.frc.team4592.robot.AutonomousModes;

import org.usfirst.frc.team4592.robot.Lib.Loopable;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain;
import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery;

public class CenterGear implements Loopable{
	private Drivetrain myDrive;
	private GearDelivery gearDelivery;
	private int counter = 0;
	
	public CenterGear(Drivetrain myDrive, GearDelivery gearDelivery){
		this.myDrive = myDrive;
		this.gearDelivery = gearDelivery;
	}
	
	@Override
	public void update(){
		if(counter >= 0 && counter < 450){
			gearDelivery.closeGear();
			myDrive.autoDrive(2);
		}else if(counter >= 600 && counter < 700){
			gearDelivery.openGear();
		}else if(counter >= 750){
			System.out.println("I'm in");
			myDrive.autoDrive(1);
		}
		
		counter++;
		System.out.println(counter);
	}
}