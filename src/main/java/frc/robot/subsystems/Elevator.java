package frc.robot.subsystems;

import frc.robot.Constants;
import frc.lib.drivers.*;
import frc.lib.math.PID;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.ErrorCode;

public class Elevator extends Subsystems {
    private double height;

    private double goalHeight;
    
    private TalonSRX talon1;
    private TalonSRX talon2;

    private PID pidHeightController = new PID(Constants.kP, Constants.kI, Constants.kD);
    private ElevatorState elvState = ElevatorState.INTAKE;

    public enum ElevatorState{
        SCORING,  
        INTAKE,
        ZEROING, 
        BRAKE
    }

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

    @Override 
    public void zeroSensors(){
        talon1.setSelectedSensorPosition(0, 0, 0);
    }

    @Override 
    public void stop(){
        
    }

    @Override 
    public void displaySmartDashBoard(){

    }

    @Override 
    public void onLoop(){
        if( ! (elvState == ElevatorState.BRAKE))
        talon1.set(ControlMode.PercentOutput, pidHeightController.calculate(height));
    }

    public void setHeight(double height) {
        goalHeight = height;
    }

    public void ResetHeight(){
        height = 0;
        talon1.setSelectedSensorPosition(0, 0, 0);
        elvState = ElevatorState.INTAKE;
    }

    public double getRawVelocity() {
        return talon1.getSelectedSensorVelocity(0);
    }

    public double getRawPosition() {
        return  talon1.getSelectedSensorPosition(0);
    }

    public double getHeight(){
        return getRawPosition() * Constants.ELEVATOR_TICKS_TO_DISTANCE;
    }

    public double getVelocity(){
       return 1;
    }
}