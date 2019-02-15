package frc.robot.subsystems;

import frc.robot.loops.Looper;
import frc.robot.subsystems.DriveTrain.DriveState;
import frc.robot.subsystems.Elevator.ElevatorState;
import frc.robot.loops.Loop;
import frc.lib.drivers.sensors.Gyro;
import frc.lib.math.PID;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.Timer;


public class Superstructure extends Subsystems{
    
    private DriveTrain driveBase = DriveTrain.getInstance();
    private Elevator elevator = Elevator.getInstance();
    private Forklift forklift = Forklift.getInstance();
    private Intake intake = Intake.getInstance();
    private static Superstructure superstructureInstance = null;

    private boolean intakeState = false;

    private double kP = 0.0;
    private double kI = 0.0;
    private double kD = 0.0;

    private PID pidDrive = new PID(kP, kI, kD);
    private Gyro gyro = new Gyro();

    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
            synchronized(Superstructure.this){

            }
        }

        @Override 
        public void onLoop(double timeStamp){
            synchronized(Superstructure.this){

            }
        }

        @Override 
        public void onStop(double timeStamp){
            synchronized(Superstructure.this){

            }
        }

    };

    public Superstructure(){

    }

    public static Superstructure getInstance(){
        if(superstructureInstance == null){
            superstructureInstance = new Superstructure();
        }
        return superstructureInstance;
    }

    @Override
    public void zeroSensors(){
        
    }

    @Override
    public void stop(){
        driveBase.stop();
        elevator.stop();
        forklift.stop();
        intake.stop();
    }

    @Override
    public void displaySmartDashBoard(){

    }

    @Override 
    public void registerLoop(Looper looper){
        looper.register(loop);
    }

    public void initSubsystems(){

    }

    public void initTeleopSubsystems(){

    }

    public void registerLoops(Looper looper){
        registerLoop(looper);
        driveBase.registerLoop(looper);
        elevator.registerLoop(looper);
        forklift.registerLoop(looper);
        intake.registerLoop(looper);
    }

    /*
    Robot Subsystems

    */

    public void tankDrive(double x1, double x2){
       synchronized(Superstructure.this){
           if(driveBase.getState() == DriveTrain.DriveState.TELEOP){
              driveBase.tankDrive(x1,x2);
           }
        }
    }

    public void autoAlign(){
        synchronized(Superstructure.this){
            if(driveBase.getState() == DriveTrain.DriveState.AUTO){
                if(driveBase.getAngleOffset() > min){ // insert some value here
                    double pow = pidDrive.calculate(driveBase.getAngleOffset());
                    tankDrive(pow, -pow);
                }
            }
        }
    }

    public void autoDriveStraight(double magnitude){
        synchronized(Superstructure.this){
            if(driveBase.getState() == DriveTrain.DriveState.AUTO){
                tankDrive(magnitude, magnitude);
            }
        }
    }

    public void toAutoAlign(){
        driveBase.changeState(DriveState.AUTO);
    }

    public void toTeleopControl(){
        driveBase.changeState(DriveState.TELEOP);
    }

    public void elevatorControl(int pos){
        // store heights as static constants in Constants.java 
        switch(pos){
            case 1:
              resetElevator();
            break;

            case 2: 
              setElevatorHeight();  // 
            break;

            case 3:
              setElevatorHeight();  // 
            break;

            case 4: 
              setElevatorHeight();  // 
            break;

            case 5: 
              setElevatorHeight();  // 
            break;

            case 6: 
              setElevatorHeight();  // 
            break;
        }
    }

    public boolean checkIntakeSafety(){
        return (intake.getState() == Intake.IntakeState.IN);
    }

    public void setIntakeState(boolean out){
        intake.setState(out);
    }

    public void reverseIntakeState(){
        setIntakeState(intakeState);
        intakeState = !intakeState;
    }

    public void setIntakeRollers(double power){
        intake.set(power);
    }

    public void setForkliftRollers(double power ){
        forklift.setMotorSpeed(power);
    }

    public void setForkliftAngle(double angle){
        forklift.setServoAngle(angle);
    }

    public void setElevatorHeight(double height){
        elevator.setHeight(height);
    }

    public void resetElevator(){
        elevator.resetHeight();
    }

    /*
    Vision code

    */

    public boolean getVisionTape(){
        return true;
    }

}