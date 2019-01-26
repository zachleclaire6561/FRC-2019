package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.phoenix.motorcontrol.can.*;

public class Elevator extends Subsystems {
    int tPort1, tPort2;
    double encodedHeight;
    double encodedVelocity;
    TalonSRX talon1;
    TalonSRX talon2;

    public Elevator(int tPort1, int tPort2) {
        this.tPort1 = tPort1;
        this.tPort2 = tPort2;
        talon1 = new TalonSRX(tPort1);
        talon2 = new TalonSRX(tPort2);
        talon2.follow(talon1);
    }
    public void raise() {
        talon1.set(ControlMode.PercentOutput, 1);
    }
    public void lower() {
        talon1.set(ControlMode.PercentOutput, -1);
    }
    public void stop() {
        talon1.set(ControlMode.PercentOutput, 0);
    }
    public getVelocity() {
        encodedVelocity = talon1.getSelectedSensorVelocity(0);
        return encodedVelocity;
    }
    public getPosition() {
        encodedPosition = talon1.getSelectedSensorPosition(0);
        return encodedPosition;
    }
}