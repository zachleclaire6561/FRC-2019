//This subsystem will have the functions of the forklift on the robot
//The forklift is begin used as the intake for the disc and the outtake for the ball

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Forklift extends Subsystems{
 

    private static Forklift forkliftInstance = null;

    public enum forkLiftState{

    }

    public Forklift(){
       
       
    }

    public static Forklift getInstance(){
        if( forkliftInstance == null){
            forkliftInstance = new Forklift();
        }
        return forkliftInstance;
    }


    @Override 
    public void zeroSensors(){

    }

    @Override 
    public void stop(){

    }

    @Override 
    public void onLoop(){

    }

    @Override
    public void displaySmartDashBoard(){

    }
}