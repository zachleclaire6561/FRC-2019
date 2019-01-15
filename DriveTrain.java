//  This is the class for the Drive Train. It will contain all of the functions the drive train will us
package frc.robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class DriveTrain extends Subsystems{
    int t1Port;
    int t2Port;
    int v1Port;
    int v2Port;
    WPI_TalonSRX talon1;
    WPI_TalonSRX talon2;
    WPI_VictorSPX victor1;
    WPI_VictorSPX victor2;
    DifferentialDrive m_robot;
    public DriveTrain(int t1Port, int t2Port, int v1Port, int v2Port){
        this.t1Port = t1Port;
        this.t2Port = t2Port;
        this.v1Port = v1Port;
        this.v2Port = v2Port;
        talon1 = new WPI_TalonSRX(t1Port);
        talon2 = new WPI_TalonSRX(t2Port); 
        victor1 = new WPI_VictorSPX(v1Port);
        victor2 = new WPI_VictorSPX(v2Port);
        m_robot = new DifferentialDrive(); // Wait until the motor controllers have a position on the robot
    }

    public void tankDrive(double speed1, double speed2){
        talon1.set(speed1);
        talon2.set(speed2);
        victor1.set(speed1);
        victor2.set(speed2); 
    }

    public void brake(){
        talon1.setNeutralMode(NeutralMode.Brake);
        talon2.setNeutralMode(NeutralMode.Brake);
        victor1.setNeutralMode(NeutralMode.Brake);
        victor1.setNeutralMode(NeutralMode.Brake);
    }

    public void coast(){
        talon1.setNeutralMode(NeutralMode.Coast);
        talon2.setNeutralMode(NeutralMode.Coast);
        victor1.setNeutralMode(NeutralMode.Coast);
        victor2.setNeutralMode(NeutralMode.Coast);
    }

}