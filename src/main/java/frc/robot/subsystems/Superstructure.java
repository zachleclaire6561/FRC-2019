package frc.robot.subsystems;

import frc.robot.loops.Looper;
import frc.robot.subsystems.Elevator.ElevatorState;
import frc.robot.loops.Loop;
import frc.lib.math.PID;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Timer;


public class Superstructure extends Subsystems{
    
    private DriveTrain driveBase = DriveTrain.getInstance();
    private Elevator elevator = Elevator.getInstance();
    private Forklift forklift = Forklift.getInstance();
    private Intake intake = Intake.getInstance();
    private static Superstructure superstructureInstance = null;

    private double kP = 0.0;
    private double kI = 0.0;
    private double kD = 0.0;

    private PID pidDrive = new PID(kP, kI, kD); // PID controller for auto align

    Compressor compressor = new Compressor();

    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
            synchronized(Superstructure.this){
                if(gyro.Connected()){
                    // display connection
                }
                else{
                    // display not connected
                }
                compressor.setClosedLoopControl(true);
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
        compressor.setClosedLoopControl(false);
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

   

    /*
    Robot Subsystems

    */

    public void tankDrive(double x1, double x2){  
              driveBase.tankDrive(x1,x2);
    }

    public void curveDrive(double throttle, double turn, boolean isQuickTurn){
        driveBase.curvatureDrive(throttle, turn , isQuickTurn);
    }

    public void elevatorControl(int pos, boolean isTop){
        // store heights as static constants in Constants.java 
        
    }

    public boolean checkIntakeSafety(){
        return (intake.getState() == Intake.IntakeState.IN);
    }

    public void setIntakeRollers(double power){
        intake.set(power);
    }

    public void setForkliftRollers(double power ){
        forklift.setMotorSpeed(power);
    }

    public void setForkliftServoPower(double pow){
        forklift.setServoSpeed(pow);
    }

    public void setElevatorHeight(double height){
        elevator.setHeight(height);
    }

    public void resetElevator(){
        elevator.resetHeight();
    }

    public void reverseIntake(){
        intake.reverseIntakeState();
    }

    public void reverseForklift(){
        forklift.reverseForkliftAngle();
    }

    /*
    Vision code

    */

    public boolean getVisionTape(){
        return true;
    }

}