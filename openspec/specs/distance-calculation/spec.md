# Distance Calculation Placeholders

## Purpose
This capability provides PlaceholderAPI integration to calculate distances between players and specific targets (coordinates or other players).

## Requirements

### Requirement: Distance Calculation Placeholders
The system SHALL provide PlaceholderAPI placeholders that calculate the distance from the player viewing the placeholder to a specific target.

#### Scenario: 3D Coordinate tracking
- **WHEN** a player requests the placeholder `%radarx_distance_xyz_100_60_200%`
- **THEN** the system returns the 3D distance between the player and coordinates X:100 Y:60 Z:200, formatted according to `config.yml`.

#### Scenario: 2D Coordinate tracking
- **WHEN** a player requests the placeholder `%radarx_distance_xz_100_200%`
- **THEN** the system returns the 2D distance between the player and coordinates X:100 Z:200 (ignoring Y), formatted according to `config.yml`.

#### Scenario: Player tracking
- **WHEN** a player requests the placeholder `%radarx_distance_player_Notch%`
- **THEN** the system returns the distance between the player and the online player named "Notch".

### Requirement: Cross-world handling
The system SHALL handle distance requests when the target is in a different world than the player.

#### Scenario: Target in different world
- **WHEN** the player and the target (coordinate or player) are in different dimensions
- **THEN** the system returns the configured `different_world` message from `config.yml`.

### Requirement: Caching and Performance
The system SHALL cache placeholder results to avoid running distance calculations on every request tick.

#### Scenario: Requesting placeholder within cache time
- **WHEN** a player requests a placeholder that was calculated 100ms ago, and `cache_time_ms` is 500ms
- **THEN** the system returns the cached result without recalculating distance.

### Requirement: Configuration
The system SHALL read localization and settings from `config.yml`.

#### Scenario: Reloading configuration
- **WHEN** an admin runs `/radarx reload`
- **THEN** the system reloads `config.yml` into memory and updates placeholder formats.
