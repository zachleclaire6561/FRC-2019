package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

public class Intake extends Subsystems{
    int sPort;
    Spark Spark1;

    public Intake(int sPort){
        this.sPort = sPort;
        Spark1 = new Spark(sPort);
    }

    @Override
    public void zeroSensors(){
        //no sensors on intake
    }

    @Override 
    public void displaySmartDashBoard(){
        //display current state
    }

    @Override
    public void stop(){
        Spark1.set(0);
    }

    @Override 
    public void onLoop(){
        
    }

    public void set(double power){
        Spark1.set(power);
    }
  
}