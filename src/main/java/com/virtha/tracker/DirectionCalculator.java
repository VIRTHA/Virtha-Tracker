package com.virtha.tracker;

import org.bukkit.Location;

public class DirectionCalculator {

    public static String calculateArrow(Location playerLoc, Location targetLoc, ConfigManager configManager) {
        double dx = targetLoc.getX() - playerLoc.getX();
        double dz = targetLoc.getZ() - playerLoc.getZ();

        if (Math.abs(dx) < 0.0001 && Math.abs(dz) < 0.0001) {
            return configManager.getArrowFront();
        }

        double targetYaw = Math.toDegrees(Math.atan2(dz, dx)) - 90.0;
        double relativeAngle = targetYaw - playerLoc.getYaw();
        relativeAngle = (relativeAngle % 360.0 + 360.0) % 360.0;

        if (relativeAngle >= 337.5 || relativeAngle < 22.5) {
            return configManager.getArrowFront();
        } else if (relativeAngle >= 22.5 && relativeAngle < 67.5) {
            return configManager.getArrowFrontRight();
        } else if (relativeAngle >= 67.5 && relativeAngle < 112.5) {
            return configManager.getArrowRight();
        } else if (relativeAngle >= 112.5 && relativeAngle < 157.5) {
            return configManager.getArrowBackRight();
        } else if (relativeAngle >= 157.5 && relativeAngle < 202.5) {
            return configManager.getArrowBack();
        } else if (relativeAngle >= 202.5 && relativeAngle < 247.5) {
            return configManager.getArrowBackLeft();
        } else if (relativeAngle >= 247.5 && relativeAngle < 292.5) {
            return configManager.getArrowLeft();
        } else {
            return configManager.getArrowFrontLeft();
        }
    }
}
