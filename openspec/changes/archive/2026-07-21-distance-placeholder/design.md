## Context

This project creates a new Spigot/Paper Minecraft plugin named `Virtha-Tracker`. The core functionality is to provide PlaceholderAPI extensions that calculate the distance from a player to a specific coordinate or entity. Because placeholders can be parsed frequently (e.g., via scoreboards that update every tick), performance is a critical concern.

## Goals / Non-Goals

**Goals:**
- Provide a set of placeholders for 2D and 3D distance calculations.
- Support cross-dimensional handling (with configurable strings when a player is in a different world).
- Implement an aggressive, customizable caching system to ensure optimal performance.
- Allow full customization of strings and formats via `config.yml`.

**Non-Goals:**
- Tracking entities that are not loaded or in other dimensions (distance is meaningless).
- Adding GUI menus, items, or commands beyond basic reload commands.

## Decisions

- **Caching Strategy:** We will use a `Map<String, CacheEntry>` where the key is the exact placeholder string requested (e.g., `%virtha_distance_xyz_10_60_10%` -> `xyz_10_60_10`). The `CacheEntry` will store the computed string and a timestamp. If the time elapsed since the timestamp is less than `cache_time_ms` (from `config.yml`), we return the cached string.
- **Cache Invalidation:** To prevent memory leaks from thousands of unique arbitrary coordinate requests, the cache will be periodically cleaned of expired entries, or we can use Guava's `CacheBuilder` with `expireAfterWrite` if we shade Guava (which is already included in Spigot API).
- **Configuration Structure:** The `config.yml` will be entirely in English as requested, with clear sections for `format` (messages) and `optimization` (cache settings).

## Risks / Trade-offs

- **[Risk] Heavy Player Load:** If a server has 500 players, and each has a scoreboard requesting 5 different distances 20 times a second, that is 50,000 requests per second.
  - **Mitigation:** The caching layer ensures that `Location.distance()` is only called a maximum of `1000 / cache_time_ms` times per second per unique request. If cache time is 500ms, it's called max 2 times per second. Reading from a cached Map in memory is O(1) and extremely fast, mitigating any TPS drops.
