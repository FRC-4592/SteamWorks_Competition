package org.usfirst.frc.team4592.robot;

import org.usfirst.frc.team4592.robot.AutonomousModes.AutoStraight;
import org.usfirst.frc.team4592.robot.AutonomousModes.UCenterGear;
import org.usfirst.frc.team4592.robot.Lib.MultiLooper;
import org.usfirst.frc.team4592.robot.Subsystems.Climber;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain;
//import org.usfirst.frc.team4592.robot.Subsystems.FuelIntake;
//import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery;
import org.usfirst.frc.team4592.robot.Subsystems.GearMech.FlipperPosition;
import org.usfirst.frc.team4592.robot.Subsystems.GearMech.GearIntake;
import org.usfirst.frc.team4592.robot.Subsystems.GearMech.GearLock;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

@SuppressWarnings("unused")
public class Robot extends IterativeRobot{
	//private MultiLooper DriveLooper = new MultiLooper("DriveLooper", 1/200.0, false);
	private MultiLooper DriveLooper = new MultiLooper("DriveLooper", 1/200.0, false);
	private MultiLooper SSLooper = new MultiLooper("SSLooper", 1/200.0, false); //Subsystems looper
	private MultiLooper AutoLooper = new MultiLooper("AutoLooper", 1/200.0, false); //Auto looper
	private Drivetrain myDrive = new Drivetrain(Hardware.rightMasterMotor, Hardware.rightSlaveMotor, Hardware.leftMasterMotor, Hardware.leftSlaveMotor, Hardware.shifter, Hardware.SpartanBoard, Constants.Average_Ticks_Per_Meter, Constants.Drive_ANGLE_Kp, Constants.Drive_ANGLE_Ki, Constants.Drive_Kf, Constants.Drive_Kp, Constants.Drive_Ki, Constants.Drive_Kd);
	//private GearDelivery gearDelivery = new GearDelivery(Hardware.gearPiston);
	private Climber climb = new Climber(Hardware.rightClimberMotor, Hardware.leftClimberMotor);
	private FlipperPosition flipperPosition = new FlipperPosition(Hardware.flipperPosition);
	private GearLock gearLock = new GearLock(Hardware.gearLock);
	private GearIntake gearIntake = new GearIntake(Hardware.gearIntakeMotor);
	private AutoStraight autoStraight = new AutoStraight(myDrive);
	//private FuelIntake intake = new FuelIntake(Hardware.fuelIntakeMotor);
	//private UCenterGear UCenterGear = new UCenterGear(myDrive, flipperPosition, gearLock);
	private SendableChooser<String> autoChooser = new SendableChooser();
	private SendableChooser<String> sideSelect = new SendableChooser();
	
	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit(){
    	//need to reset all encoders and set it up in intended way
    	myDrive.setupSensors();
    	  	
    	DriveLooper.addLoopable(myDrive);
    	//SSLooper.addLoopable(myDrive);
    	//SSLooper.addLoopable(gearDelivery);
    	SSLooper.addLoopable(climb);
    	SSLooper.addLoopable(flipperPosition);
    	SSLooper.addLoopable(gearLock);
    	SSLooper.addLoopable(gearIntake);
    	//SSLooper.addLoopable(intake);
    	
    	autoChooser.addDefault("Nothing", "1");
    	autoChooser.addObject("Center Gear Hook", "2");
    	autoChooser.addObject("Center Gear 45", "3");
    	
    	sideSelect.addDefault("Red", "1");
    	sideSelect.addObject("Blue", "2");
    	
    	SmartDashboard.putData("Auto Select", autoChooser);
    	SmartDashboard.putData("Side Select", sideSelect);
    }
    
    /**
	 * This function is run when the robot is set into autonomous mode
	 * this used for any initialization code of autonomous. 
	 */
    public void autonomousInit(){
    	/*if(autoChooser.getSelected() == "1"){
    		if(sideSelect.getSelected() == "1"){
    			//set side red
    		}else if(sideSelect.getSelected() == "2"){
    			//set side blue
    		}
    		AutoLooper.addLoopable(null);
    	}else if(autoChooser.getSelected() == "2"){
    		if(sideSelect.getSelected() == "1"){
    			//set side red
    		}else if(sideSelect.getSelected() == "2"){
    			//set side blue
    		}
    		
    		AutoLooper.addLoopable(null);
    	}else if(autoChooser.getSelected() == "3"){
    		if(sideSelect.getSelected() == "1"){
    			//set side red
    		}else if(sideSelect.getSelected() == "2"){
    			//set side blue
    		}
    		
    		AutoLooper.addLoopable(null);
    	}*/
    	
    	myDrive.autoSetupMotors();
    	//AutoLooper.addLoopable(UCenterGear);
    	AutoLooper.addLoopable(autoStraight);
    	//AutoLooper.addLoopable(UCenterGear);
    	AutoLooper.start();
    	AutoLooper.update();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic(){
    	myDrive.outputToSmartDashboard();
    	myDrive.resetSpartanBoard();
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
    	myDrive.resetSpartanBoard();
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