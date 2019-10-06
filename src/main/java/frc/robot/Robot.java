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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class Robot extends TimedRobot {
  private Compressor compressor = new Compressor();
  private PowerDistributionPanel pdp = new PowerDistributionPanel();
  private Superstructure superstruct = Superstructure.getInstance();
  private DriveTrain driveBase = DriveTrain.getInstance();
  private Elevator elevator = Elevator.getInstance();
  private Forklift forklift = Forklift.getInstance();
  private Intake intake = Intake.getInstance();
  private Looper looper = new Looper(); 


  private XBox xbox = new XBox(Constants.XBOX_PORT);
  private Joysticks joysticks = new Joysticks(Constants.JOYSTICK_PORT_1, Constants.JOYSTICK_PORT_2);

  @Override
  public void robotInit() {
    compressor.clearAllPCMStickyFaults();
    pdp.clearStickyFaults();
    AnalogInput.setGlobalSampleRate(62500);
    registerLooper();
  }

  @Override
  public void autonomousInit() {
    looper.start();
  }

  @Override
  public void autonomousPeriodic() {
    driveTrainPeriodic();
    elevatorPeriodic();
    intakePeriodic();
    forkliftPeriodic();
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    driveTrainPeriodic();
    elevatorPeriodic();
    intakePeriodic();
    forkliftPeriodic();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    driveTrainPeriodic();
  }

  public void registerLooper(){
      superstruct.registerLoop(looper);
      driveBase.registerLoop(looper);
      elevator.registerLoop(looper);
      forklift.registerLoop(looper);
      intake.registerLoop(looper);
  }

  public void driveTrainPeriodic(){
    superstruct.tankDrive(-joysticks.getY1(), -joysticks.getY2());
  }

  public void elevatorPeriodic(){
    
  }

  public void intakePeriodic(){
    if(xbox.getLeftTrigger() > 0.4){
      superstruct.setIntakeRollers(0.6);
    }
    if(xbox.getButtonBNewPress()){
      superstruct.reverseIntake();
    }
  }

  public void forkliftPeriodic(){
    if(xbox.getRightTrigger() > 0.4){
      superstruct.setForkliftRollers(0.6);
    }
    if(xbox.getButtonANewPress()){
      superstruct.reverseForklift();
    }
  }

}
