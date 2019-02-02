//This subsystem will have the functions of the forklift on the robot
//The forklift is begin used as the intake for the disc and the outtake for the ball

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Forklift extends Subsystems{
    int sPort1;
    int sPort2;
    DoubleSolenoid solenoid1;

    public enum forkLiftState{

    }

    public Forklift(int sPort1, int sPort2){
        this.sPort1 = sPort1;
        this.sPort2 = sPort2;
        solenoid1 = new DoubleSolenoid(sPort1, sPort2);
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