package frc.robot;

import edu.wpi.first.wpilibj.SerialPort;
public class Constants{
    
    //CAN ID's
    public static final int DRIVE_TRAIN_MTR_LEFT_FRONT  = 0;
    public static final int DRIVE_TRAIN_MTR_RIGHT_FRONT = 3;
    public static final int DRIVE_TRAIN_MTR_LEFT_BACK   = 1;
    public static final int DRIVE_TRAIN_MTR_RIGHT_BACK  = 2;
    public static final int ELEVATOR_MTR_1              = 4; 
    public static final int ELEVATOR_MTR_2              = 5;

    //PWM Ports
    public static final int INTAKE_MTR_1   = 0; // 1 works too
    public static final int INTAKE_MTR_2   = 1; // 2, 0, 3, 4, 8 doesn't work
    public static final int FORKLIFT_MTR   = 5; //WORKS!!!!! DO NOT CHANGE.
    public static final int FORKLIFT_SERVO = 3;

    //MXP Ports
    public static final SerialPort.Port  DRIVE_TRAIN_GYRO = SerialPort.Port.kMXP;

    //DIO Ports
    public static final int LIMIT_SWITCH_1 = 0; 
    public static final int LIMIT_SWITCH_2 = 1; 

    //Analog Ports
    public static final int ULTRASONIC_1 = 0; 
    public static final int ULTRASONIC_2 = 1; 

    // PCM Ports
    public static final int DOUBLE_SOLENOID_1 = 0;
    public static final int DOUBLE_SOLENOID_2 = 1; 

    // USB Ports
    public static final int XBOX_PORT = 2;
    public static final int JOYSTICK_PORT_1 = 0;
    public static final int JOYSTICK_PORT_2 = 1;

    //Subsystem calibrated constants
    public static final int ELEVATOR_TICKS_TO_DISTANCE = 0;

    public static final int kLongCANTimeoutMs = 10; //adjust
    public static final double DRIVE_VOLTAGE_RAMP_RATE = 10;
    public static final double INTAKE_VOLTAGE_RAMP_RATE = 10;
    public static final int kLooperDt = 100;

    public static final double FORKLIFT_SERVO_POWER = 0.4; // min power needed to keep servo up 

    public static final double ULTRASONIC_SENSOR_DISTANCE = 2; // calibrate
    // ^^ not even used...
}