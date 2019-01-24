package frc.robot;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Subsystems.DriveTrain;

public class GyroAngleRotator extends Subsystems {
    int t1Port;
    int t2Port;
    int v1Port;
    int v2Port;
    double currentAngle;
    double rotationTimes;
    double modifiedAngle;
    double finalCurrentAngle;
    boolean goRight;

    DriveTrain rotator;
    /* UNCOMMENT THIS WHEN THE NAVXGYROSCOPE CLASS IS IN THE SAME FOLDER!!!!!!
    navXGyroscope gyroscope = new navXGyroscope();
    */

    public GyroAngleRotator(int t1Port, int t2Port, int v1Port, int v2Port) {
        this.t1Port = t1Port;
        this.t2Port = t2Port;
        this.v1Port = v1Port;
        this.v2Port = v2Port;
        rotator = new DriveTrain(t1Port, t2Port, v1Port, v2Port);
    }
    public void rotate(double target) {
        if (target < 0 || target > 360) { //Catching bad input
            System.out.println("Please input an angle between 0 and 360 degrees!");
            break;
        }
        double currentAngle = gyroscope.getTotalAngle(); //TEST CASE: -370 DEGREES
        rotationTimes = Math.floor(currentAngle / 360); //-370/360 = -1.02, floored to -2 because of how Math.floor works
            int rTTrueFalse = (rotationTimes < 0) ? 1 : 0; //Yes, -2 is negative
        rotationTimes = rotationTimes + (1 * rTTrueFalse); //Since there have actually been "-1" rotations, -2+1=-1 (correction)
        modifiedAngle = currentAngle - (360 * rotationTimes); //-370 - -360 == mA= -10deg
            int mATrueFalse = (modifiedAngle < 0) ? 1 : 0; //-10 is negative
        modifiedAngle = modifiedAngle + (360 * mATrueFalse); //Add 360 to get a proper bearing (350 degrees)
            goRight = (target - currentAngle > 0) ? true : false; //350-20 = 330, so you should go right if you CANNOT CROSS 0/360 LINE
        if (Math.abs(target - currentAngle) > 180) { //Checks to see if contiguous distance is the shortest distance, in this case it is not
            goRight = !goRight; //Thus, the robot must turn left to get from 20deg to 330deg the quickest
        }
        while (Math.abs(target - currentAngle) > 0.25) { //While the discrepancy is more than a quarter of a degree;
            if (goRight) { //If you have to go right:
                rotator.tankDrive(1, -1); //then go right, otherwise
            } else if (!goRight) {
                rotator.tankDrive(-1, 1); //go left. The program stops turning when the discrepancy is within a good limit, and then that's it!
            } else {
                System.out.println("Sorry, but the robot cannot rotate along the Z axis (goRight was not assigned a boolean value, something has gone terribly wrong.)");
            }
        }
    }     
}
        /*
        Old code, could be used for a practice thing about optimization:
        Thanks to Kanishk for helping to optimize:
        if (Math.abs(currentAngle) > 360) {
            rotationTimes = Math.floor(Math.abs(currentAngle)/360);
            if (positive) {
                modifiedAngle = (currentAngle - (360 * rotationTimes));
            } else if (negative) {
                modifiedAngle = (currentAngle + (360 * rotationTimes));
            }
        } else {
            modifiedAngle = currentAngle;
        }
        --Spencer
        */