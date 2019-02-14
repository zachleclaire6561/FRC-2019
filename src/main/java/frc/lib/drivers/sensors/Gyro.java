package frc.lib.drivers.sensors;

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

<<<<<<< HEAD
=======
    public boolean isConnected(){
        return gyro.isConnected();
    }

    public boolean isCalibrating(){
        return gyro.isCalibrating();
    }



>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4
}