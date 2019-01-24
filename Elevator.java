package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.Spark;

public class Elevator extends Subsystems {
    int sPort1;
    Spark elevatorController;

    public Elevator(int sPort1) {
        this.sPort1 = sPort1;
        elevatorController = new Spark(sPort1);
    }
    public void raise() {
        elevatorController.set(1);
    }
    public void lower() {
        elevatorController.set(-1);
    }
    public void stop() {
        elevatorController.set(0);
    }
}