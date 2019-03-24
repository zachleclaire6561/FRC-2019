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


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.cameraserver.CameraServer;


public class Robot extends TimedRobot {

  private Superstructure superstruct = Superstructure.getInstance();
  private DriveTrain driveBase = DriveTrain.getInstance();
  private Elevator elevator = Elevator.getInstance();
  private Forklift forklift = Forklift.getInstance();
  private Climber climber = Climber.getInstance();
  private Intake intake = Intake.getInstance();
  private Looper looper = new Looper(); 



  private XBox xbox = new XBox(Constants.XBOX_PORT);
  private Joysticks joysticks = new Joysticks(Constants.JOYSTICK_PORT_1, Constants.JOYSTICK_PORT_2);




  @Override
  public void robotInit() {
    AnalogInput.setGlobalSampleRate(62500);
    registerLooper();
    CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
  }

  @Override
  public void autonomousInit() {
    looper.start();
    intake.setState(false);
    }

  @Override
  public void autonomousPeriodic() {
    driveTrainPeriodic();
    elevatorPeriodic();
    climberPeriodic();
    //intakePeriodic();
    //Forklift and Intake controls are combined into forkliftPeriodic()
    forkliftPeriodic();
  }

  @Override
  public void teleopInit() {

  }

  @Override
  public void teleopPeriodic() {
    driveTrainPeriodic();
    elevatorPeriodic();
    climberPeriodic();
    //intakePeriodic();
    forkliftPeriodic();
   // visionPeriodic();
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  
  }

  public void registerLooper(){
      superstruct.registerLoop(looper);
      driveBase.registerLoop(looper);
      elevator.registerLoop(looper);
      forklift.registerLoop(looper);
      intake.registerLoop(looper);
  }
 public void climberPeriodic(){
    if(xbox.getButtonA()){
  //    System.out.println("A is being pressed");
      superstruct.reverseClimb();
    }else if(xbox.getButtonX()){
   //   System.out.println("X is being pressed");
      superstruct.runClimb();
    }else{
      superstruct.climbOff();
    }
  }

  public void driveTrainPeriodic(){
   // superstruct.tankDrive(joysticks.getY1(), joysticks.getY2());
   /*  if(joystick.getTrigger2()){
   superstruct.curveDrive(joysticks.getY1(), joysticks);
   }
   else{
     superstruct.curveDrive(joysticks.getX1());
   }
   */
  //if(!joysticks.getTrigger1())
    //superstruct.curveDrive(joysticks.getY1(), joysticks.getX1(), joysticks.getTrigger2());
  //else
   superstruct.tankDrive(-joysticks.getY1(), -joysticks.getY2());
}

  

  public void elevatorPeriodic(){
    
    // if(!xbox.getButtonX()){
    //   if(xbox.getButtonANewPress()){
    //     superstruct.elvState(false);
    //   }
    //   if(xbox.getButtonYNewPress()){
    //     superstruct.elvState(true);
    //   }
    // }
    // else{
       superstruct.elevatorPow(-xbox.getLeftY());
    // }
  }
  /*
  public void intakePeriodic(){
    if(xbox.getLeftTrigger() > 0.4){
     // System.out.println("Left trigger is activated");
      //superstruct.setIntakeRollers(-0.6);
      superstruct.setForkliftRollers(0.6);
    }
    else{
      superstruct.setIntakeRollers(0.0);
      superstruct.setForkliftRollers(0.0);
    }
  }
  */
  public void forkliftPeriodic(){
    if(xbox.getRightTrigger() > 0.4){
      superstruct.setForkliftRollers(-0.6);
      superstruct.setIntakeRollers(0.6);
    }
    else if(xbox.getLeftTrigger() > 0.4){
    //  System.out.println("Left trigger is activated");
      //superstruct.setIntakeRollers(-0.6);
      superstruct.setForkliftRollers(1);
      //superstruct.setIntakeRollers(-0.6);
    }
    else if(xbox.getButtonBNewPress()){
    //  System.out.println("B button is being pressed");
      superstruct.reverseIntake();
    }
    else{
      superstruct.setForkliftRollers(0.0);
      superstruct.setIntakeRollers(0.0);
    }
  }
}