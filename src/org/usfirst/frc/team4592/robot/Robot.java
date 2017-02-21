package org.usfirst.frc.team4592.robot;

import org.usfirst.frc.team4592.robot.AutonomousModes.CenterGear;
import org.usfirst.frc.team4592.robot.Lib.MultiLooper;
import org.usfirst.frc.team4592.robot.Subsystems.Climber;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain;
import org.usfirst.frc.team4592.robot.Subsystems.FuelIntake;
import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery;

import edu.wpi.first.wpilibj.IterativeRobot;

@SuppressWarnings("unused")
public class Robot extends IterativeRobot{
	//private MultiLooper DriveLooper = new MultiLooper("DriveLooper", 1/200.0, false);
	private MultiLooper DriveLooper = new MultiLooper("DriveLooper", 1/200.0, false);
	private MultiLooper SSLooper = new MultiLooper("SSLooper", 1/200.0, false); //Subsystems looper
	private MultiLooper AutoLooper = new MultiLooper("AutoLooper", 1/200.0, false); //Auto looper
	private Drivetrain myDrive = new Drivetrain(Hardware.rightMasterMotor, Hardware.rightSlaveMotor, Hardware.leftMasterMotor, Hardware.leftSlaveMotor, Hardware.shifter, Hardware.SpartanBoard, Constants.Average_Ticks_Per_Meter, Constants.Drive_ANGLE_Kp, Constants.Drive_ANGLE_Ki, Constants.Drive_Kf, Constants.Drive_Kp, Constants.Drive_Ki, Constants.Drive_Kd);
	private GearDelivery gearDelivery = new GearDelivery(Hardware.gearPiston);
	private Climber climb = new Climber(Hardware.rightClimberMotor, Hardware.leftClimberMotor);
	private FuelIntake intake = new FuelIntake(Hardware.fuelIntakeMotor);
	private CenterGear centerGear = new CenterGear(myDrive, gearDelivery);
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit(){
    	//need to reset all encoders and set it up in intended way
    	myDrive.setupSensors();
    	
    	DriveLooper.addLoopable(myDrive);
    	//SSLooper.addLoopable(myDrive);
    	SSLooper.addLoopable(gearDelivery);
    	SSLooper.addLoopable(climb);
    	SSLooper.addLoopable(intake);
    }
    
    /**
	 * This function is run when the robot is set into autonomous mode
	 * this used for any initialization code of autonomous. 
	 */
    public void autonomousInit(){
    	myDrive.autoSetupMotors();
    	AutoLooper.addLoopable(centerGear);
    	AutoLooper.start();
    	AutoLooper.update();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic(){
    	myDrive.outputToSmartDashboard();
    	
    	//myDrive.autoDrive(2);
    }
    
    public void teleopInit(){
    	//Start Control Loops
    		myDrive.teleopSetupMotors();
	    	SSLooper.start();
	    	DriveLooper.start();
	    	SSLooper.update();
	    	DriveLooper.update();
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
    
    public void disabledInit(){
    	AutoLooper.stop();
    }
    
}