//  This is the class for the Drive Train. It will contain all of the functions the drive train will us
package frc.robot.subsystems;

import frc.lib.drivers.motorcontrollers.TalonSRXFactory;
import frc.lib.drivers.motorcontrollers.VictorSPXFactory;
import frc.robot.loops.Looper;
import frc.robot.loops.Loop;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.ErrorCode;

public class DriveTrain extends Subsystems{
    private TalonSRX talon1;
    private TalonSRX talon2;
    private VictorSPX victor1;
    private VictorSPX victor2;

    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
            synchronized(DriveTrain.this){

            }
        }

        @Override 
        public void onLoop(double timeStamp){
            synchronized(DriveTrain.this){

            }
        }

        @Override 
        public void onStop(double timeStamp){
            synchronized(DriveTrain.this){

            }
        }

    };

    // add Gyro here

    public enum DriveState {
        BRAKE, 
        STOP,
        TELEOP, 
        AUTO
    }

    private DriveState state = DriveState.STOP; 
    public static DriveTrain driveTrainInstance = null;

    private void configureMaster(TalonSRX talon, boolean left) {
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        final ErrorCode sensorPresent = talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100); 
            if (sensorPresent != ErrorCode.OK) {
                // display somewhere
            }
        talon.setInverted(!left);
        talon.setSensorPhase(true);
        talon.enableVoltageCompensation(true);
        talon.configVoltageCompSaturation(12.0, Constants.kLongCANTimeoutMs);
        talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, Constants.kLongCANTimeoutMs);
        talon.configVelocityMeasurementWindow(1, Constants.kLongCANTimeoutMs);
        talon.configClosedloopRamp(Constants.DRIVE_VOLTAGE_RAMP_RATE, Constants.kLongCANTimeoutMs);
        talon.configNeutralDeadband(0.04, 0);
    }

    public DriveTrain(){
        talon1  = TalonSRXFactory.createDefaultTalon(Constants.DRIVE_TRAIN_MTR_LEFT_FRONT);
        configureMaster(talon1, true);

        victor1 = VictorSPXFactory.createPermanentSlaveVictor(Constants.DRIVE_TRAIN_MTR_LEFT_BACK, Constants.DRIVE_TRAIN_MTR_LEFT_FRONT);
        victor1.setInverted(false);

        talon2  = TalonSRXFactory.createDefaultTalon(Constants.DRIVE_TRAIN_MTR_RIGHT_FRONT);
        configureMaster(talon1, false);
     
        victor2 = VictorSPXFactory.createPermanentSlaveVictor(Constants.DRIVE_TRAIN_MTR_RIGHT_BACK, Constants.DRIVE_TRAIN_MTR_RIGHT_FRONT);
        victor2.setInverted(true);
    }

    public DriveState getState(){
        return state;
    }

    public static DriveTrain getInstance(){
        if(driveTrainInstance == null){
            driveTrainInstance = new DriveTrain();
        }
        return driveTrainInstance;
    }

    @Override
    public void zeroSensors(){
        talon1.setSelectedSensorPosition(0, 0, 0);
        talon2.setSelectedSensorPosition(0, 0, 0);
    }

    @Override
    public void stop(){
        coast();
        zeroSensors();
    }

    @Override 
    public void registerLoop(Looper looper){
        looper.register(loop);
    }
    
    @Override
    public void displaySmartDashBoard(){
        
    }

    public void tankDrive(double speed1, double speed2){
        talon1.set(ControlMode.PercentOutput, speed1);
        talon2.set(ControlMode.PercentOutput, speed2);
    }

    public synchronized void brake(){
        talon1.setNeutralMode(NeutralMode.Brake);
        talon2.setNeutralMode(NeutralMode.Brake);
    }

    public synchronized void coast(){
        talon1.setNeutralMode(NeutralMode.Coast);
        talon2.setNeutralMode(NeutralMode.Coast);
    }

    public void changeState(DriveState state){
        this.state = state;
    }
}

/*
alignment of drive base: 
1. check for vision tape
2. 



*/

