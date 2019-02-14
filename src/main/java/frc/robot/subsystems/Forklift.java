//This subsystem will have the functions of the forklift on the robot
//The forklift is begin used as the intake for the disc and the outtake for the ball

package frc.robot.subsystems;
<<<<<<< HEAD

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Servo;

import frc.robot.Constants;

public class Forklift extends Subsystems{
    
    Spark spark1 = new Spark(Constants.FORKLIFT_MTR);
    Servo servo1 = new Servo(Constants.FORKLIFT_SERVO);
    public enum forkLiftState{
=======
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.loops.Looper;
import frc.robot.loops.Loop;

public class Forklift extends Subsystems{
 

    private static Forklift forkliftInstance = null;
>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4

    public enum forkLiftState{
        
    }

<<<<<<< HEAD
    public Forklift(){

    }
=======
    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
            synchronized(Forklift.this){
>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4

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

<<<<<<< HEAD
    public void pistonOut(){
     
    }
    
    public void reverse(){
      
    }

    public void off(){
       
=======
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

>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4
    }

    @Override
    public void displaySmartDashBoard(){

    }

    @Override 
    public void registerLoop(Looper looper){
        looper.register(loop);
    }
}