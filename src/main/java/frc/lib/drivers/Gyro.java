package frc.lib.drivers;

import frc.robot.Constants;

import com.kauailabs.navx.frc.AHRS;

public class Gyro{

    AHRS gyro;

    public Gyro(){
        gyro = new AHRS(Constants.DRIVE_TRAIN_GYRO); 
    }

    public double getAngle(){
       return gyro.getAngle();
    }

}