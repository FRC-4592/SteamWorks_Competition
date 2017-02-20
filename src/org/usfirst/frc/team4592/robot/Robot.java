package org.usfirst.frc.team4592.robot;

import org.usfirst.frc.team4592.robot.Lib.MultiLooper;
import org.usfirst.frc.team4592.robot.Subsystems.Climber;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain;
import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery;

import edu.wpi.first.wpilibj.IterativeRobot;

@SuppressWarnings("unused")
public class Robot extends IterativeRobot{
	private MultiLooper SSLooper = new MultiLooper("SSLooper", 1/200.0, false); //Subsystems looper
	private MultiLooper AutoLooper = new MultiLooper("AutoLooper", 1/200.0, false); //Auto looper
	private Drivetrain myDrive = new Drivetrain(Hardware.drivetrainButtons, Hardware.rightMotor, Hardware.RightCANMotor, Hardware.leftMotor, Hardware.leftCANMOTOR, Hardware.shifter, Hardware.pegCam, Hardware.SpartanBoard, Constants.Average_Counts_Per_Meter, Constants.Drive_ANGLE_Kp, Constants.Drive_ANGLE_Ki, Constants.Drive_Kp, Constants.Drive_Ki);
	private GearDelivery gearDelivery = new GearDelivery(Hardware.gearPiston);
	private Climber climb = new Climber(Hardware.rightClimberMotor, Hardware.leftClimberMotor);
	private int counter = 0;
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit(){
    	//need to reset all encoders and set it up in intened way
    	myDrive.setupSensors();
    	
    	SSLooper.addLoopable(myDrive);
    	SSLooper.addLoopable(gearDelivery);
    	SSLooper.addLoopable(climb);
    	SSLooper.start();
    }
    
    /**
	 * This function is run when the robot is set into autonomous mode
	 * this used for any initialization code of autonomous. 
	 */
    public void autonomousInit(){
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic(){
    	
    }
    
    public void teleopInit(){
    	//Start Control Loops
	    	SSLooper.start();
	    	SSLooper.update();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic(){
    	myDrive.autoDrive(-(2.84-.9));
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic(){
    
    }
    
}