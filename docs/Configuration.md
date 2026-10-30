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
```

### Option Breakdown

| Option | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `dropPlayerHeadOnDeath` | `boolean` | `true` | Master toggle for player head drops on death. Set to `false` to disable head drops entirely. |
| `dropPlayerHeadOnlyWhenKilledByPlayer` | `boolean` | `true` | When `true`, only PvP deaths (direct weapon hits, arrows, potions, etc.) drop heads. When `false`, **any** death (mobs, fall damage, lava, etc.) drops the player's head. |

---

## Common Use Cases

### 1. Hardcore / Trophy PvP (Default)
```toml
[player_head_drops]
    dropPlayerHeadOnDeath = true
    dropPlayerHeadOnlyWhenKilledByPlayer = true
```
> Players only earn heads by defeating other players in combat.

### 2. Fun / Casual PvE & SMP
```toml
[player_head_drops]
    dropPlayerHeadOnDeath = true
    dropPlayerHeadOnlyWhenKilledByPlayer = false
```
> Every single death drops a head, regardless of cause (creepers, falling, lava, etc.).

### 3. Disabled Head Drops
```toml
[player_head_drops]
    dropPlayerHeadOnDeath = false
```
> Disables the player head drop mechanic completely.

