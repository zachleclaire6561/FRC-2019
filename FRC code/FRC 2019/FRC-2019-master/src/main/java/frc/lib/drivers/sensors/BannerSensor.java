/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.drivers.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class BannerSensor {

    int port;
    boolean isTape = false;
    DigitalInput sensor;

    public BannerSensor(int port){
        this.port = port;
        sensor = new DigitalInput(port);
    }

    public int getPort(){
        return this.port;
    }

    public boolean seesTape(){
        if(sensor.get()){
            isTape = true;
        }else{
            isTape = false;
        }
        return isTape;    
    }
}
