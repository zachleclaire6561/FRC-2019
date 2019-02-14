package frc.lib.drivers.motorcontrollers;

import frc.lib.math.*;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;

/*
This is a wrapper class for spark motor controllers
*/

public class Sparky {

    private Spark sparky;
    private double voltageApplied;
    private double desiredVoltage;
    private double lastTimeStamp;
    private PID pid;

    public static class SparkData{
        public double rampRate = 1;

        public double deadband = 1;

        public double kP;
        public double kI;
        public double kD;
    }

    private SparkData SparkyData;

    public Sparky(int portPWM){
        sparky = new Spark(portPWM);
    }

    public Sparky(int portPWM, double voltage){
        sparky = new Spark(portPWM);
        desiredVoltage = voltage;
    }

    public void set(double voltage){
       desiredVoltage = voltage;
    }

    public void onLoop(){
        double time = Timer.getFPGATimestamp();
        double dt = time - lastTimeStamp;
        lastTimeStamp = time;
        if(Math.abs(desiredVoltage - voltageApplied ) > SparkyData.deadband){
        voltageApplied += SparkyData.rampRate * dt * (desiredVoltage > voltageApplied ? 1 : -1);
        }
        sparky.set(Voltage.voltageToAnalog(voltageApplied, desiredVoltage));
    }

    /*public int getPWMPort(){
        return portPWM;
    }

    public int getPDPPort(){
        return portPDP;
    }*/

    
}