//This subsystem will have the functions of the forklift on the robot
//The forklift is begin used as the intake for the disc and the outtake for the ball

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.loops.Looper;
import frc.robot.loops.Loop;

public class Forklift extends Subsystems{
 

    private static Forklift forkliftInstance = null;

    public enum forkLiftState{
        
    }

    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
            synchronized(Forklift.this){

            }
        }

        @Override 
        public void onLoop(double timeStamp){
            synchronized(Forklift.this){

            }
        }

        @Override 
        public void onStop(double timeStamp){
            synchronized(Forklift.this){

            }
        }

    };

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
    public void displaySmartDashBoard(){

    }

    @Override 
    public void registerLoop(Looper looper){
        looper.register(loop);
    }
}