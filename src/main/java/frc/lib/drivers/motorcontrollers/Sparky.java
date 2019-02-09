package frc.lib.drivers.motorcontrollers;

import frc.lib.math.*;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;

/*
This is a wrapper class for spark motor controllers
*/

public class Sparky {

    private int portPWM, portPDP;
    private Spark sparky;
    private double voltageApplied;
    private double desiredVoltage;
    private double rampRate;
    private double dt;

    private double deadband;


    public Sparky(int portPWM, double dt, double rampRate){
        this.portPWM = portPWM;
        this.portPDP = portPWM;
        this.dt = dt;
        this.rampRate = rampRate;
        sparky = new Spark(portPWM);
    }

    public void set(double voltage){
       desiredVoltage = voltage;
    }

    public void periodic(double systemVoltage){
        if(Math.abs(desiredVoltage - voltageApplied ) > deadband){
        voltageApplied += rampRate * dt * (desiredVoltage > voltageApplied ? 1 : -1);
        }
        sparky.set(Voltage.voltageToAnalog(voltageApplied, systemVoltage));
    }

    public int getPWMPort(){
        return portPWM;
    }

    public int getPDPPort(){
        return portPDP;
    }

    
}