package frc.lib.drivers.sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogDevice{

    private int port;
    private AnalogInput sensor;

    AnalogDevice(int port){
        this.port = port;
        sensor = new AnalogInput(port); 
    }

    public int getPort() {
        return port;
    }

    public double getRawVoltage(){
        return sensor.getVoltage();
    }

    public double getAverage(){
        return sensor.getAverageVoltage();
    }

    public boolean testSensor(){
        boolean testSuccess = true;
        try {
            getAverage();
        } catch (Exception e) {
            System.out.println("AnalogDevice on Port " + port + " has a connection issue");
            testSuccess = false;
        }
        return testSuccess;
    }

}