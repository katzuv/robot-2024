package frc.robot.subsystems.hood;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.MotionMagicExpoTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.Angle;
import edu.wpi.first.units.MutableMeasure;

public class HoodIOReal implements HoodIO {
    private final TalonFX motor;
    private final TalonFXConfiguration motorConfiguration = new TalonFXConfiguration();
    private final TalonFXConfigurator motorConfigurator;
    private final MotionMagicExpoTorqueCurrentFOC positionControl =
            new MotionMagicExpoTorqueCurrentFOC(0);
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    public HoodIOReal() {
        motor = new TalonFX(6); // TODO: to be changed

        motorConfigurator = motor.getConfigurator();

        motorConfiguration.Feedback.SensorToMechanismRatio = HoodConstants.GEAR_RATIO;
        motorConfiguration.Slot0.kP = HoodConstants.kP.get();
        motorConfiguration.Slot0.kI = HoodConstants.kI.get();
        motorConfiguration.Slot0.kD = HoodConstants.kD.get();
        motorConfiguration.Slot0.kS = HoodConstants.kS.get();
        motorConfiguration.Slot0.kV = HoodConstants.kV.get();
        motorConfiguration.Slot0.kA = HoodConstants.kA.get();
        motorConfiguration.Slot0.kG = HoodConstants.kG.get();

        motorConfigurator.apply(motorConfiguration);
    }