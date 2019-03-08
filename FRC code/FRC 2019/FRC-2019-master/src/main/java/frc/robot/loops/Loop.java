package frc.robot.loops;

public interface Loop{
    
    public void onStart(double timestamp);

    public void onLoop(double timestamp);

    public void onStop(double timestamp);
    
}