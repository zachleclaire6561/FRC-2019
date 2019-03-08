package frc.lib.math;

public class Voltage{
   
    public static double checkVoltage(double goalVoltage, double systemVoltage, double minVoltage){
        if(goalVoltage > systemVoltage){
            goalVoltage = systemVoltage;
        }
        if(goalVoltage < minVoltage){
            goalVoltage = minVoltage;
        }
        return goalVoltage;
    }

    public static double analogToVoltage(double analogValue, double systemVoltage, double minVoltage){  
        return minVoltage + analogValue * (systemVoltage - minVoltage);
    }

    public static double voltageToAnalog(double voltage, double systemVoltage){
        return voltage/systemVoltage;
    }

}