
public class Constants{
    
    //CAN ID's
    public static final int DRIVE_TRAIN_MTR_LEFT_FRONT  = 0;
    public static final int DRIVE_TRAIN_MTR_RIGHT_FRONT = 1;
    public static final int DRIVE_TRAIN_MTR_LEFT_BACK   = 2;
    public static final int DRIVE_TRAIN_MTR_RIGHT_BACK  = 3;
    public static final int ELEVATOR_MTR_1              = 4; 
    public static final int ELEVATOR_MTR_2              = 5;
    public static final int PDP_Module                  = 1; 

    //PWM Ports
    public static final int INTAKE_MTR_1  = 0;

    //MXP Ports
    public static final int DRIVE_TRAIN_GYRO = 1;

    //DIO Ports
    public static final int LIMIT_SWITCH_1 = 0;
    public static final int LIMIT_SWITCH_2 = 1;

    //Analog Ports
    public static final int ULTRASONIC_1 = 0; 
    public static final int ULTRASONIC_2 = 1;

    // PCM Ports
    public static final int DOUBLE_SOLENOID_1 = 0;
    public static final int DOUBLE_SOLENOID_2 = 1; 

    //Subsystem calibrated constants
    public static final int ELEVATOR_TICKS_TO_DISTANCE = 0;

    public static final int MAX_CAN_TIMEOUT = 10; // adjust
    public static final double DRIVE_VOLTAGE_RAMP_RATE = 10;
    public static final double INTAKE_VOLTAGE_RAMP_RATE = 10;


}