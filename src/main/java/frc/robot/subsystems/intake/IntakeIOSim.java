package frc.robot.subsystems.intake;

import com.ctre.phoenix6.controls.PositionVoltage;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Units;
import edu.wpi.first.units.Velocity;
import lib.motors.SparkMaxSim;
import lib.motors.TalonFXSim;

public class IntakeIOSim implements IntakeIO {
    private final TalonFXSim angleMotor;
    private final SparkMaxSim frontRoller;
    private final SparkMaxSim centerRoller;
    private final PositionVoltage positionRequest = new PositionVoltage(0);

    public IntakeIOSim() {
        angleMotor =
                new TalonFXSim(
                        1, IntakeConstants.GEAR_RATIO, 0.3, 360 * IntakeConstants.GEAR_RATIO);
        frontRoller = new SparkMaxSim(1, 1, 0.5, 1);
        centerRoller = new SparkMaxSim(1, 1, 0.5, 1);
        angleMotor.setController(IntakeConstants.angleController);
    }

    @Override
    public void setAngle(MutableMeasure<Angle> angle) {
        angleMotor.setControl(positionRequest.withPosition(angle.in(Units.Degrees)));
    }

    @Override
    public void setRollerSpeed(MutableMeasure<Velocity<Angle>> speed) {
        frontRoller.set(speed.in(Units.RotationsPerSecond));
    }

    @Override
    public void setCenterRollerSpeed(MutableMeasure<Velocity<Angle>> speed) {
        centerRoller.set(speed.in(Units.RotationsPerSecond));
    }

    @Override
    public void updateInputs() {
        inputs.currentAngle.mut_replace((angleMotor.getPosition()), Units.Rotations);
        inputs.rollerSpeed.mut_replace(frontRoller.getVelocity(), Units.RotationsPerSecond);
        inputs.centerRollerSpeed.mut_replace(centerRoller.getVelocity(), Units.RotationsPerSecond);
        inputs.angleMotorVoltage.mut_replace(angleMotor.getAppliedVoltage(), Units.Volts);
        inputs.spinMotorVoltage.mut_replace(frontRoller.getBusVoltage(), Units.Volts);
        inputs.centerMotorVoltage.mut_replace(centerRoller.getBusVoltage(), Units.Volts);
    }
}
