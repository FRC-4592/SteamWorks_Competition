package org.usfirst.frc.team4592.robot;

/* This file is used to hold constant robot values.
 * DO NOT DEFINE MOTOR PWM OR PID VALUES OUTSIDE OF THIS FILE!!
 */

public class Constants {
	//Drivetrain CAN Values
		public static final int RIGHT_MOTOR_MASTER_CAN = 11;
		public static final int RIGHT_MOTOR_SLAVE_CAN = 10;
		public static final int LEFT_MOTOR_MASTER_CAN = 5;
		public static final int LEFT_MOTOR_SLAVE_CAN = 6;
		
	//Drivetrain Pnuematics
		public static final int SHIFTER_OPEN = 1;
		public static final int SHIFTER_CLOSE = 0;
	
	//Fuel Intake PWM Values
		public static final int FUEL_INTAKE_MOTOR_PWM = 9;
	
	//Fuel Sorter  Values
		public static final int SORTER_MOTOR_CAN = 2; 
	
	//Fuel Delivery
		public static final int FUEL_DELIVERY_MOTOR_CAN = 3;
	
	//Fuel Shooter CAN Values
		public static final int SHOOTER_TURRET_MOTOR_CAN = 4;
		public static final int SHOOTER_HOOD_MOTOR_CAN = 7;
		public static final int SHOOTER_WHEEL_MOTOR_CAN = 6;
		
	//Gear Delivery Pnuematics
		public static final int GEAR_DELIVERY_CLOSE = 3;
		public static final int GEAR_DELIVERY_OPEN = 2;
		
	//Climber PWM Values
		public static final int RIGHT_CLIMBER_MOTOR_PWM = 8;	
		public static final int LEFT_CLIMBER_MOTOR_PWM = 7;
		
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
			public static final int GEARDELIVERY_OPEN = 3;
			public static final int GEARDELIVERY_CLOSE = 4;
		//Drivetrain Buttons
			public static final int DRIVETRAIN_LOWGEAR = 1;
			public static final int DRIVETRAIN_HIGHGEAR = 2;
		//Climber Button
			public static final int CLIMB = 6;
		
	//Operator Buttons
		//Fuel Intake Buttons
			public static final int FUELINTAKE_PICKUP = 7;
			public static final int FUELINTAKE_REVERSE = 8;
			public static final int FUELINTAKE_STOP = 5;
		//Fuel Sorter Buttons
			public static final int FUELSORTER_FORWARD = 4;
			public static final int FUELSORTER_REVERSE = 5;
			public static final int FUELSORTER_STOP = 6;
		//Fuel Delivery Buttons
			public static final int FUELDELIVERY_PICKUP = 7;
			public static final int FUELDELIVERY_REVERSE = 8;
			public static final int FUELDELIVERY_STOP = 9;
	
	//Drivetrain PI Gains
		public static final double Drive_ANGLE_Kp = 0; //0.0675;
		public static final double Drive_ANGLE_Ki = 0; //0.002;
		//needs to be fixed
		public static final double Average_Ticks_Per_Meter = 3.110508;
		public static final double Drive_Kf = 0;
		public static final double Drive_Kp = 0.7;
		public static final double Drive_Ki = 0;
		public static final double Drive_Kd = 3.5;
		
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