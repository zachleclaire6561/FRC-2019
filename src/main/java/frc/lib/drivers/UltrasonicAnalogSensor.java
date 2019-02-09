package frc.lib.drivers;
import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicAnalogSensor {
    int port;
    double distance;
    double cmPerVolt;
    double voltage;
    AnalogInput sensor;
  
    public UltrasonicAnalogSensor(int port, double cmPerVolt) {
        this.port = port;
        this.cmPerVolt = cmPerVolt;
        sensor = new AnalogInput(port);
    }
    //cmPerVolt is 5mm per 4.88mv at 4096 12-bit Analog to Digital converter
    //so basically set cmPerVolt to 0.5 if 12-bit, 2 if 10-bit (1024 10bit 4096 12bit)
    public int getPort() {
        return this.port;
    }
    public void setCmPerVolt(double cmPerVolt) {
        this.cmPerVolt = cmPerVolt;
    }
    public double getCmPerVolt() {
        return this.cmPerVolt;
    }
    public double getDistance() {
        try {
        distance = cmPerVolt * sensor.getVoltage();
        } catch (Exception e) {
            System.out.println("Error with distance calculation! Make sure to check cmPerVolt is set and check if voltage is being input.");
        }
        // Distance is how many inches per volt multiplied by the voltage output by the sensor
        return distance;
    }
}