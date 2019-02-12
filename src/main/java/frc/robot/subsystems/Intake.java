package frc.robot.subsystems;



import frc.robot.Constants;
import frc.lib.drivers.motorcontrollers.Sparky;
import frc.robot.loops.Looper;
import frc.robot.loops.Loop;

import  edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class Intake extends Subsystems{

    private DoubleSolenoid pistonController;
    private Sparky Spark1;
    private Sparky Spark2;
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
        Spark1 = new Sparky(Constants.INTAKE_MTR_1, Constants.INTAKE_VOLTAGE_RAMP_RATE);
        Spark2 = new Sparky(Constants.INTAKE_MTR_2, Constants.INTAKE_VOLTAGE_RAMP_RATE);
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
        Spark2.set(1);
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

    public IntakeState getState(){
        return intakeState;
    }
}