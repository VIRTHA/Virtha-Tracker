## Why

Server administrators and game designers need a way to track the distance between a player and specific locations (coordinates or other entities) to build complex systems, minigames, or quality-of-life features in Survival/Factions modes. PlaceholderAPI (PAPI) is the standard for text-replacement in Minecraft servers, making it the perfect vehicle for this functionality. This plugin will provide a suite of highly configurable and optimized placeholders for calculating 2D and 3D distances.

## What Changes

- Initialize a new Spigot/Paper Minecraft plugin named `Virtha-Tracker`.
- Implement PlaceholderAPI integration to parse and resolve `%virtha_distance_...%` placeholders.
- Provide distance placeholders for fixed coordinates (`xyz` and `xz`) and entities/players.
- Implement an aggressive, customizable caching system to prevent performance degradation when placeholders are requested frequently (e.g., in a fast-updating scoreboard).
- Create a `config.yml` file (in English) allowing admins to customize output formats, missing/offline states, cross-dimensional tracking, and cache expiration times.

## Capabilities

### New Capabilities
- `distance-calculation`: The core capability handling PlaceholderAPI expansions, parsing target locations, caching distance calculations, and formatting the output strings based on `config.yml`.

### Modified Capabilities
- None

## Impact

- Introduces a new Spigot plugin to the ecosystem.
- Adds dependencies on the Spigot API and PlaceholderAPI.
- Memory and CPU usage will be negligible due to the caching implementation, but proper cache invalidation/expiration is critical.
