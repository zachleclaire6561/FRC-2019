/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.Constants;
import frc.robot.loops.Looper;

/**
 * Add your docs here.
 */
public class Climber extends Subsystems{ 
    
    private Spark sparky1;
    private Spark sparky2;

    public static Climber climberInstance = null;

    boolean isMade = false;

    @Override
    public void zeroSensors() {
        
    }

    @Override
    public void displaySmartDashBoard() {
        
    }

    @Override
    public void registerLoop(Looper looper) {
        
    }

    public Climber(){
        sparky1 = new Spark(Constants.CLIMB_MTR_1);
        sparky2 = new Spark(Constants.CLIMB_MTR_2);
    }

    public static Climber getInstance(){
        if(climberInstance == null){
            climberInstance = new Climber();
        }
        return climberInstance;
    }
    
    public void run(){
        sparky1.set(1);
        sparky2.set(1);
    }

    public void reverse(){
        sparky1.set(-0.5);
        sparky2.set(-0.5);
    }

    public void stop(){
        sparky1.set(0);
        sparky2.set(0);
    }
}
