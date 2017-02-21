package org.usfirst.frc.team4592.robot;

import org.usfirst.frc.team4592.robot.Lib.MultiLooper;
import org.usfirst.frc.team4592.robot.Subsystems.Climber;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain;
import org.usfirst.frc.team4592.robot.Subsystems.FuelIntake;
import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

@SuppressWarnings("unused")
public class Robot extends IterativeRobot{
	//private MultiLooper DriveLooper = new MultiLooper("DriveLooper", 1/200.0, false);
	private MultiLooper DriveLooper = new MultiLooper("DriveLooper", 1/200.0, false);
	private MultiLooper SSLooper = new MultiLooper("SSLooper", 1/200.0, false); //Subsystems looper
	private MultiLooper AutoLooper = new MultiLooper("AutoLooper", 1/200.0, false); //Auto looper
	private Drivetrain myDrive = new Drivetrain(Hardware.rightMasterMotor, Hardware.rightSlaveMotor, Hardware.leftMasterMotor, Hardware.leftSlaveMotor, Hardware.shifter, Hardware.SpartanBoard);//, Constants.Average_Counts_Per_Meter, Constants.Drive_ANGLE_Kp, Constants.Drive_ANGLE_Ki, Constants.Drive_Kp, Constants.Drive_Ki);
	private GearDelivery gearDelivery = new GearDelivery(Hardware.gearPiston);
	private Climber climb = new Climber(Hardware.rightClimberMotor, Hardware.leftClimberMotor);
	private FuelIntake intake = new FuelIntake(Hardware.fuelIntakeMotor);
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit(){
    	//need to reset all encoders and set it up in intended way
    	myDrive.setupSensors();
    	
    	//DriveLooper.addLoopable(myDrive);
    	SSLooper.addLoopable(myDrive);
    	SSLooper.addLoopable(gearDelivery);
    	SSLooper.addLoopable(climb);
    	SSLooper.addLoopable(intake);
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
    	//myDrive.autoDrive(-(1.94));
    	//myDrive.outputToSmartDashboard();
    }
    
    public void teleopInit(){
    	//Start Control Loops
	    	SSLooper.start();
	    	//DriveLooper.start();
	    	SSLooper.update();
	    	//DriveLooper.update();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic(){
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic(){
    
    }
    
}