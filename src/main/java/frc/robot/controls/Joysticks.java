package frc.robot.controls;

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
}