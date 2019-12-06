# robotplus
A collection of reusable code for FTC robots, structured for use as a Maven package.

## Robot Tutorial
To start, create a subclass of `Robot` to represent your team's robot for the year. 
```java
public class MyRobot extends Robot<MecanumDrive> { // Replace MecanumDrive with whatever Drivetrain type your robot uses.
    @Override
    public void initHardware(HardwareMap hardwareMap) {
        // Place your hardware config initialization code in here.
    }
    
    @Override
    public double voltageDistanceAdapter(double v) {
        // A function that calculates the distance traveled by the robot in one second based on the battery voltage. 
    }
}
```
Check the `Robot` source for further information.

It can then be used in your opmodes.
```java
// Teleop example
@TeleOp(name = "blah")
public class BlahTeleOp extends OpMode {
    private MyRobot robot = new MyRobot();
    
    @Override
    public void init() {
        // Make sure this is here or you might get runtime errors!
        robot.initHardware(hardwareMap);
    }
    
    // rest of your teleop code...
}

// Autonomous example
@Autonomous(name = "blah")
public class BlahAutonomous extends LinearOpMode {
    private MyRobot robot = new MyRobot();
        
    @Override
    public void runOpMode() {
        // Same as before!
        robot.initHardware(hardwareMap);
        
        // rest of your autonomous code...
    }
}
```
