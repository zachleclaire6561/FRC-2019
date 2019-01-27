package frc.robot.Subsystems;
import edu.wpi.first.wpilibj.Spark;
import com.ctre.phoenix.motorcontrol.can.*;

enum Height {
    ROCKET_3, ROCKET_2, ROCKET_1, CARGO_BAY, LOADING_STATION, FLOOR;
}
public class Elevator extends Subsystems {
    int tPort1, tPort2;
    boolean debug;
    boolean ball;
    private final double margin;
    TalonSRX talon1;
    TalonSRX talon2;
    Height height;

    public Elevator(int tPort1, int tPort2, boolean ball) {
        this.tPort1 = tPort1;
        this.tPort2 = tPort2;
        this.ball = ball;
        talon1 = new TalonSRX(tPort1);
        talon2 = new TalonSRX(tPort2);
        talon2.follow(talon1);
    }
    public void raise() { //Raises elevator
        talon1.set(ControlMode.PercentOutput, 1);
    }
    public void lower() { //Lowers elevator
        talon1.set(ControlMode.PercentOutput, -1);
    }
    public void stop() { //Stops elevator
        talon1.set(ControlMode.PercentOutput, 0);
    }
    public void debugOn() { //Turns on debug mode
        debug = true;
    }
    public void debugOff() { //Turns off debug mode
        debug = false;
    }
    public void setBall() { //Sets elevator height values to ball
        ball = true;
    }
    public void setDisk() { //Sets elevator height values to disk
        ball = false;
    }
    public void switchGamepiece() { //Switches disk/ball depending on current value
        ball = !ball;
    }
    public getVelocity() { //Get velocity of the elevator (encoder)
        return talon1.getSelectedSensorVelocity(0);
        if (debug) {
            System.out.println("Talon 1 Velocity: " + talon1.getSelectedSensorVelocity(0));
            System.out.println("Talon 2 Velocity: " + talon2.getSelectedSensorVelocity(0));
            System.out.println("Average Value: " + (0.5 * (talon1.getSelectedSensorVelocity(0) + talon2.getSelectedSensorVelocity(0))));
        }
    }
    public getPosition() { //Get position of the elevator (encoder)
        return talon1.getSelectedSensorPosition(0);
        if (debug) {
            System.out.println("Talon 1 Position: " + talon1.getSelectedSensorPosition(0));
            System.out.println("Talon 2 Position: " + talon2.getSelectedSensorPosition(0));
            System.out.println("Average Value: " + (0.5 * (talon1.getSelectedSensorPosition(0) + talon2.getSelectedSensorPosition(0))));
        }
    }
    private goToSub(double target) {
        if (Math.abs(target - talon1.getSelectedSensorPosition(0)) <= margin) {
            break;
        }
        while (target + margin < talon1.getSelectedSensorPosition(0)) {
            this.Elevator.lower();
        }
        while (target - margin > talon1.getSelectedSensorPosition(0)) {
            this.Elevator.raise();
        }
    }
    public void goTo(Height height) {
        if (ball) {
            switch (height) {
                case ROCKET_3:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case ROCKET_2:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case ROCKET_1:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case CARGO_BAY:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case LOADING_STATION:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case FLOOR: 
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
            }
        }
        else if (!ball) {
            switch (height) {
                case ROCKET_3:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case ROCKET_2:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case ROCKET_1:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case CARGO_BAY:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case LOADING_STATION:
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
                case FLOOR: 
                    this.Elevator.goToSub(/*INSERT TARGET VALUE*/);
            }
        }
        else {
            System.out.println("Error! Ball or disk value unset. Make sure to set value.");
        }
    }
}