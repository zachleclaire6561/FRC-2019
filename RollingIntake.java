//This class has the functions for the rolling intake

package frc.robot;
import edu.wpi.first.wpilibj.Spark;
public class RollingIntake{
    int sPort;
    Spark wheelController;

    public RollingIntake(int sPort){
        this.sPort = sPort;
        wheelController = new Spark(sPort);
    }
    
    public void move(){
        wheelController.set(1);
    }
}