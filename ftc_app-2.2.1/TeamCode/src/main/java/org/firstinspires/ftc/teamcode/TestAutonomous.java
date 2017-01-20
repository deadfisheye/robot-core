/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="Test Autonomous", group="Autonomous")
//@Disabled
public class TestAutonomous extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();
    DcMotor leftMotor = null;
    DcMotor rightMotor = null;
    DcMotor launcherMotor = null;
    
    int phase;
    int currentPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        //leftMotor  = hardwareMap.dcMotor.get("lMotor");
        //rightMotor = hardwareMap.dcMotor.get("rMotor");
        //leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //currentPosition = leftMotor.getCurrentPosition();

        launcherMotor = hardwareMap.dcMotor.get("launcher");
        launcherMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        phase = 0;

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        //rightMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        // rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        test2 = false;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();

            if(/*leftMotor.isBusy()&&rightMotor.isBusy()*/launcherMotor.isBusy()) {
                telemetry.addData("busy", null);
            }
            else {
                if (phase == 0) {

                    //leftMotor.setTargetPosition(goPosition(1));
                    //rightMotor.setTargetPosition(goPosition(-1));
                    //leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    //rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    //leftMotor.setPower(0.5);
                    //rightMotor.setPower(0.0);

                    launcherMotor.setTargetPosition(launcherMotor.getCurrentPosition()+1120);
                    launcherMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    launcherMotor.setPower(0.5);
                    phase++;

                }
                else if(phase == 1) {
//                    telemetry.addData("Test2 Running", 2);
//                    leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                    rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                    leftMotor.setTargetPosition(goPosition(-1));
//                    rightMotor.setTargetPosition(goPosition(-1));
//                    leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    launcherMotor.setPower(0.0);
                    phase++;
                }
                else if(phase == 2) {
                    //leftMotor.setPower(0.0);
                    //rightMotor.setPower(0.0);
                }

            }

            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
            // leftMotor.setPower(-gamepad1.left_stick_y);
            // rightMotor.setPower(-gamepad1.right_stick_y);

            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
        }
    }

    public int goPosition(double distance) {
        return currentPosition + (int)(distance*4779);
    }

}