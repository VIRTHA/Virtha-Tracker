# Virtha-Tracker

**Virtha-Tracker** is a highly-optimized, lightweight Minecraft plugin (Spigot/Paper) that provides custom [PlaceholderAPI](https://github.com/PlaceholderAPI/PlaceholderAPI) placeholders for calculating distances between players and specific targets. It's built for minigames, Factions, and Survival servers to allow server administrators to track objectives easily through scoreboards or action bars.

## 🚀 Features

* **3D & 2D Distance Tracking**: Calculate the distance to a specific coordinate or another player.
* **Aggressive Caching**: To prevent server TPS drops when updating rapidly (e.g., in a scoreboard updating 20 times a second), all distance calculations are cached in memory for a configurable amount of time.
* **Cross-Dimensional Support**: Displays a customizable string if the target is in a different dimension.
* **Highly Configurable**: Change every message and tweak performance parameters directly in `config.yml`.

## 📦 Dependencies

* Minecraft 1.13+ (Tested up to 1.20+)
* [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) (Required to parse the placeholders)

## 🔧 Installation

1. Drop `VirthaTracker.jar` into your server's `/plugins/` folder.
2. Ensure you have `PlaceholderAPI` installed.
3. Restart your server.
4. Modify `plugins/VirthaTracker/config.yml` to your liking.
5. Use `/virtha reload` to apply configuration changes.

## 📝 Placeholders

Use these placeholders anywhere PlaceholderAPI is supported (holograms, scoreboards, chat, tab, etc.):

| Placeholder | Description |
|---|---|
| `%virtha_distance_xyz_<x>_<y>_<z>%` | Returns the **3D distance** between the player and the specific X, Y, Z coordinate. |
| `%virtha_distance_xz_<x>_<z>%` | Returns the **2D distance** between the player and the specific X, Z coordinate (ignoring height/Y). |
| `%virtha_distance_player_<name>%` | Returns the distance between the player and another online player. |

*Examples:*
* `%virtha_distance_xyz_100_64_200%` 
* `%virtha_distance_xz_100_200%`
* `%virtha_distance_player_Notch%`

## ⚙️ Configuration (`config.yml`)

The configuration file allows you to customize the output formats and optimize performance:

```yaml
format:
  # What to show when the target (player or coordinate) is in a different world?
  different_world: "N/A"
  
  # What to show if the tracked player is offline?
  player_offline: "Offline"
  
  # The format of the distance output. %distance% will be replaced by the calculated number.
  distance_text: "%distance%m"
  
  # Whether to round the distance to the nearest whole number (true) or leave decimal points (false)
  round_distance: true

optimization:
  # Cache time in milliseconds.
  # 500ms means the distance is recalculated at most 2 times per second for the same placeholder.
  # Increase this number if you experience TPS drops with hundreds of players.
  cache_time_ms: 500
```

## 💻 Commands & Permissions

* `/virtha reload` - Reloads `config.yml` directly from disk.
  * **Permission**: `virthatracker.admin`
