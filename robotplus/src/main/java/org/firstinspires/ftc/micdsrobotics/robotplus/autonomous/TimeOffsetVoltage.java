package org.firstinspires.ftc.micdsrobotics.robotplus.autonomous;

import android.util.Log;

import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;

/**
 * Calculates the delay time necessary for a given voltage
 * @author Alex M, Blake A
 * @since 1/22/18
 */
public class TimeOffsetVoltage {
    // Utility class, so private constructor, à la java.lang.Math
    private TimeOffsetVoltage() { }

    /**
     * Corrects the voltage based on the velocity of the robot; it is a primer for the DistanceVoltage function
     * @param voltage the current voltage of the robot
     * @param velocity the velocity of the robot
     * @return the primed voltage
     * @deprecated this is untested
     */
    private static double timeVoltage(double voltage, double velocity) {
        System.out.println(velocity * voltage);
        Log.i("[TimeTest]", "TimeVoltage: " + velocity * voltage);
        return velocity * voltage;
    }

    /**
     * This will find the corrected distance
     * @param distance the distance that the robot travels in one second at a given voltage
     * @param target the target distance
     * @return the corrected time
     */
    private static double calculateCorrectedTime(double distance, double target) {
        double factor = 1/distance;
        return (target * factor * 1000);
    }

    /**
     * Calculates the delay time to go at a given distance. Note that it only works for a velocity of 1
     * @param robot the robot that will move accurately
     * @param voltage the voltage that the robot is currently at
     * @param desiredDistance the distance (in cm) that the robot should go
     * @return the delay time in ms
     */
    public static long calculateDistance(Robot robot, double voltage, double desiredDistance) {
        return (long)calculateCorrectedTime(robot.voltageDistanceAdapter(voltage), desiredDistance - 10);
    }
}
