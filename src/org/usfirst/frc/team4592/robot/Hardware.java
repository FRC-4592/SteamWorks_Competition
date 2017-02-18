package org.usfirst.frc.team4592.robot;

import org.usfirst.frc.team4592.robot.Button.DrivetrainButton;
import org.usfirst.frc.team4592.robot.Button.FuelDeliveryButton;
import org.usfirst.frc.team4592.robot.Button.FuelIntakeButton;
import org.usfirst.frc.team4592.robot.Button.FuelSorterButton;
import org.usfirst.frc.team4592.robot.Button.GearDeliveryButton;
import org.usfirst.frc.team4592.robot.Subsystems.Drivetrain.DrivetrainStates;
import org.usfirst.frc.team4592.robot.Subsystems.FuelDelivery.FuelDeliveryStates;
import org.usfirst.frc.team4592.robot.Subsystems.FuelIntake.FuelIntakeStates;
import org.usfirst.frc.team4592.robot.Subsystems.FuelSorter.FuelSorterStates;
import org.usfirst.frc.team4592.robot.Subsystems.GearDelivery.GearDeliveryStates;
import org.usfirst.frc.team4592.robot.Util.InfraredSensor;
import org.usfirst.frc.team4592.robot.Util.PixyCam;
import org.usfirst.frc.team4592.robot.Util.doubleSolenoid;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.VictorSP;

//This file holds all initialization for hardware and buttons

public class Hardware {
	//Drivetrain Motors
	public static final VictorSP rightMotor =
			new VictorSP(Constants.RIGHT_MOTOR_PWM);
	public static final VictorSP leftMotor =
			new VictorSP(Constants.LEFT_MOTOR_PWM);
	public static final CANTalon RightCANMotor =
			new CANTalon(Constants.RIGHT_MOTOR_CAN);
	public static final CANTalon leftCANMOTOR =
			new CANTalon(Constants.LEFT_MOTOR_CAN);
	
	//Drivetrain Pnuematics
	public static final doubleSolenoid shifter =
			new doubleSolenoid(Constants.SHIFTER_OPEN, Constants.SHIFTER_CLOSE);
	
	//Fuel Intake Motors
	public static final VictorSP fuelIntakeMotor =
			new VictorSP(Constants.FUEL_INTAKE_MOTOR_PWM);
	
	//Sorter Motors
	public static final CANTalon sorterMotor =
			new CANTalon(Constants.SORTER_MOTOR_CAN);
	
	//Fuel Delivery Motors
	public static final CANTalon fuelDeliveryMotor =
			new CANTalon(Constants.FUEL_DELIVERY_MOTOR_CAN);
	
	//Fuel Shooter Motors
	public static final CANTalon turretMotor =
			new CANTalon(Constants.SHOOTER_TURRET_MOTOR_CAN);
	public static final CANTalon hoodMotor =
			new CANTalon(Constants.SHOOTER_HOOD_MOTOR_CAN);
	public static final CANTalon shooterWheelCAN =
			new CANTalon(Constants.SHOOTER_WHEEL_MOTOR_CAN);
	
	//Gear Delivery
	public static final doubleSolenoid gearPiston =
			new doubleSolenoid(Constants.GEAR_DELIVERY_OPEN, Constants.GEAR_DELIVERY_CLOSE);
	
	//Climber
	public static final VictorSP climberCANMotor =
			new VictorSP(Constants.RIGHT_CLIMBER_MOTOR_PWM);
	public static final VictorSP climberMotor =
			new VictorSP(Constants.LEFT_CLIMBER_MOTOR_PWM);
	
	//Vision
	public static final PixyCam fuelShooterCam =
			new PixyCam(Constants.SHOOTER_CAM, Constants.SHOOTER_CAMERA_GOAL, Constants.SHOOTER_LED_RING_SET);
	public static final PixyCam pegCam =
			new PixyCam(Constants.PEG_CAM, Constants.PEG_CAMERA_GOAL, Constants.PEG_LED_RING_SET);
	
	//Gyro
	public static final ADXRS450_Gyro SpartanBoard =
			new ADXRS450_Gyro();
	
	//Analog Sensors
	
	//Sticks
	public static final Joystick driverPad = 
			new Joystick(Constants.DRIVE_USB_PORT);
	public static final Joystick operatorPad = 
			new Joystick(Constants.OPERATOR_USB_PORT);
	
	//Driver Buttons
		//Gear Delivery Buttons
			public static GearDeliveryButton [] gearDeliveryButtons = {
					new GearDeliveryButton(Constants.GEAR_DELIVERY_OPEN, GearDeliveryStates.Open),
					new GearDeliveryButton(Constants.GEAR_DELIVERY_CLOSE, GearDeliveryStates.Close)
			};
		// Drivetrain Buttons
			public static DrivetrainButton [] drivetrainButtons = {
					new DrivetrainButton(Constants.DRIVETRAIN_LOWGEAR, DrivetrainStates.LowGear),
					new DrivetrainButton(Constants.DRIVETRAIN_HIGHGEAR, DrivetrainStates.HighGear)
			};
	
	//Operator Buttons
		//Fuel Intake Buttons
			public static FuelIntakeButton [] fuelIntakeButtons = {
					new FuelIntakeButton(Constants.FUELINTAKE_PICKUP, FuelIntakeStates.PickUp),
					new FuelIntakeButton(Constants.FUELINTAKE_REVERSE, FuelIntakeStates.Reverse),
					new FuelIntakeButton(Constants.FUELINTAKE_STOP, FuelIntakeStates.Stop)
			};
		//Fuel Sorter Buttons
			public static FuelSorterButton [] fuelSorterButtons = {
					new FuelSorterButton(Constants.FUELSORTER_FORWARD, FuelSorterStates.Forward),
					new FuelSorterButton(Constants.FUELSORTER_REVERSE, FuelSorterStates.Reverse),
					new FuelSorterButton(Constants.FUELSORTER_STOP, FuelSorterStates.Stop)
			};
		//Fuel Delivery Buttons
			public static FuelDeliveryButton [] fuelDeliveryButtons = {
					new FuelDeliveryButton(Constants.FUELDELIVERY_PICKUP, FuelDeliveryStates.PickUp),
					new FuelDeliveryButton(Constants.FUELDELIVERY_REVERSE, FuelDeliveryStates.Reverse),
					new FuelDeliveryButton(Constants.FUELDELIVERY_STOP, FuelDeliveryStates.Stop)
			};
		
}