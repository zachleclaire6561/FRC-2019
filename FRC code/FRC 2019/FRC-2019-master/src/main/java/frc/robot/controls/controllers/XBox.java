package frc.robot.controls.controllers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class XBox{

    private XboxController xbox;

    private boolean tempB = true;
    private boolean tempA = true;
    private boolean tempX = true;
    private boolean tempY = true;
    private boolean tempStart = true;
    private boolean tempBack = true;

    public XBox(int iD){
        xbox = new XboxController(iD);
    }

    public boolean getButtonB(){
        return xbox.getBButton();
    }

    public boolean getButtonA(){
        return xbox.getAButton();
    }

    public boolean getButtonX(){
        return xbox.getXButton();
    }

    public boolean getButtonY(){
        return xbox.getYButton();
    }

    public boolean getRightBumper(){
        return xbox.getBumper(Hand.kRight);
    }

    public boolean getLeftBumper(){
        return xbox.getBumper(Hand.kLeft);
    }

    public boolean getStart(){
        return xbox.getStartButton();
    }

    public boolean getBack(){
        return xbox.getBackButton();
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

    public boolean getButtonANewPress(){
        boolean newPress = false;
        if(getButtonA()){
            if(tempA){
                newPress = true;
                tempA = false;
            }
        }
        else{
            tempA = true;
        }
        return newPress;
    }

    public boolean getButtonXNewPress(){
        boolean newPress = false;
        if(getButtonX()){
            if(tempX){
                newPress = true;
                tempX = false;
            }
        }
        else{
            tempX = true;
        }
        return newPress;
    }

    public boolean getButtonYNewPress(){
        boolean newPress = false;
        if(getButtonY()){
            if(tempY){
                newPress = true;
                tempY = false;
            }
        }
        else{
            tempY = true;
        }
        return newPress;
    }

    public boolean getStartNewPress(){
        boolean newPress = false;
        if(getStart()){
            if(tempStart){
                newPress = true;
                tempStart = false;
            }
        }
        else{
            tempStart = true;
        }
        return newPress;
    }
    public boolean getBackNewPress(){
        boolean newPress = false;
        if(getBack()){
            if(tempBack){
                newPress = true;
                tempStart = false;
            }
        }
        else{
            tempBack = true;
        }
        return newPress;
    }

    public double getLeftTrigger(){
        return xbox.getTriggerAxis(Hand.kLeft);
    }

    public double getRightTrigger(){
        return xbox.getTriggerAxis(Hand.kRight);
    }

    public double getLeftY(){
        return xbox.getY(Hand.kLeft);
    }

    public double getRightY(){
        return xbox.getY(Hand.kLeft);
    }
   
}

