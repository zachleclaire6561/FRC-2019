package frc.lib.drivers.sensors;

public class UltrasonicAnalogSensor extends AnalogDevice {
    private double distance;
    private double cmPerVolt;
    private double voltage;
  
    public UltrasonicAnalogSensor(int port, double cmPerVolt) {
       super(port);
       this.cmPerVolt = cmPerVolt;
    }
    //cmPerVolt is 5mm per 4.88mv at 4096 12-bit Analog to Digital converter
    //so basically set cmPerVolt to 0.5 if 12-bit, 2 if 10-bit (1024 10bit 4096 12bit)
  
    public void setCmPerVolt(double cmPerVolt) {
        this.cmPerVolt = cmPerVolt;
    }
    public double getCmPerVolt() {
        return this.cmPerVolt;
    }
    public double getDistance() {
       
        distance = cmPerVolt * super.getAverage();
        // Distance is how many inches per volt multiplied by the voltage output by the sensor
        return distance;
    }
}