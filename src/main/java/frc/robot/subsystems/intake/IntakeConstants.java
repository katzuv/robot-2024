package frc.robot.subsystems.intake;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.InvertedValue;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.MutableMeasure;
import frc.robot.Constants;
import lib.webconstants.LoggedTunableNumber;

import static edu.wpi.first.units.Units.Degrees;

public class IntakeConstants {
    public static final double GEAR_RATIO = 45.62;
    public static final LoggedTunableNumber ANGLE_KP = new LoggedTunableNumber("Intake/kP");
    public static final LoggedTunableNumber ANGLE_KI = new LoggedTunableNumber("Intake/kI");
    public static final LoggedTunableNumber ANGLE_KD = new LoggedTunableNumber("Intake/kD");

    public static final LoggedTunableNumber SPIN_KP = new LoggedTunableNumber("Intake/kP");
    public static final LoggedTunableNumber SPIN_KI = new LoggedTunableNumber("Intake/kI");
    public static final LoggedTunableNumber SPIN_KD = new LoggedTunableNumber("Intake/kD");

    public static final LoggedTunableNumber SPIN_KS = new LoggedTunableNumber("Intake/kS");
    public static final LoggedTunableNumber SPIN_KV = new LoggedTunableNumber("Intake/kV");
    public static final LoggedTunableNumber SPIN_KA = new LoggedTunableNumber("Intake/kA");


    public static final double ANGLE_GEAR_RATIO = 44.44;
    public static final double ANGLE_CURRENT_LIMIT = 40;
    public static final TalonFXConfiguration ANGLE_CONFIGURATION = new TalonFXConfiguration();


    public void initConstants() {
        switch (Constants.CURRENT_MODE) {
            case REAL:
                ANGLE_KP.initDefault(10.0 / 360.0);
                ANGLE_KI.initDefault(0);
                ANGLE_KD.initDefault(0);

                SPIN_KP.initDefault(0);
                SPIN_KI.initDefault(0);
                SPIN_KD.initDefault(0);

                break;
            case SIM:
            case REPLAY:
            default:
                ANGLE_KP.initDefault(10.1 / 360.0);
                ANGLE_KI.initDefault(0);
                ANGLE_KD.initDefault(0);

                SPIN_KP.initDefault(0);
                SPIN_KI.initDefault(0);
                SPIN_KD.initDefault(0);


                break;
        }


        ANGLE_CONFIGURATION
                .withSlot0(
                        new Slot0Configs()
                                .withKP(ANGLE_KP.get())
                                .withKI(ANGLE_KI.get())
                                .withKP(ANGLE_KD.get()))
                .withMotorOutput(
                        new MotorOutputConfigs().withInverted(InvertedValue.Clockwise_Positive))
                .withCurrentLimits(
                        new CurrentLimitsConfigs()
                                .withSupplyCurrentLimitEnable(true)
                                .withStatorCurrentLimitEnable(true)
                                .withStatorCurrentLimit(ANGLE_CURRENT_LIMIT)
                                .withSupplyCurrentLimit(ANGLE_CURRENT_LIMIT))
                .withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(ANGLE_GEAR_RATIO));

    }

    public enum IntakePose {
        UP(MutableMeasure.zero(Degrees)),
        DOWN(MutableMeasure.zero(Degrees));
        final MutableMeasure<Angle> intakePose;

        IntakePose(MutableMeasure<Angle> intakePose) {
            this.intakePose = intakePose;
        }
    }
}
