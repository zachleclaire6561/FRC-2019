package frc.robot.subsystems;

import frc.robot.loops.*;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends Subsystems{

    private double x,y,area;
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    private NetworkTableEntry tx,ty,ta;
    
    public void visionPeriodic(){
       tx = table.getEntry("tx");
       ty = table.getEntry("ty");
       ta = table.getEntry("ta");
       x = tx.getDouble(0.0);
       y = ty.getDouble(0.0);
       area = ta.getDouble(0.0);
    }
    
    public Loop loop = new Loop(){
        @Override 
        public void onStart(double timeStamp){
          initVision();
        }
    
        @Override 
        public void onLoop(double timeStamp){
          visionPeriodic();  
        }
    
        @Override 
        public void onStop(double timeStamp){
        
        }
    };

    public void zeroSensors(){

    }
    
    public void stop(){

    }
    
    public void displaySmartDashBoard(){

    }

    public void registerLoop(Looper looper){
        looper.register(loop);
    }

    public void initVision(){
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
    }

    public void setVisionLight(boolean state){
        if(state)
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
        else
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }
}