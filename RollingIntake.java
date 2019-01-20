//This class has the functions for the rolling intake

package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.Spark;
public class RollingIntake{
    int sPort;
    Spark wheelController;

    public RollingIntake(int sPort){
        this.sPort = sPort;
        wheelController = new Spark(sPort);
    }
    
    public void forward(){
        wheelController.set(1);
    }

    public void reverse(){
        wheelController.set(-1);
    }

    public void stop(){
        wheelController.set(0);
    }
}