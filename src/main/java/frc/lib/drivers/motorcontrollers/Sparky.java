package frc.lib.drivers.motorcontrollers;

import frc.lib.math.*;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;

/*
This is a wrapper class for spark motor controllers
*/

public class Sparky {

    private Spark sparky;
    private double voltageApplied;
    private double desiredVoltage;
<<<<<<< HEAD
    private double lastTimeStamp;
    private PID pid;
=======
    private double rampRate;
>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4

    public static class SparkData{
        public double rampRate = 1;

<<<<<<< HEAD
        public double deadband = 1;

        public double kP;
        public double kI;
        public double kD;
    }

    private SparkData SparkyData;

    public Sparky(int portPWM){
=======
    public Sparky(int portPWM, double rampRate){
        this.portPWM = portPWM;
        this.portPDP = portPWM;
       
        this.rampRate = rampRate;
>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4
        sparky = new Spark(portPWM);
    }

    public Sparky(int portPWM, double voltage){
        sparky = new Spark(portPWM);
        desiredVoltage = voltage;
    }

    public void set(double voltage){
       desiredVoltage = voltage;
    }

<<<<<<< HEAD
    public void onLoop(){
        double time = Timer.getFPGATimestamp();
        double dt = time - lastTimeStamp;
        lastTimeStamp = time;
        if(Math.abs(desiredVoltage - voltageApplied ) > SparkyData.deadband){
        voltageApplied += SparkyData.rampRate * dt * (desiredVoltage > voltageApplied ? 1 : -1);
=======
    public void periodic(double systemVoltage){
        if(Math.abs(desiredVoltage - voltageApplied ) > deadband){
        voltageApplied += rampRate * (desiredVoltage > voltageApplied ? 1 : -1);
>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4
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