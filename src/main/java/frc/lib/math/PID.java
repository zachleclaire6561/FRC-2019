package frc.lib.math;

public class PID{
    private double kP; // Proportional factor
    private double kI; // Integral factor
    private double kD; //Derivative factor
    
    private double maxOutput = 1.0; // maximum output
    private double minOutput = -1.0; // minimum output
    
    private double setPoint = 0;
    private double input = 0;
    private double lastError = Double.NaN;
    private double error = 0;
    private double totalError = 0;
    private double deadband = 0.0;
    
    /*
     * This class calculates the result of PID controller.
     *
     * The calculate() function must be called from user's thread each iteration
     */
    
    public PID() {
    }
    
    /*
     * Sets the constants of PID controller
     *
     * @param kProportional
     *
     * @param kDerivitive
     *
     * @param kIntegral
     */
    
    public PID(double kProportional, double kDerivitive, double kIntegral) {
        kP = kProportional;
        kD = kDerivitive;
        kI = kIntegral;
    }
    
    /*
     * Calculates the output of the PID controller.
     *
     * @param realValue
     */
    
    public double calculate(double realValue){
        lastError = error;
        input = realValue;
        error = setPoint - input;
        
        double pError = Math.abs(error) > deadband? error : 0.0;
        
        // countering Integral windup
        if(Math.abs(kP * pError) < maxOutput) {
            totalError += error;
        }
        else {
            totalError = 0;
        }
        
        double result = kP * pError + kI * totalError + kD * (error - lastError);
        
        return result;
    }
    
    
    /*
     * checks if current error is within tolerance
     *
     * @param tolerance
     */
    
    public boolean onTarget(double tolerance) {
        return (lastError != Double.NaN) && (error <= tolerance) ;
    }
    
    public void setSetPoint(double sP) {
        setPoint = sP;
    }
    
    public void setRange(double min, double max) {
        if(min > max) {
            //throw new
        }
    }
    
    public void resetIntegrator() {
        totalError = 0;
    }
    
    public void reset(){
        setPoint = 0;
        input = 0;
        error = 0;
        lastError = Double.NaN;
        totalError = 0;
    }
    
    public double getError() {
        return  error;
    }
    
    /*
     * Gets The current Setpoint
     *
     */
    
    public double getSetPoint() {
        return setPoint;
    }
    
    public double getIntegralConst() {
        return kI;
    }
    
    public String getState() {
        return "kP: " + kP + "\n" + "kI: " + kI + "\n" + "kD: " + kD + "\n";
    }
    
    public String getType() {
        return "PIDController";
    }

}