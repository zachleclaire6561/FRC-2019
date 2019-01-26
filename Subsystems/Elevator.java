package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.Spark;

public class Elevator extends Subsystems {

    int port1;
    int port2; 
    private WPI_TalonSRX talon1;
    private WPI_TalonSRX talon2;

    public Elevator(int port1, int port2) {
        this.port1 = port1;
        this.port2 = port2;
        talon1 = new WPI_TalonSRX();
    }

    public void raise() {
        elevatorController.set(1);
    }

    public void lower() {
        elevatorController.set(-1);
    }

    @Override
    public void zeroSensors(){

    }

    @Override 
    public void stop(){

    }

    @Override
    public void displaySmartDashBoard(){

    }

    @Override 
    public void looper(){

    }
}