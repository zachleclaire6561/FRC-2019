package frc.robot.subsystems;

import frc.robot.loops.Looper;
import frc.robot.loops.Loop;

public class Climber extends Subsystems{

    private static Climber climb = null;

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

}

public void reverse(){

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