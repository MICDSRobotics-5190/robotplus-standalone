package org.firstinspires.ftc.micdsrobotics.robotplus.autonomous;

public interface VoltageDistanceAdapter {
    /**
     * Calculates the distance that the robot will travel in one second based on the voltage.
     * @see TimeOffsetVoltage#calculateDistance(VoltageDistanceAdapter, double, double)
     * @param voltage the voltage to be calculated
     * @return the distance, in centimeters
     */
    double voltageToDistance(double voltage);
}
