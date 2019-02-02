package frc.robot.subsystems;

import frc.robot.Constants;
import frc.lib.drivers.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Elevator extends Subsystems {
    int tPort1, tPort2;
    double encodedHeight;
    double encodedVelocity;
    double encodedPosition;
    TalonSRX talon1;
    TalonSRX talon2;

    public enum ElevatorState{
        SCORING,
        INTAKE,
        ZEROING
    }

    public Elevator() {
        talon1 = new TalonSRXFactory.createDefaultTalon(Constants.ELEVATOR_MTR_1);
        talon2 = new TalonSRX(Constants.ELEVATOR_MTR_2);
        talon2.follow(talon1);
    }

    @Override 
    public void zeroSensors(){
        
    }

    @Override 
    public void stop(){

    }

    @Override 
    public void displaySmartDashBoard(){

    }

    @Override 
    public void onLoop(){

    }

    public void setHeight(double height) {
        talon1.set(ControlMode.PercentOutput, height );
    }

    public double getRawVelocity() {
        encodedVelocity = talon1.getSelectedSensorVelocity(0);
        return encodedVelocity;
    }

    public double getRawPosition() {
        encodedPosition = talon1.getSelectedSensorPosition(0);
        return encodedPosition;
    }

    public double getHeight(){
        return getRawPosition() * Constants.ELEVATOR_TICKS_TO_DISTANCE;
    }

    public double getVelocity(){
       return 1; 
    }
}