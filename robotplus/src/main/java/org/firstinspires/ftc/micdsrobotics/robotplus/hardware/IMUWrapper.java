package org.firstinspires.ftc.micdsrobotics.robotplus.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.bosch.NaiveAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Wrapper class for the REV's onboard IMU
 * @author Alex Migala, Nick Clifford, Blake Abel
 * @since 9/28/17
 */

public class IMUWrapper {

    private BNO055IMU imu;

    public IMUWrapper(HardwareMap map) {
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.angleUnit            = BNO055IMU.AngleUnit.DEGREES;
        params.accelUnit            = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        params.calibrationDataFile  = "AdafruitIMUCalibration.json"; // see the calibration sample opmode
        params.loggingEnabled       = true;
        params.loggingTag           = "IMU";
        //trying to just use the bad but default one [NAH]
        // Our custom IMUAccelerationIntegrator class is causing stack overflows and crashing the app
        params.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        this.imu = map.get(BNO055IMU.class, "sensor_imu");
        this.imu.initialize(params);

        this.imu.startAccelerationIntegration(new Position(), new Velocity(),5);
    }

    public BNO055IMU getIMU() { return this.imu; }

    public Orientation getOrientation() { return this.imu.getAngularOrientation(); }

    public Position getPosition() { return this.imu.getPosition(); }

    public BNO055IMU.Parameters getInitilizationParameters(){
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.angleUnit            = BNO055IMU.AngleUnit.DEGREES;
        params.accelUnit            = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        params.calibrationDataFile  = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        params.loggingEnabled       = true;
        params.loggingTag           = "IMU";
        //trying to just use the bad but default one [NAH]
        params.accelerationIntegrationAlgorithm = new IMUAccelerationIntegrator();

        return params;
    }

}
