package frc.robot.subsystems;

import frc.robot.loops.Looper;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.loops.Loop;


public class Climber extends Subsystems{

    private static Climber climb = null;
    Spark spark3 = new Spark(3);
    Spark spark4 = new Spark(4);

public Loop loop = new Loop(){
    @Override 
    public void onStart(double timeStamp){
      
    }

    @Override 
    public void onLoop(double timeStamp){
        
    }

    @Override 
    public void onStop(double timeStamp){
    
    }
};

public void run(){
    spark3.set(1);
    spark4.set(1);
}

public void reverse(){
    spark3.set(-0.5);
    spark4.set(-0.5);
}

public void setToZero(){
    spark3.set(0);
    spark4.set(0);
}

public static Climber getInstance(){
    if(climb == null){
     climb = new Climber();
    }
    return climb;
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

}