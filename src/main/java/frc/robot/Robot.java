/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.controls.*;

import edu.wpi.first.wpilibj.TimedRobot;

import edu.wpi.first.wpilibj.AnalogInput;



public class Robot extends TimedRobot {

  Superstructure superstruct = new Superstructure();
  
  @Override
  public void robotInit() {
    AnalogInput.setGlobalSampleRate(62500);
    
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
    
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  
  }

}
