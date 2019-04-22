/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.micdsrobotics.robotplus.inputtracking;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.micdsrobotics.robotplus.hardware.Robot;
import org.firstinspires.ftc.micdsrobotics.robotplus.inputtracking.Input;
import org.firstinspires.ftc.micdsrobotics.robotplus.inputtracking.InputReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Opmode for reading the file recorded via {@link RecordingHardware} or {@link RecordingGamepad}.
 * Unlike {@link Playback}, this OpMode logs to the computer and doesn't move any hardware. It should
 * be used for making sur ethat it reads in sync with the runtime and checking the values of any inputs.
 * @since 3/27/18
 * @author Blake Abel
 */
@Autonomous(name="Playback Testing", group="Recording")
public class PlaybackTesting extends LinearOpMode implements Filename{

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private Robot robot;

    private MecanumDrive drivetrain;

    private ArrayList<Input> inputs;

    private FileInputStream inputStream;
    private InputReader inputReader = new InputReader();


    @Override
    public void runOpMode() {

        robot = new Robot(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();

        try {
            inputStream = hardwareMap.appContext.openFileInput(FILENAME);
            Log.d("READER", "Opened file for reading");
        } catch (FileNotFoundException error){
            Log.e("READER", "Couldn't create input stream for file");
        }

        try {
            inputs = inputReader.readJson(inputStream);
            Log.d("READER", "Read the file's contents!");
        } catch (IOException error){
            Log.e("READER", "Couldn't read JSON from file.");
        }

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        for (Input input : inputs) {
            long catchUp = (long)((input.getCurrentTime() - runtime.time()) * 1000);
            if(catchUp < 0){
                catchUp = 0;
            }
            sleep(catchUp);
            Log.v("READER", input.toString() + " Runtime: " + runtime.toString() + " Lag: " + (long)((input.getCurrentTime() - runtime.time()) * 1000));
        }

    }
}
