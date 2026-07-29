# directional-arrows Specification

## Purpose
TBD - created by archiving change add-directional-arrow-placeholders. Update Purpose after archive.
## Requirements
### Requirement: Relative Directional Arrow Calculation
The system SHALL calculate relative 2D directional arrows pointing toward target coordinates or target players based on the requesting player's horizontal viewing angle (yaw).

#### Scenario: Arrow pointing straight ahead
- **WHEN** the target is within -22.5° to +22.5° relative to the player's yaw
- **THEN** the system SHALL return the `front` arrow symbol (`↑` by default)

#### Scenario: Arrow pointing to the right
- **WHEN** the target is within +67.5° to +112.5° relative to the player's yaw
- **THEN** the system SHALL return the `right` arrow symbol (`→` by default)

#### Scenario: Arrow pointing behind
- **WHEN** the target is within +157.5° to +202.5° (or -157.5° to -202.5°) relative to the player's yaw
- **THEN** the system SHALL return the `back` arrow symbol (`↓` by default)

### Requirement: Custom Arrow Symbols Configuration
The system SHALL support configuring custom strings for all 8 relative directional octants in `config.yml` under the `arrows` section (`front`, `front_right`, `right`, `back_right`, `back`, `back_left`, `left`, `front_left`).

#### Scenario: Custom arrow configuration
- **WHEN** `config.yml` defines `arrows.front: "^"`
- **THEN** placeholders evaluating to front SHALL return `"^"`

### Requirement: Cross-World and Offline Fallback Handling
The system SHALL return appropriate fallback strings when distance or direction calculations cannot be performed due to dimensional boundaries or player availability.

#### Scenario: Target in different world
- **WHEN** a player requests an arrow placeholder for a target in a different world
- **THEN** the system SHALL return the configured `format.different_world` string (`"N/A"` by default)

#### Scenario: Target player is offline
- **WHEN** a player requests `%virtha_arrow_player_<name>%` for an offline or non-existent player
- **THEN** the system SHALL return the configured `format.player_offline` string (`"Offline"` by default)

