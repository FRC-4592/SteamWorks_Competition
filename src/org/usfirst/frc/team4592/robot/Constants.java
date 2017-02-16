package org.usfirst.frc.team4592.robot;

/* This file is used to hold constant robot values.
 * DO NOT DEFINE MOTOR PWM OR PID VALUES OUTSIDE OF THIS FILE!!
 */

public class Constants {
	//Drivetrain PWM Values
		public static final int RIGHT_MOTOR_PWM = 0;
		public static final int LEFT_MOTOR_PWM = 1;
	
	//Drivetrain CAN Values
		public static final int RIGHT_MOTOR_CAN = 0;
		public static final int LEFT_MOTOR_CAN = 1;
		
	//Drivetrain Pnuematics
		public static final int SHIFTER_OPEN = 0;
		public static final int SHIFTER_CLOSE = 1;
	
	//Fuel Intake CAN Values
		public static final int FUEL_INTAKE_MOTOR_CAN = 2;
	
	//Fuel Sorter  Values
		public static final int SORTER_MOTOR_CAN = 3; 
	
	//Fuel Delivery
		public static final int FUEL_DELIVERY_MOTOR_CAN = 4;
	
	//Fuel Shooter CAN Values
		public static final int SHOOTER_TURRET_MOTOR_CAN = 5;
		public static final int SHOOTER_HOOD_MOTOR_CAN = 6;
		public static final int SHOOTER_WHEEL_MOTOR_CAN = 7;
		
	//Gear Delivery Pnuematics
		public static final int GEAR_DELIVERY_CLOSE = 2;
		public static final int GEAR_DELIVERY_OPEN = 3;
		
	//Climber CAN Values
		public static final int CLIMBER_MOTOR_CAN = 8;
	
	//Climber PWM Values
		public static final int CLIMBER_MOTOR_PWM = 2;
		
	//Vision Camera
		public static final int PEG_CAM = 0;
		public static final int SHOOTER_CAM = 1;

	//Vision Lights	
		public static final int PEG_LED_RING_SET = 4;
		public static final int SHOOTER_LED_RING_SET = 5;
	
	//Vision Constants
		public static final double PEG_CAMERA_GOAL = 0;
		public static final double SHOOTER_CAMERA_GOAL = 0;
		
	//Analog Sensors Inputs
	
	//Stick USB Values
		public static final int DRIVE_USB_PORT = 0;
		public static final int OPERATOR_USB_PORT = 1;
	
	//Driver Buttons
		//Gear Delivery Buttons
			public static final int GEARDELIVERY_OPEN = 9;
			public static final int GEARDELIVERY_CLOSE = 10;
		//Drivetrain Buttons
			public static final int DRIVETRAIN_LOWGEAR = 0;
			public static final int DRIVETRAIN_HIGHGEAR = 1;
		
	//Operator Buttons
		//Fuel Intake Buttons
			public static final int FUELINTAKE_PICKUP = 0;
			public static final int FUELINTAKE_REVERSE = 1;
			public static final int FUELINTAKE_STOP = 2;
		//Fuel Sorter Buttons
			public static final int FUELSORTER_FORWARD = 3;
			public static final int FUELSORTER_REVERSE = 4;
			public static final int FUELSORTER_STOP = 5;
		//Fuel Delivery Buttons
			public static final int FUELDELIVERY_PICKUP = 6;
			public static final int FUELDELIVERY_REVERSE = 7;
			public static final int FUELDELIVERY_STOP = 8;
	
	//Drivetrain PI Gains
		public static final double Drive_ANGLE_Kp = 0.0675;
		public static final double Drive_ANGLE_Ki = 0.002;
		//needs to be fixed
		public static final double Average_RPM_Per_Meter = 0;
		public static final double Drive_Kp = 0;
		public static final double Drive_Ki = 0;
		
	//Fuel Shooter PI Gains
		//Turret
			public static final double Turret_Kp = 0;
			public static final double Turret_Ki = 0;
		//Hood
			public static final double Hood_Kp = 0;
			public static final double Hood_Ki = 0;
		//Shooter Wheel
			public static final double ShooterWheel_Kp = 0;
			public static final double ShooterWheel_Ki = 0;
}