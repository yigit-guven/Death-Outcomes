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

    # Whether a player's head should only ever drop once. Once dropped, subsequent deaths will not drop their head.
    # Persisted across server restarts per world.
    # Default: false
    dropPlayerHeadOnlyOnceEver = false

[tracking_compass]
    # Whether crafting a tracking compass by combining a compass and player head is enabled.
    # Default: true
    enablePlayerTrackingCompassRecipe = true

    # Whether placing a Player's Compass in a crafting grid dismantles/recovers the player's head back.
    # Default: false
    enablePlayerCompassDismantleRecipe = false
```

### Option Breakdown

| Option | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `dropPlayerHeadOnDeath` | `boolean` | `true` | Master toggle for player head drops on death. Set to `false` to disable head drops entirely. |
| `dropPlayerHeadOnlyWhenKilledByPlayer` | `boolean` | `true` | When `true`, only PvP deaths drop heads. When `false`, **any** death drops the player's head. |
| `dropPlayerHeadOnlyOnceEver` | `boolean` | `false` | When `true`, each player can only ever drop their head once across the lifetime of the world. Subsequent deaths will never yield another head for that player. |
| `enablePlayerTrackingCompassRecipe` | `boolean` | `true` | Master toggle for crafting a tracking compass by combining a Compass with a Player Head in a crafting grid. |
| `enablePlayerCompassDismantleRecipe` | `boolean` | `false` | When `true`, placing a Player's Compass into any crafting grid will dismantle it and return the original player's head. |

---

## Common Use Cases

### 1. Hardcore / Trophy PvP (Default)
```toml
[player_head_drops]
    dropPlayerHeadOnDeath = true
    dropPlayerHeadOnlyWhenKilledByPlayer = true
    dropPlayerHeadOnlyOnceEver = false

[tracking_compass]
    enablePlayerTrackingCompassRecipe = true
    enablePlayerCompassDismantleRecipe = false
```

### 2. Recyclable Tracking Compasses
```toml
[player_head_drops]
    dropPlayerHeadOnDeath = true
    dropPlayerHeadOnlyWhenKilledByPlayer = true
    dropPlayerHeadOnlyOnceEver = false

[tracking_compass]
    enablePlayerTrackingCompassRecipe = true
    enablePlayerCompassDismantleRecipe = true
```
> Allows players to retrieve their trophy player head back by placing the compass in a crafting grid.
