package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.loops.Looper;
import frc.robot.loops.Loop;
import frc.lib.math.PID;

import  edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class Intake extends Subsystems{

    private DoubleSolenoid pistonController;
    private Spark Spark1;
    private Spark Spark2;

    double kP = 1.0;
    double kI = 0.0;
    double kD = 0.0;
    
    public IntakeState intakeState;

    public enum IntakeState{
        OUT,
        IN
    }

    private static Intake intakeInstance= null;

    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
            synchronized(Intake.this){
                
            }
        }

        @Override 
        public void onLoop(double timeStamp){
            synchronized(Intake.this){

            }
        }

        @Override 
        public void onStop(double timeStamp){
            synchronized(Intake.this){

            }
        }
    };

    public Intake(){
        Spark1 = new Spark(Constants.INTAKE_MTR_1);
        Spark2 = new Spark(Constants.INTAKE_MTR_2);
        pistonController = new DoubleSolenoid(Constants.DOUBLE_SOLENOID_1, Constants.DOUBLE_SOLENOID_2);
    }

    public static Intake getInstance(){
        if( intakeInstance == null){
            intakeInstance = new Intake();
        }
        return intakeInstance;
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
        Spark1.set(0);
        Spark2.set(0);
    }

    @Override 
    public void registerLoop(Looper looper){
        looper.register(loop);
    }

    public void set(double power){
        Spark1.set(power);
        Spark2.set(-power);
    }

    public void setState(boolean state){
        if(state){
            pistonController.set(DoubleSolenoid.Value.kForward);
            intakeState = IntakeState.OUT;
        }else{
            pistonController.set(DoubleSolenoid.Value.kReverse);
            intakeState = IntakeState.IN;
        }
    } 

    public void reverseIntakeState(){
        if(intakeState == IntakeState.IN){
            setState(false);
        }
        else if(intakeState == IntakeState.OUT){
            setState(true);
        }
    }

    public IntakeState getState(){
        return intakeState;
    }
}