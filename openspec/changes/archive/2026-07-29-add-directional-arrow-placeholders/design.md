## Context

Virtha-Tracker currently provides 3D and 2D distance calculation placeholders using `TrackerExpansion`, cached in memory via `DistanceCache`, and formatted through `ConfigManager`. Server administrators require radar-style directional indicator arrows relative to player horizontal view rotation (yaw) for scoreboards, holograms, and action bars.

## Goals / Non-Goals

**Goals:**
- Implement 2D relative angle calculation (`atan2` - `player.getYaw()`) mapped to 8 directional octants.
- Expose new placeholders: `%virtha_arrow_xyz_<x>_<y>_<z>%`, `%virtha_arrow_xz_<x>_<z>%`, `%virtha_arrow_player_<name>%`.
- Add configurable arrow character map in `config.yml` under `arrows` section.
- Cache arrow results using the existing TTL caching pattern to preserve high TPS under heavy scoreboard updates.

**Non-Goals:**
- 3D pitch/elevation arrow calculation (e.g. up/down pitch arrows).
- Absolute cardinal direction (North/South/East/West) indicators.

## Decisions

### 1. Relative Yaw Angle & Octant Mapping
- **Choice**: Use relative horizontal angle $\theta_{\text{rel}} = (\text{targetAngle} - \text{playerYaw}) \pmod{360}$.
- **Rationale**: Provides intuitive radar-style guidance relative to player's current gaze.
- **Octant Breakdown**:
  - `[337.5°, 22.5°)` -> `front` (`↑`)
  - `[22.5°, 67.5°)` -> `front_right` (`↗`)
  - `[67.5°, 112.5°)` -> `right` (`→`)
  - `[112.5°, 157.5°)` -> `back_right` (`↘`)
  - `[157.5°, 202.5°)` -> `back` (`↓`)
  - `[202.5°, 247.5°)` -> `back_left` (`↙`)
  - `[247.5°, 292.5°)` -> `left` (`←`)
  - `[292.5°, 337.5°)` -> `front_left` (`↖`)

### 2. Config & Arrow Data Structure in `ConfigManager`
- Store loaded octant strings in an `EnumMap<DirectionOctant, String>` or helper class within `ConfigManager`.
- Provide default values matching unicode arrows (`↑`, `↗`, `→`, `↘`, `↓`, `↙`, `←`, `↖`).

### 3. Caching Strategy
- Extend or utilize `DistanceCache` TTL mechanism keying by player UUID, target, and placeholder type.

## Risks / Trade-offs

- **[Rapid Yaw Changes]** → Frequent head turning will hit cache if `cache_time_ms` is too high (e.g. 500ms). Mitigation: admin can tune `cache_time_ms` in `config.yml` if high precision is required.
