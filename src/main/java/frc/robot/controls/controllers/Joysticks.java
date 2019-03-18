package frc.robot.controls.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class Joysticks{

    Joystick joystick1;
    Joystick joystick2;

    public Joysticks(int port1, int port2){
        joystick1 = new Joystick(port1);
        joystick2 = new Joystick(port2);
    }

    public double getX1(){
        return joystick1.getX();
    }

    public double getX2(){
        return joystick2.getX();
    }

    public double getY1(){
        return joystick1.getY();
    }

    public double getY2(){
        return joystick2.getY();
    }

    public double getZ2(){
        return joystick2.getZ();
    }

    public boolean getTrigger2(){
        return joystick2.getTrigger();
    }

    public double getAngle2(){
        double angle = 0;
        double x = getX2();
        double y = getY2();
        angle = Math.atan(y/x);
            if(x > 0 && y > 0){
                angle = Math.atan(y/x);
            }
            if(x < 0 && y < 0){
                angle = 180 + Math.atan(y/x);
            }
            if(x > 0 && y < 0){
                angle = 270 + Math.atan(-y/x);
            }
            if(x < 0 && y > 0){
                angle = 90 + Math.atan(-y/x);
            }
        return angle;
    }

}