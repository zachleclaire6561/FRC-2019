

public class Math{
   
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

}