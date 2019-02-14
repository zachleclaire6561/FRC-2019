/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.controls.*;
import frc.robot.controls.*;
import frc.robot.loops.Looper;


import edu.wpi.first.wpilibj.TimedRobot;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
=======

>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4
import edu.wpi.first.wpilibj.AnalogInput;

public class Robot extends TimedRobot {

<<<<<<< HEAD
  DriveTrain driveBase = new DriveTrain();
  PowerDistributionPanel pdp = new PowerDistributionPanel(0);
  XboxController xboxController = new XboxController(2);
  Joystick j1 = new Joystick(0);
  Joystick j2 = new Joystick(1);
  
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    AnalogInput.setGlobalSampleRate(62500);
=======
  private Superstructure superstruct = Superstructure.getInstance();
  private Looper loops = new Looper(); 


  private XBox xbox = new XBox(Constants.XBOX_PORT);
  private Joysticks joysticks = new Joysticks(Constants.JOYSTICK_PORT_1, Constants.JOYSTICK_PORT_2);

  @Override
  public void robotInit() {
    AnalogInput.setGlobalSampleRate(62500);
    registerLooper();
>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4
  }

  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    driveTrain();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  
  }

<<<<<<< HEAD
}
=======
  public void registerLooper(){
    superstruct.registerLoops(loops);
  }

  public void driveTrain(){
    superstruct.tankDrive(joysticks.getY1(), joysticks.getY2());
  }

}
>>>>>>> c856c3e8ce79fe2f167024ae2edf4f00c500e8b4
