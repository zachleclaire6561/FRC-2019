package frc.robot.subsystems;

import  edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Spark;

public class Intake extends Subsystems{

    DoubleSolenoid pistonController;
    Spark Spark1;
    IntakeState intakeState;

    public enum IntakeState{
        OUT,
        IN
    }

    public Intake(){
        Spark1 = new Spark(Constants.INTAKE_MTR_1);
        pistonController = new DoubleSolenoid(Constants.DOUBLE_SOLENOID_1, Constants.DOUBLE_SOLENOID_2);
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
    }

    @Override 
    public void onLoop(){
        
    }

    public void set(double power){
        Spark1.set(power);
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
}