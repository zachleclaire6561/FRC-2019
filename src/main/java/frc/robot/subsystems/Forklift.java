//This subsystem will have the functions of the forklift on the robot
//The forklift is begin used as the intake for the disc and the outtake for the ball

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;

public class Forklift extends Subsystems{
    
    Spark spark1 = new Spark(Constants.FORKLIFT_MTR);
    public enum forkLiftState{

    }

    public Forklift(){

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

    public void pistonOut(){
        solenoid1.set(DoubleSolenoid.Value.kForward);
    }
    
    public void reverse(){
       solenoid1.set(DoubleSolenoid.Value.kReverse);
    }

    public void off(){
        solenoid1.set(DoubleSolenoid.Value.kOff);  
    }

    @Override
    public void displaySmartDashBoard(){

    }
}