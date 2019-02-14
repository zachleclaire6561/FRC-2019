package frc.robot.controls.controllers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class XBox{

    private XboxController xbox;
    private boolean tempB = true;

    public XBox(int iD){
        xbox = new XboxController(iD);
    }

    public boolean getButtonB(){
        return xbox.getBButton();
    }

    public boolean getButtonBNewPress(){
        boolean newPress = false;
        if(getButtonB()){
            if(tempB){
                newPress = true;
                tempB = false;
            }
        }
        else{
            tempB = true;
        }
        return newPress;
    }

    public double getLeftTrigger(){
        return xbox.getTriggerAxis(Hand.kLeft);
    }

    public double getRightTrigger(){
        return xbox.getTriggerAxis(Hand.kRight);
    }

    // add one for each button (A, X, Y)
}