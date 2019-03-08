package frc.robot.subsystems;

import frc.robot.Constants;
import frc.lib.drivers.motorcontrollers.*;
import frc.lib.drivers.sensors.BannerSensor;
import frc.robot.loops.Looper;
import frc.robot.loops.Loop;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.ErrorCode;

public class Elevator extends Subsystems {
    private TalonSRX talon1;
    private TalonSRX talon2;
    private DoubleSolenoid brakePiston = new DoubleSolenoid(2, 3);
    private BannerSensor Bannersensor = new BannerSensor(9);

    private ElevatorState wantedState  = ElevatorState.BOTTOM;
    private ElevatorState currentState = ElevatorState.BOTTOM;

    private double timeSinceEngaged = 0;

    public enum ElevatorState{
     BOTTOM, 
     D1, 
     D2, 
     D3, 
     B1, 
     B2, 
     B3, 
     MAX
    }

    public void incrimentState()
    {
        if(wantedState != ElevatorState.MAX){
            int index = wantedState.ordinal();
            int nextIndex = index + 1;
            ElevatorState[] cars = ElevatorState.values();
            nextIndex %= cars.length;
            wantedState = cars[nextIndex];
        }
    }

    public void decrimentState(){
        if(wantedState != ElevatorState.BOTTOM){
            int index = wantedState.ordinal();
            int nextIndex = index - 1;//index + 1... Kanishk
            ElevatorState[] cars = ElevatorState.values();
            nextIndex %= cars.length;
            wantedState = cars[nextIndex];
        }
    }

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
                updatePosition(timeStamp);
                updatePower();

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
        talon2.follow(talon1);

    }

    public static Elevator getInstance(){
        if(elevatorInstance == null){
            elevatorInstance = new Elevator();
        }
        return elevatorInstance;
    }

    @Override 
    public void zeroSensors(){
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

    public synchronized void updatePosition(double timeStamp){
        if(wantedState == currentState){
            if(timeStamp - timeSinceEngaged > 0.5){
                if(Bannersensor.seesTape() && wantedState == ElevatorState.BOTTOM){
                    zeroSensors();
                }
                timeSinceEngaged = timeStamp;
                engageBrake();
            }
            else{

            }
        } 
        else{
            if(timeStamp - timeSinceEngaged > 0.5){
                timeSinceEngaged = timeStamp;
                releaseBrake();//Kanishk
                updatePower();
            }
        }
    }

    public synchronized void setHeight(ElevatorState state) {
        wantedState = state;
    }

    public void engageBrake(){
        brakePiston.set(DoubleSolenoid.Value.kForward);
    }

    public void releaseBrake(){
        brakePiston.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void updatePower(){//Kanishk
        double pow = (0.9)*getDistanceFromPoint(wantedState);//Kp == 0.9
        talon1.set(ControlMode.PercentOutput, pow);
        talon2.set(ControlMode.PercentOutput, pow);
    }

    public void setPower(double pow){
        if(Bannersensor.seesTape()){
        talon1.set(ControlMode.PercentOutput, pow);
        talon2.set(ControlMode.PercentOutput, pow);
        }
    }

    public boolean getBanner(){
        return Bannersensor.seesTape();
    }

    public double getDistanceFromPoint(ElevatorState state){
        double height = 0;
        double distance = getEncoderValue();
        if(state == ElevatorState.D1){
         //   height = // insert Disk 1 height
        }
        if(state == ElevatorState.D2){
        //    height = //insert Disk 2 height
        }
        if(state == ElevatorState.D3){
        //    height = // insert Disk 3 height
        //YO ROBOT... FUCKING WORK
        }
        if(state == ElevatorState.B1){
        //    height = // insert Ball 1 height
        }
        if(state == ElevatorState.B2){
        //    height = // insert Ball 2 height
        }
        if(state == ElevatorState.B3){
        //    height = // insert Ball 3 height
        }
        if(state == ElevatorState.MAX){
            height = 20500;
        }
        return height-distance;
    }

    public double getEncoderValue(){
        return talon1.getSelectedSensorPosition(0);
    }

    public double getElevatorSpeed(){
        return talon1.getSelectedSensorVelocity(0);
    }
}
