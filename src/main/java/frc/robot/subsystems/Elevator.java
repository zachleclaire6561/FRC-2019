package frc.robot.subsystems;

import frc.robot.Constants;
import frc.lib.math.PID;
import frc.lib.drivers.motorcontrollers.*;
import frc.lib.drivers.sensors.LimitSwitch;
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

    private double goalHeight;
    
    private TalonSRX talon1;
    private TalonSRX talon2;
    private LimitSwitch lim1 = new LimitSwitch(Constants.LIMIT_SWITCH_1);;

    public double kP = 1.0; //Need to re-write... PID variables not set.
    public double kI = 0;
    public double kD = 0;

    public double tolerance = 1;  //sensitivity constant for controlls

    private PID pidHeightController = new PID(kP, kI, kD);
    private ElevatorState elvState = ElevatorState.RESET; 


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
                updatePosition();
                updateBrake();
                height = getHeight();
            }
        }

        @Override 
        public void onStop(double timeStamp){
            synchronized(Elevator.this){
                stop();
            }
        }

    }; //ALL CODE FROM LINE 38 TO HERE IS IRRELEVANT

    public enum ElevatorState{
      BRAKE, //stopped
      MOVING, //moving
      RESET, //moving back to bottom
      BOTTOM //grounded at bottom--default
    }

    private static Elevator elevatorInstance = null;

    private void configureMaster(TalonSRX talon) { //talon factory
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

    public void elevatorDrive(double speed1){
        talon1.set(ControlMode.PercentOutput, speed1);
        talon2.set(ControlMode.PercentOutput, speed1);
    }

    @Override 
    public void zeroSensors(){
        height = 0;
        talon1.setSelectedSensorPosition(0, 0, 0);
        pidHeightController.resetIntegrator();
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
        if( elvState != ElevatorState.BRAKE)
            talon1.set(ControlMode.PercentOutput, pidHeightController.calculate(height));
    }

    public void updateBrake(){
        if(pidHeightController.onTarget(tolerance))
            elvState = ElevatorState.BRAKE;
    }

    public synchronized void resetHeight(){
        if(! lim1.get() && elvState != ElevatorState.BOTTOM){
            //setHeight(-0.5);
            elvState = ElevatorState.RESET;
        }
        else{
            elvState = ElevatorState.BOTTOM;
            height = 0;
            zeroSensors();
        }
    }

    //Accessors

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
       return getRawVelocity() * Constants.ELEVATOR_TICKS_TO_DISTANCE;
    }

    public boolean getZeroSwitch(){
        return lim1.get();
    }
}