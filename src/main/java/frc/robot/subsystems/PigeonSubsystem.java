package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Pigeon2Configuration;
import com.ctre.phoenix6.hardware.Pigeon2;

// import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class PigeonSubsystem extends SubsystemBase {
    private static PigeonSubsystem instance = null;
    private final Pigeon2 pigeon;

    private PigeonSubsystem() {
        pigeon = new Pigeon2(Constants.DifferentialDriveConstants.Pigeon2ID);
        initPigeon();
    }

    public static PigeonSubsystem getInstance() {
        if(instance == null) {
            instance = new PigeonSubsystem();
        }
        return instance;
    }

    private void initPigeon() {
        var toApply = new Pigeon2Configuration();
        toApply.MountPose.MountPosePitch = 0;
        toApply.MountPose.MountPoseYaw = -90;

        pigeon.getConfigurator().apply(toApply);
        
        pigeon.getPitch().setUpdateFrequency(1000);
        pigeon.setYaw(0, .1);

        pigeon.getYaw().setUpdateFrequency(100);
        pigeon.getYaw().waitForUpdate(.1);
    }

    public double getYaw() {
        return pigeon.getYaw().getValue();
    }

    public double getPitch() {
        return pigeon.getPitch().getValue() * -1;
    }

    public void setYaw(double yaw) {
        pigeon.setYaw(yaw, 10);
    }

    public double getAngle() {
        return pigeon.getAngle();
    }
}
