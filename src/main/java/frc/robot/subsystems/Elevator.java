package frc.robot.subsystems;

import frc.robot.Constants;
import frc.lib.math.PID;
import frc.lib.drivers.motorcontrollers.*;
import frc.lib.drivers.sensors.BannerSensor;
import frc.robot.loops.Looper;
import frc.robot.loops.Loop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.ErrorCode;

public class Elevator extends Subsystems {
    private double height;
    private double lastHeight = 0;

    private double goalHeight;

    private TalonSRX talon1;
    private TalonSRX talon2;
    private BannerSensor sensor = new BannerSensor(Constants.LIMIT_SWITCH_1);


    // limit switch

    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
            synchronized(Elevator.this){
                zeroSensors();
            }
        }

        @Override 
        public void onLoop(double timeStamp){
            synchronized(Elevator.this){

            }
        }

        @Override 
        public void onStop(double timeStamp){
            synchronized(Elevator.this){
                stop();
            }
        }

    };

    private static Elevator elevatorInstance = null;

    private void configureMaster(TalonSRX talon) {
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5, 100);
        final ErrorCode sensorPresent = talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100); 
        if (sensorPresent != ErrorCode.OK) {
           // display this somewhere
        }
        talon.setInverted(false);
        talon.setSensorPhase(true);
        talon.enableVoltageCompensation(true);
        talon.configVoltageCompSaturation(12.0, Constants.kLongCANTimeoutMs);
        talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_50Ms, Constants.kLongCANTimeoutMs);
        talon.configVelocityMeasurementWindow(1, Constants.kLongCANTimeoutMs);
        talon.configClosedloopRamp(Constants.DRIVE_VOLTAGE_RAMP_RATE, Constants.kLongCANTimeoutMs);
        talon.configNeutralDeadband(0.04, 0);
    }

    public Elevator() {
        talon1 = TalonSRXFactory.createDefaultTalon(Constants.ELEVATOR_MTR_1);
        configureMaster(talon1);

        talon2 = TalonSRXFactory.createPermanentSlaveTalon(Constants.ELEVATOR_MTR_2, Constants.ELEVATOR_MTR_1);
    }

    public static Elevator getInstance(){
        if(elevatorInstance == null){
            elevatorInstance = new Elevator();
        }
        return elevatorInstance;
    }

    @Override 
    public void zeroSensors(){
        height = 0;
        talon1.setSelectedSensorPosition(0, 0, 0);
    }

    @Override 
    public void stop(){
        
    }
    
    @Override 
    public void registerLoop(Looper looper){
        looper.register(loop);
    }

    @Override 
    public void displaySmartDashBoard(){

    }

    public void updatePosition(){
        if((lastHeight > height) && sensor.seesTape()){
           talon1.set(ControlMode.PercentOutput, 0);
        }
        else{
            talon1.set(ControlMode.Position, height);
        }
        zeroSensors();
    }

    public synchronized void setHeight(double kHeight) {
        lastHeight = goalHeight;
        goalHeight = kHeight;
    }

    public void setPower(double pow){
        talon1.set(ControlMode.PercentOutput, pow);
    }

    public double getRawVelocity() {
        return talon1.getSelectedSensorVelocity(0);
    }

    public double getRawPosition() {
        return  talon1.getSelectedSensorPosition(0);
    }
}