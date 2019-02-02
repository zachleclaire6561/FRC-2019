//This class has the functions for the rolling intake

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Spark;

public class RollingIntake extends Subsystem{
    int sPort;
    Spark Spark1;

    public RollingIntake(int sPort){
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
        wheelController.set(0);
    }
    
    /*
    
    */

    public void Roll(double power){
        Spark.set(power);
    }
  
}