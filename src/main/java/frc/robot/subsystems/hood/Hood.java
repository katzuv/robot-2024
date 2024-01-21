package frc.robot.subsystems.hood;

import edu.wpi.first.units.Angle;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.littletonrobotics.junction.Logger;

public class Hood extends SubsystemBase {
    private static Hood INSTANCE = null;
    private final HoodInputsAutoLogged inputs = HoodIO.inputs;
    private final HoodIO io;

    /**
     * Constructor for Hood subsystem.
     *
     * @param io IO of the subsystem.
     */
    private Hood(HoodIO io) {
        this.io = io;
    }

    /**
     * Gets the single instance of hoodSubsystem.
     *
     * @return The single instance of hoodSubsystem.
     */
    public static Hood getInstance() {
        return INSTANCE;
    }

    public static void initialize(HoodIO io) {
        INSTANCE = new Hood(io);
    }

    /**
     * Sets the position of the hood.
     *
     * @param angle The angle of the hood to set.
     */
    public void setAngle(MutableMeasure<Angle> angle) {
        inputs.angleSetpoint = angle;
    }

    public MutableMeasure<Angle> getAngle() {
        return inputs.angle;
    }

    public boolean atSetpoint() {
        return io.atSetpoint();
    }

    public Command getResetAbsoluteEncoderCommand(){
        return new RunCommand(() -> io.resetAbsoluteEncoder(), this);
    }

    public Command getUpdateInternalEncoderCommand(){
        return new RunCommand(() -> io.updateInternalEncoder(), this);
    }

    /** Updates the state of the hood. */
    @Override
    public void periodic() {
        io.setAngle(inputs.angleSetpoint);

        io.updateInputs();
        Logger.processInputs("Hood", inputs);
    }
}
