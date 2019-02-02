

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.LazyVictorSPX;

/**
* Creates CANTalon objects and configures all the parameters we care about to factory defaults. Closed-loop and sensor
* parameters are not set, as these are expected to be set by the application.
*/

public class VictorSPXFactory {

   private final static int kTimeoutMs = 100;

   public static class Configuration {
       public NeutralMode NEUTRAL_MODE = NeutralMode.Coast;
       // This is factory default.
       public double NEUTRAL_DEADBAND = 0.04;

       public boolean ENABLE_CURRENT_LIMIT = false;
       public boolean ENABLE_SOFT_LIMIT = false;
       public boolean ENABLE_LIMIT_SWITCH = false;
       public int FORWARD_SOFT_LIMIT = 0;
       public int REVERSE_SOFT_LIMIT = 0;

       public boolean INVERTED = false;
       public boolean SENSOR_PHASE = false;

       public int CONTROL_FRAME_PERIOD_MS = 5;
       public int MOTION_CONTROL_FRAME_PERIOD_MS = 100;
       public int GENERAL_STATUS_FRAME_RATE_MS = 5;
       public int FEEDBACK_STATUS_FRAME_RATE_MS = 100;
       public int QUAD_ENCODER_STATUS_FRAME_RATE_MS = 100;
       public int ANALOG_TEMP_VBAT_STATUS_FRAME_RATE_MS = 100;
       public int PULSE_WIDTH_STATUS_FRAME_RATE_MS = 100;

       public VelocityMeasPeriod VELOCITY_MEASUREMENT_PERIOD = VelocityMeasPeriod.Period_100Ms;
       public int VELOCITY_MEASUREMENT_ROLLING_AVERAGE_WINDOW = 64;

       public double OPEN_LOOP_RAMP_RATE = 0.0;
       public double CLOSED_LOOP_RAMP_RATE = 0.0;
   }

   private static final Configuration kDefaultConfiguration = new Configuration();
   private static final Configuration kSlaveConfiguration = new Configuration();

   static {
       // This control frame value seems to need to be something reasonable to avoid the Talon's
       // LEDs behaving erratically.  Potentially try to increase as much as possible.
       kSlaveConfiguration.CONTROL_FRAME_PERIOD_MS = 100;
       kSlaveConfiguration.MOTION_CONTROL_FRAME_PERIOD_MS = 1000;
       kSlaveConfiguration.GENERAL_STATUS_FRAME_RATE_MS = 1000;
       kSlaveConfiguration.FEEDBACK_STATUS_FRAME_RATE_MS = 1000;
       kSlaveConfiguration.QUAD_ENCODER_STATUS_FRAME_RATE_MS = 1000;
       kSlaveConfiguration.ANALOG_TEMP_VBAT_STATUS_FRAME_RATE_MS = 1000;
       kSlaveConfiguration.PULSE_WIDTH_STATUS_FRAME_RATE_MS = 1000;
   }

   // Create a CANVictor with the default (out of the box) configuration.
   public static VictorSPX createDefaultTalon(int id) {
       return createVictor(id, kDefaultConfiguration);
   }

   public static VictorSPX createPermanentSlaveVictor(int id, int master_id) {
       final TalonSRX Victor = createVictor(id, kSlaveConfiguration);
       Victor.set(ControlMode.Follower, master_id);
       return Victor;
   }

   public static VictorSPX createVictor(int id, Configuration config) {
       VictorSPX Victor = new LazyVictorSPX(id);
       Victor.set(ControlMode.PercentOutput, 0.0);
Victor.changeMotionControlFramePeriod(config.MOTION_CONTROL_FRAME_PERIOD_MS);
       Victor.clearMotionProfileHasUnderrun(kTimeoutMs);
       Victor.clearMotionProfileTrajectories();

       Victor.clearStickyFaults(kTimeoutMs);

       Victor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
               LimitSwitchNormal.NormallyOpen, kTimeoutMs);
       Victor.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
               LimitSwitchNormal.NormallyOpen, kTimeoutMs);
       Victor.overrideLimitSwitchesEnable(config.ENABLE_LIMIT_SWITCH);

       // Turn off re-zeroing by default.
       Victor.configSetParameter(
               ParamEnum.eClearPositionOnLimitF, 0, 0, 0, kTimeoutMs);
       Victor.configSetParameter(
               ParamEnum.eClearPositionOnLimitR, 0, 0, 0, kTimeoutMs);

       Victor.configNominalOutputForward(0, kTimeoutMs);
       Victor.configNominalOutputReverse(0, kTimeoutMs);
       Victor.configNeutralDeadband(config.NEUTRAL_DEADBAND, kTimeoutMs);

       Victor.configPeakOutputForward(1.0, kTimeoutMs);
       Victor.configPeakOutputReverse(-1.0, kTimeoutMs);

       Victor.setNeutralMode(config.NEUTRAL_MODE);

       Victor.configForwardSoftLimitThreshold(config.FORWARD_SOFT_LIMIT, kTimeoutMs);
       Victor.configForwardSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);

       Victor.configReverseSoftLimitThreshold(config.REVERSE_SOFT_LIMIT, kTimeoutMs);
       Victor.configReverseSoftLimitEnable(config.ENABLE_SOFT_LIMIT, kTimeoutMs);
       Victor.overrideSoftLimitsEnable(config.ENABLE_SOFT_LIMIT);

       Victor.setInverted(config.INVERTED);
       Victor.setSensorPhase(config.SENSOR_PHASE);

       Victor.selectProfileSlot(0, 0);

       Victor.configVelocityMeasurementPeriod(config.VELOCITY_MEASUREMENT_PERIOD, kTimeoutMs);
       Victor.configVelocityMeasurementWindow(config.VELOCITY_MEASUREMENT_ROLLING_AVERAGE_WINDOW,
               kTimeoutMs);

       Victor.configOpenloopRamp(config.OPEN_LOOP_RAMP_RATE, kTimeoutMs);
       Victor.configClosedloopRamp(config.CLOSED_LOOP_RAMP_RATE, kTimeoutMs);

       Victor.configVoltageCompSaturation(0.0, kTimeoutMs);
       Victor.configVoltageMeasurementFilter(32, kTimeoutMs);
       Victor.enableVoltageCompensation(false);

       Victor.enableCurrentLimit(config.ENABLE_CURRENT_LIMIT);

       Victor.setStatusFramePeriod(StatusFrameEnhanced.Status_1_General,
               config.GENERAL_STATUS_FRAME_RATE_MS, kTimeoutMs);
       Victor.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0,
               config.FEEDBACK_STATUS_FRAME_RATE_MS, kTimeoutMs);

       Victor.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature,
               config.QUAD_ENCODER_STATUS_FRAME_RATE_MS, kTimeoutMs);
       Victor.setStatusFramePeriod(StatusFrameEnhanced.Status_4_AinTempVbat,
               config.ANALOG_TEMP_VBAT_STATUS_FRAME_RATE_MS, kTimeoutMs);
       Victor.setStatusFramePeriod(StatusFrameEnhanced.Status_8_PulseWidth,
               config.PULSE_WIDTH_STATUS_FRAME_RATE_MS, kTimeoutMs);

       Victor.setControlFramePeriod(ControlFrame.Control_3_General, config.CONTROL_FRAME_PERIOD_MS);

       return Victor;
   }
}