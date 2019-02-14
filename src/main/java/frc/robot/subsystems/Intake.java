package frc.robot.subsystems;

import frc.robot.Constants;
import frc.lib.drivers.motorcontrollers.Sparky;

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

    public Intake(){
        Spark1 = new Sparky(Constants.INTAKE_MTR_1, Constants.INTAKE_VOLTAGE_RAMP_RATE);
        Spark2 = new Sparky(Constants.INTAKE_MTR_2, Constants.INTAKE_VOLTAGE_RAMP_RATE);
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
        Spark2.set(1);
    }

    @Override 
    public void onLoop(){
        //Spark1.periodic();
        //Spark2.periodic();
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
}