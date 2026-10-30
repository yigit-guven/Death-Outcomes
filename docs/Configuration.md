# Configuration

Death Outcomes uses server configuration files saved per world/save.

---

## File Location

On dedicated servers or singleplayer worlds, the configuration file is located at:

```text
<world_directory>/serverconfig/death_outcomes-server.toml
```

> **Note**: In singleplayer or LAN worlds, `<world_directory>` is inside your `.minecraft/saves/<world_name>/`. On dedicated servers, it is inside your root server's world folder (typically `world/serverconfig/`).

---

## Configuration Options

Below is the structure of the `death_outcomes-server.toml` file:

```toml
[player_head_drops]
    # Whether players should drop their head upon death.
    # Default: true
    dropPlayerHeadOnDeath = true

    # Whether player heads should only drop if the death was caused directly or indirectly by another player.
    # If false (and dropPlayerHeadOnDeath is true), heads will drop on any death.
    # Default: true
    dropPlayerHeadOnlyWhenKilledByPlayer = true

[tracking_compass]
    # Whether crafting a tracking compass by combining a compass and player head is enabled.
    # Default: true
    enablePlayerTrackingCompassRecipe = true
```

### Option Breakdown

| Option | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `dropPlayerHeadOnDeath` | `boolean` | `true` | Master toggle for player head drops on death. Set to `false` to disable head drops entirely. |
| `dropPlayerHeadOnlyWhenKilledByPlayer` | `boolean` | `true` | When `true`, only PvP deaths drop heads. When `false`, **any** death drops the player's head. |
| `enablePlayerTrackingCompassRecipe` | `boolean` | `true` | Master toggle for crafting a tracking compass by combining a Compass with a Player Head in a crafting grid. |

---

## Common Use Cases

### 1. Hardcore / Trophy PvP (Default)
```toml
[player_head_drops]
    dropPlayerHeadOnDeath = true
    dropPlayerHeadOnlyWhenKilledByPlayer = true

[tracking_compass]
    enablePlayerTrackingCompassRecipe = true
```
> Players only earn heads by defeating other players in combat, and can craft Player Compasses to track them.

### 2. Casual PvE / SMP (No Tracking)
```toml
[player_head_drops]
    dropPlayerHeadOnDeath = true
    dropPlayerHeadOnlyWhenKilledByPlayer = false

[tracking_compass]
    enablePlayerTrackingCompassRecipe = false
```
> Heads drop on any death, but player tracking compasses cannot be crafted.
