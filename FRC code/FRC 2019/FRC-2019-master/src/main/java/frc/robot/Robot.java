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
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.TimedRobot;

//import static org.junit.Assert.assertArrayEquals;

import edu.wpi.first.wpilibj.AnalogInput;

public class Robot extends TimedRobot {

  private Superstructure superstruct = Superstructure.getInstance();
  private DriveTrain driveBase = DriveTrain.getInstance();
  private Elevator elevator = Elevator.getInstance();
  private Forklift forklift = Forklift.getInstance();
  private Intake intake = Intake.getInstance();
  private Looper looper = new Looper(); 
  private Compressor comp = new Compressor();
  private PowerDistributionPanel pDP = new PowerDistributionPanel();


  private XBox xbox = new XBox(Constants.XBOX_PORT);
  private Joysticks joysticks = new Joysticks(Constants.JOYSTICK_PORT_1, Constants.JOYSTICK_PORT_2);

  @Override
  public void robotInit() {
    AnalogInput.setGlobalSampleRate(62500);
    registerLooper();
    comp.clearAllPCMStickyFaults();
    pDP.clearStickyFaults();
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
    //superstruct.elevatorMinHeight();
  }

  @Override
  public void teleopPeriodic() {
    driveTrainPeriodic();
    elevatorPeriodic();
    intakePeriodic();
    forkliftPeriodic();
    climberPeriodic();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    System.out.println("Encoder: " + elevator.getEncoderValue());
    System.out.println("Banner: " + elevator.getBanner());
    System.out.println("Speed: " + elevator.getElevatorSpeed());
    elevatorPeriodic();
    intakePeriodic();
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

  /*
    instructions: 
    - get rocket heights (in encoder units) for robot 1 encoder rotation = 7.8 in 
    - calibrate P controller ()
    - un-comment the code bellow and comment the setPower thingy
    - test if overall code works
  */
  public void elevatorPeriodic(){
    /*
    if(xbox.getBack()){
      superstruct.toMax();
    }
    else if(xbox.getStart()){
      superstruct.Reset();
    }
    else if(xbox.getRightBumper()){
      //superstruct.incrimentHeight();
      superstruct.engageBrake();
    }
    else if(xbox.getLeftBumper()){
      //superstruct.decrimentHeight();
      superstruct.releaseBrake();
    }
    */
  // elevator.setPower(-1*xbox.getLeftY());
  
  }

  public void intakePeriodic(){
    if(xbox.getLeftTrigger() > 0.4){
      superstruct.setIntakeRollers(0.6);
    }
    else{
      superstruct.setIntakeRollers(0);
    }
    if(xbox.getButtonBNewPress()){
      superstruct.reverseIntake();
    }
  }

  public void forkliftPeriodic(){
    if(xbox.getLeftTrigger() > 0.4){
      superstruct.setForkliftRollers(-0.6);
    }
    else if(xbox.getRightTrigger() > 0.4){
      superstruct.setForkliftRollers(0.6);
    }
    else{
      superstruct.setForkliftRollers(0);
    }
    if(xbox.getButtonANewPress()){
      superstruct.reverseForklift();
    }
  }

  
  public void climberPeriodic(){
    if(xbox.getRightY()>0.3){
      superstruct.runClimber();
    }
    else if(xbox.getRightY()<-0.3){
      superstruct.reverseClimber();
    }
    else{
      superstruct.stopClimber();
    }
  }
}
