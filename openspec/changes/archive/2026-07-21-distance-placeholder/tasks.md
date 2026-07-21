## 1. Project Initialization

- [x] 1.1 Create basic Spigot plugin structure (`plugin.yml`, `Main.java`)
- [x] 1.2 Add Spigot API and PlaceholderAPI dependencies to `pom.xml` (or `build.gradle`)

## 2. Configuration Setup

- [x] 2.1 Create default `config.yml` with format and optimization sections
- [x] 2.2 Implement a `ConfigManager` class to load and parse settings from `config.yml`
- [x] 2.3 Register `/virtha reload` command to reload the configuration

## 3. Core Logic and Caching

- [x] 3.1 Implement a `DistanceCache` class to store computed distances with timestamps
- [x] 3.2 Implement a cache cleanup task (runs asynchronously or uses expiring cache map)

## 4. PlaceholderAPI Integration

- [x] 4.1 Create `TrackerExpansion` class extending `PlaceholderExpansion`
- [x] 4.2 Parse `xyz_<x>_<y>_<z>` strings and return 3D distances
- [x] 4.3 Parse `xz_<x>_<z>` strings and return 2D distances
- [x] 4.4 Parse `player_<name>` strings and return distances to online players
- [x] 4.5 Ensure cross-dimension distance requests return the proper string from `config.yml`
- [x] 4.6 Route all distance calculations through the `DistanceCache`

## 5. Verification

- [x] 5.1 Test placeholders on the server with varying cache times
- [x] 5.2 Validate that cross-world tracking works correctly
- [x] 5.3 Test `/virtha reload` and verify format updates apply properly
