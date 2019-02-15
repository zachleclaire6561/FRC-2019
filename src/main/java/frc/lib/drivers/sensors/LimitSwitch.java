package frc.lib.drivers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;;

public class LimitSwitch{

    DigitalInput input;

    public LimitSwitch(int port){
        input = new DigitalInput(port);
    }

    public boolean get(){
        return input.get();
    }
}