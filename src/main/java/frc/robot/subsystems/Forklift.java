//This subsystem will have the functions of the forklift on the robot
//The forklift is begin used as the intake for the disc and the outtake for the ball

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;

import frc.robot.loops.Looper;
import frc.robot.loops.Loop;
import frc.robot.Constants;

public class Forklift extends Subsystems{
 
    // limit switch code
    private Spark sparky = new Spark(Constants.FORKLIFT_MTR);
    //private Servo servo = new Servo(Constants.FORKLIFT_SERVO);
    private static Forklift forkliftInstance = null;
    private forkLiftState forkliftState = forkLiftState.UP;

    public enum forkLiftState{
        DOWN,
        UP
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
                /*
                double angle = getServoAngle();
                if(forkliftState == forkLiftState.DOWN){
                    setMotorSpeed((180 - angle)/180);
                }
                else{
                    if(angle < 109){
                    ///    setMotorSpeed(Constants.FORKLIFT_SERVO_POWER);
                    }
                    else{
                        setMotorSpeed((angle-110)/70);
                    }
                }
                */
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

    /*public void setServoAngle(double angle){
        servo.setAngle(angle);
    }

    public void setServoSpeed(double power){
        servo.set(power);
    }*/

    public void setMotorSpeed(double power){
        sparky.set(power);
    }

    /*public double getServoAngle(){
        return servo.getAngle();
    }

    public void reverseForkliftAngle(){
        if(forkliftState == forkLiftState.UP){
            setServoAngle(180);
            forkliftState = forkLiftState.DOWN;
        }
        else{
            setServoAngle(107);
            forkliftState = forkLiftState.UP;
        }
    }*/
}