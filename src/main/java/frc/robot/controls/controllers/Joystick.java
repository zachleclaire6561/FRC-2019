package frc.robot.controls;

public class Joystick{

    Joystick joystick;

    public Joystick(int iD){
        joystick = new Joystick(iD);
    }

    public double getX(){
        return joystick.getX();
    }

    public double getY(){
        return joystick.getY();
    }
}