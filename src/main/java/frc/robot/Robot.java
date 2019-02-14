/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.controls.controllers.*;
import frc.robot.loops.Looper;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.AnalogInput;

public class Robot extends TimedRobot {

  private Superstructure superstruct = Superstructure.getInstance();
  private Looper loops = new Looper(); 


  private XBox xbox = new XBox(Constants.XBOX_PORT);
  private Joysticks joysticks = new Joysticks(Constants.JOYSTICK_PORT_1, Constants.JOYSTICK_PORT_2);

  @Override
  public void robotInit() {
    AnalogInput.setGlobalSampleRate(62500);
    registerLooper();
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

  public void registerLooper(){
    superstruct.registerLoops(loops);
  }

  public void driveTrain(){
    superstruct.tankDrive(joysticks.getY1(), joysticks.getY2());
  }

}
