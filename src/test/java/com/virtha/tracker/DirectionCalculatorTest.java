package com.virtha.tracker;

import org.bukkit.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionCalculatorTest {

    private final DummyConfigManager dummyConfig = new DummyConfigManager();

    @Test
    public void testFrontArrowFacingSouthTargetSouth() {
        // Player facing South (yaw = 0), target is South (0, 0, 100)
        Location player = new Location(null, 0, 64, 0, 0f, 0f);
        Location target = new Location(null, 0, 64, 100);

        String result = DirectionCalculator.calculateArrow(player, target, dummyConfig);
        assertEquals("↑", result);
    }

    @Test
    public void testRightArrowFacingSouthTargetWest() {
        // Player facing South (yaw = 0), target is West (-100, 64, 0) -> 90 deg relative
        Location player = new Location(null, 0, 64, 0, 0f, 0f);
        Location target = new Location(null, -100, 64, 0);

        String result = DirectionCalculator.calculateArrow(player, target, dummyConfig);
        assertEquals("→", result);
    }

    @Test
    public void testBackArrowFacingSouthTargetNorth() {
        // Player facing South (yaw = 0), target is North (0, 64, -100) -> 180 deg relative
        Location player = new Location(null, 0, 64, 0, 0f, 0f);
        Location target = new Location(null, 0, 64, -100);

        String result = DirectionCalculator.calculateArrow(player, target, dummyConfig);
        assertEquals("↓", result);
    }

    @Test
    public void testLeftArrowFacingSouthTargetEast() {
        // Player facing South (yaw = 0), target is East (100, 64, 0) -> 270 deg relative
        Location player = new Location(null, 0, 64, 0, 0f, 0f);
        Location target = new Location(null, 100, 64, 0);

        String result = DirectionCalculator.calculateArrow(player, target, dummyConfig);
        assertEquals("←", result);
    }

    @Test
    public void testFrontRightArrowFacingNorthTargetNorthEast() {
        // Player facing North (yaw = 180), target is North-East (100, 64, -100)
        Location player = new Location(null, 0, 64, 0, 180f, 0f);
        Location target = new Location(null, 100, 64, -100);

        String result = DirectionCalculator.calculateArrow(player, target, dummyConfig);
        assertEquals("↗", result);
    }

    @Test
    public void testSameLocationReturnsFront() {
        Location player = new Location(null, 10, 64, 10, 45f, 0f);
        Location target = new Location(null, 10, 64, 10);

        String result = DirectionCalculator.calculateArrow(player, target, dummyConfig);
        assertEquals("↑", result);
    }

    private static class DummyConfigManager extends ConfigManager {
        public DummyConfigManager() {
            super(null);
        }

        @Override
        public void loadConfig() {}

        @Override
        public String getArrowFront() { return "↑"; }
        @Override
        public String getArrowFrontRight() { return "↗"; }
        @Override
        public String getArrowRight() { return "→"; }
        @Override
        public String getArrowBackRight() { return "↘"; }
        @Override
        public String getArrowBack() { return "↓"; }
        @Override
        public String getArrowBackLeft() { return "↙"; }
        @Override
        public String getArrowLeft() { return "←"; }
        @Override
        public String getArrowFrontLeft() { return "↖"; }
    }
}
