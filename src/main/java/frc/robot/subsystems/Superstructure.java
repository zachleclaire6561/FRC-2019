package frc.robot.subsystems;

import frc.robot.loops.Looper;
import frc.robot.subsystems.DriveTrain.DriveState;
import frc.robot.loops.Loop;
import frc.lib.drivers.sensors.Gyro;

import edu.wpi.first.wpilibj.Timer;


public class Superstructure extends Subsystems{
    
    private DriveTrain driveBase = DriveTrain.getInstance();
    private Elevator elevator = Elevator.getInstance();
    private Forklift forklift = Forklift.getInstance();
    private Intake intake = Intake.getInstance();
    private static Superstructure superstructureInstance = null;

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

    public void tankDrive(double x1, double x2){
       synchronized(Superstructure.this){
           if(driveBase.getState() == DriveTrain.DriveState.TELEOP){
              driveBase.tankDrive(x1,x2);
           }
        }
    }

    public void autoDrive(){
        synchronized(Superstructure.this){
            if(driveBase.getState() == DriveTrain.DriveState.AUTO){
                /*
                Data needed for autoalignment to work: 
                a) 
                - location of vision tape (vertical + angular distance) 
                    - remember gyro angles so vision isn't needed anymore??? - backup sol if vision doesn't work
                - distance from target (ultrasonic)
                How auto align needs to work: 
                1. wait for retroreflecive tape and button press 
                3. align so that front of robot is parallel to wall + align robot to be aligned with wall

                NOTE: This code will require A LOT more code than can fit in a function 
                and some things will need to be outside this class
                */
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
               //starting position / ball loading height
            break;

            case 2: 
                //disk loading height
            break;

            case 3:
                //cargo disk state
            break;

            case 4: 
                // Rocket bottom
            break;

            case 5: 
                // Rocket mid
            break;

            case 6: 
                // Rocket top
            break;
        }
        
    }

    public boolean checkIntakeSafety(){
        return (intake.getState() == Intake.IntakeState.IN);
    }
}