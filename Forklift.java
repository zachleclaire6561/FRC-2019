//This subsystem will have the functions of the forklift on the robot
//The forklift is begin used as the intake for the disc and the outtake for the ball

package frc.robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Forklift{
    int sPort1;
    int sPort2;
    DoubleSolenoid pistonController;

    public Forklift(int sPort1, int sPort2){
        this.sPort1 = sPort1;
        this.sPort2 = sPort2;
        pistonController = new DoubleSolenoid(sPort1, sPort2);
    }

    public void forward(){
        pistonController.set(DoubleSolenoid.Value.kForward);
    }
    
    public void reverse(){
        pistonController.set(DoubleSolenoid.Value.kReverse);
    }

    public void off(){
        pistonController.set(DoubleSolenoid.Value.kOff)
;    }
}