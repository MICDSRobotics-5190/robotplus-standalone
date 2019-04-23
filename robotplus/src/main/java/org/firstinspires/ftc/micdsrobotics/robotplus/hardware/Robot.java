package org.firstinspires.ftc.micdsrobotics.robotplus.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Class representing the entire robot for the season.
 * Refactored during 2019 off-season to allow minimum modifications to robotplus.
 * @since 4/10/17
 * @author Blake Abel, Alex Migala, Nick Clifford
 */
public abstract class Robot {
    /**
     * the Robot's drivetrain (can be any type)
     */
    private Drivetrain drivetrain;

    /**
     * Initializes drivetrain and other necessary hardware (i.e. IMU, color sensor).
     * @param hardwareMap the hardware map containing configured devices
     */
    public abstract void initHardware(HardwareMap hardwareMap);

    /**
     * Calculates the distance that the robot will travel in one second based on the voltage.
     * @see org.firstinspires.ftc.micdsrobotics.robotplus.autonomous.TimeOffsetVoltage#calculateDistance(Robot, double, double)
     * @param voltage the voltage to be calculated
     * @return the distance
     */
    public abstract double voltageDistanceAdapter(double voltage);

    /**
     * Stops the robot completely.
     */
    public void stopMoving(){
        drivetrain.setPower(0);
    }

    /**
     * Returns the drivetrain the robot is set to use
     * @return {@link Robot#drivetrain}
     */
    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    /**
     * Sets the robot's drivetrain. Only used in subclasses for initialization.
     * @param drivetrain {@link Robot#drivetrain}
     */
    protected void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }
}
