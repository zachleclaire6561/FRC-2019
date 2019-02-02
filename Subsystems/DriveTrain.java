//  This is the class for the Drive Train. It will contain all of the functions the drive train will us
package frc.robot.Subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.pheonix.motorcontrol.StatusFrameEnhanced;
import com.kauailabs.navx.frc.AHRS;
//import com.ctre.phoenix.motorcontrol.can.;

public class DriveTrain extends Subsystems{
    

    private TalonSRX leftMaster;
    private TalonSRX rightMaster;
    private VictorSPX leftSlave;
    private VictorSPX rightSlave;
    private AHRS gyro;


    enum DriveState {
        Loading,
        Disk,
        Aligning,
        Drive,
        Parked
    }

    public void configureTalon(TalonSRX talon, boolean left){
        talon.setStatusFrameEnhanced(StatusFrameEnhanced.Status_2_Feedback, 5, 100);
        ErrorCode sensor = talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MafEncoder_Relative, 0, 100); 
        if(sensor != ErrorCode.OK){
            //Log this issue
        }
        talon.setInverted(!left);
        talon.enableVoltageCompensation(true);
        talon.configVoltageCompSaturation(12.0, Constants.MAX_CAN_TIMEOUT);
        talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, Constants.MAX_CAN_TIMEOUT);
        talon.configVelocityMeasurementPeriod( 1 , Constants.MAX_CAN_TIMEOUT);
        talon.configClosedloopRamp(Constants.DRIVE_VOLTAGE_RAMP_RATE, Constants.MAX_CAN_TIMEOUT);
        talon.configNeutralDeadband(Constants.DRIVE_NEUTRAL_DEADBAND, 5);
    }

    public DriveTrain(){
        talon1 = TalonSRXFactory.createDefaultTalon(Constants.LEFT_MASTER_ID);
        talon1.configureTalon(talon1, true);

        victorSlave1 = new WPI_VictorSPX(v1Port);
        victorSlave1 = 
        talon2 = TalonSRXFactory.createDefaultTalon(Constants.RIGHT_MASTER_ID); 
        

        
        victorSlave2 = new WPI_VictorSPX(v2Port);
    }

    

    @Override
    public void zeroSensors(){

    }

    @Override
    public void stop(){
        brake();
    }
    
    @Override
    public void displaySmartDashBoard(){
        
    }

    public void tankDrive(double speed1, double speed2){
        talon1.set(speed1);
        talon2.set(speed2);
        victor1.set(speed1);
        victor2.set(speed2); 
    }

    public void brake(){
        talon1.setNeutralMode(NeutralMode.Brake);
        talon2.setNeutralMode(NeutralMode.Brake);
        victor1.setNeutralMode(NeutralMode.Brake);
        victor1.setNeutralMode(NeutralMode.Brake);
    }

    public void coast(){
        talon1.setNeutralMode(NeutralMode.Coast);
        talon2.setNeutralMode(NeutralMode.Coast);
        victor1.setNeutralMode(NeutralMode.Coast);
        victor2.setNeutralMode(NeutralMode.Coast);
    }

}

/*
alignment of drive base: 
1. check for vision tape
2. 



*/

