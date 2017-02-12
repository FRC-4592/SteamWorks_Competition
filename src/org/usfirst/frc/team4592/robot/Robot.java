package org.usfirst.frc.team4592.robot;

import org.usfirst.frc.team4592.robot.Lib.MultiLooper;
import org.usfirst.frc.team4592.robot.Subsystems.Climber;

import edu.wpi.first.wpilibj.IterativeRobot;

@SuppressWarnings("unused")
public class Robot extends IterativeRobot{
	private MultiLooper SSLooper = new MultiLooper("SSLooper", 1/200.0, false); //Subsystems looper
	private MultiLooper AutoLooper = new MultiLooper("AutoLooper", 1/200.0, false); //Auto looper
    
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit(){
    	//need to reset all encoders and set it up in intened way
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