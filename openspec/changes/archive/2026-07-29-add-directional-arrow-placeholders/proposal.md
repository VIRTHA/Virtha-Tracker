## Why

Server administrators using scoreboards, holograms, and action bars want visual direction indicators (radar-style arrows) pointing toward specific targets or players. Adding directional arrow placeholders allows players to easily navigate toward objectives based on their current viewing angle without needing separate compass or map plugins.

## What Changes

- Add new PlaceholderAPI placeholders for directional arrows relative to player yaw:
  - `%virtha_arrow_xyz_<x>_<y>_<z>%` - Calculates 2D directional arrow toward target X, Y, Z coordinates relative to viewer yaw.
  - `%virtha_arrow_xz_<x>_<z>%` - Calculates 2D directional arrow toward target X, Z coordinates relative to viewer yaw.
  - `%virtha_arrow_player_<name>%` - Calculates 2D directional arrow toward target online player relative to viewer yaw.
- Add configuration section `arrows` in `config.yml` to customize arrow symbols for 8 relative directional octants (front, front_right, right, back_right, back, back_left, left, front_left).
- Integrate arrow calculations with `ConfigManager` and cache layer (`DistanceCache` / arrow cache).
- Handle edge cases: return `format.different_world` when in different dimensions, `format.player_offline` when target player is offline, and a configurable fallback or front arrow when standing at exact target coordinates.

## Capabilities

### New Capabilities
- `directional-arrows`: Provides 2D relative directional arrow placeholders (%virtha_arrow_xyz%, %virtha_arrow_xz%, %virtha_arrow_player%) based on player yaw angle and configurable octant symbols.

### Modified Capabilities

## Impact

- `com.virtha.tracker.ConfigManager`: Expanded to load customizable arrow symbols from `config.yml`.
- `com.virtha.tracker.TrackerExpansion`: Registered new placeholder patterns (`arrow_xyz_`, `arrow_xz_`, `arrow_player_`).
- `com.virtha.tracker.DistanceCache` / Direction calculator: Added relative angle evaluation logic (atan2 - player yaw) mapped to 8 octants.
- `config.yml`: New default YAML section `arrows`.
