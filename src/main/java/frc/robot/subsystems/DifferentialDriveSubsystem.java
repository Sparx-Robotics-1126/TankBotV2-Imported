// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DifferentialDriveSubsystem extends SubsystemBase {
  
  CANSparkMax leftFrontMotor = new CANSparkMax(Constants.DifferentialDriveConstants.leftFrontCANID, CANSparkLowLevel.MotorType.kBrushless);
  CANSparkMax rightFrontMotor = new CANSparkMax(Constants.DifferentialDriveConstants.rightFrontCANID, CANSparkLowLevel.MotorType.kBrushless);
  CANSparkMax leftBackMotor = new CANSparkMax(Constants.DifferentialDriveConstants.leftBackCANID, CANSparkLowLevel.MotorType.kBrushless);
  CANSparkMax rightBackMotor = new CANSparkMax(Constants.DifferentialDriveConstants.rightBackCANID, CANSparkLowLevel.MotorType.kBrushless);
  
  RelativeEncoder leftEncoder = leftFrontMotor.getEncoder();
  RelativeEncoder rightEncoder = leftFrontMotor.getEncoder();

  DifferentialDrive differentialDrive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
  private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()), leftEncoder.getPosition(),
  rightEncoder.getPosition());;

  /** Creates a new ExampleSubsystem. */
public DifferentialDriveSubsystem() {
    leftFrontMotor.restoreFactoryDefaults();
    leftBackMotor.restoreFactoryDefaults();
    rightFrontMotor.restoreFactoryDefaults();
    rightBackMotor.restoreFactoryDefaults();

    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);

    leftBackMotor.follow(leftFrontMotor);
    rightBackMotor.follow(rightFrontMotor);

    leftFrontMotor.setInverted(false);
    rightFrontMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
      odometry.update(
        Rotation2d.fromDegrees(getHeading()), leftEncoder.getPosition(), -rightEncoder.getPosition());

  }

  public double getHeading() {
    return PigeonSubsystem.getInstance().getAngle();
  }

}
