package org.usfirst.frc.team6203.robot.subsystems;

import org.usfirst.frc.team6203.robot.OI;
import org.usfirst.frc.team6203.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Chassis extends Subsystem {

	public SpeedControllerGroup m_left, m_right;
	public DifferentialDrive drive;
	public Victor leftFrontMotor, rightFrontMotor, leftBackMotor, rightBackMotor;
	public ADIS16448_IMU imu;

	private final double kAngleSetpoint = 0.0;

	public Chassis() {
		leftFrontMotor = new Victor(RobotMap.leftMotorF);
		rightFrontMotor = new Victor(RobotMap.rightMotorF);
		leftBackMotor = new Victor(RobotMap.leftMotorB);
		rightBackMotor = new Victor(RobotMap.rightMotorB);

		m_left = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
		m_right = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);

		m_left.setInverted(true);
		m_right.setInverted(true);

		imu = new ADIS16448_IMU();

		drive = new DifferentialDrive(m_left, m_right);
	}

	public void initDefaultCommand() {
	}

	public void tankDrive(double a, double b) {
		SmartDashboard.putString("type", "tank");
		drive.tankDrive(a * 0.5, b * 0.5);
	}

	public void driveStraight(double speed) {
		double turningValue = (kAngleSetpoint - imu.getAngle());
		turningValue = Math.copySign(turningValue, -speed); 

		drive.arcadeDrive(speed, turningValue);
	}


	public void arcadeDrive() {
		double xspeed = OI.driverStick.getX();
		double yspeed = OI.driverStick.getY();

		drive.tankDrive(-(yspeed + xspeed) / 1.5, -(yspeed - xspeed) / 1.5);
	}

}
