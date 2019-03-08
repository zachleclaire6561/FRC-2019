package frc.robot.subsystems;

import frc.robot.loops.Looper;

public abstract class Subsystems{

    public abstract void zeroSensors();
    
    public abstract void stop();
    
    public abstract void displaySmartDashBoard();

    public abstract void registerLoop(Looper looper);
    
} 