## 1. Configuration & Data Model Updates

- [x] 1.1 Update `ConfigManager.java` to load default `arrows` YAML section and store directional octant arrow symbols (`front`, `front_right`, `right`, `back_right`, `back`, `back_left`, `left`, `front_left`).
- [x] 1.2 Update `src/main/resources/config.yml` with default `arrows` mapping and documentation comments.

## 2. Directional Math & Cache Utility

- [x] 2.1 Implement `DirectionCalculator` utility (or methods in `DistanceCache`) to compute relative 2D angle $(\text{targetAngle} - \text{playerYaw}) \pmod{360}$ and resolve the appropriate octant arrow.
- [x] 2.2 Implement caching for relative arrow calculations in `DistanceCache.java`.

## 3. Placeholder Expansion Integration

- [x] 3.1 Register `%virtha_arrow_xyz_<x>_<y>_<z>%` handler in `TrackerExpansion.java`.
- [x] 3.2 Register `%virtha_arrow_xz_<x>_<z>%` handler in `TrackerExpansion.java`.
- [x] 3.3 Register `%virtha_arrow_player_<name>%` handler in `TrackerExpansion.java`.

## 4. Verification & Testing

- [x] 4.1 Build project with `./mvnw clean package` or `mvn clean package` to verify compilation.
- [x] 4.2 Verify placeholders handle same-world vs different-world conditions and offline targets properly.
