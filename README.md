<div align="center">

<img src="https://raw.githubusercontent.com/yigit-guven/Death-Outcomes/main/docs/assets/banner.png" alt="Death Outcomes" />

<br />

[![CurseForge](https://img.shields.io/badge/CurseForge-Available-F16436?style=for-the-badge&logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/death-outcomes)
[![Modrinth](https://img.shields.io/badge/Modrinth-Available-00AF5C?style=for-the-badge&logo=modrinth)](https://modrinth.com/mod/death-outcomes)
[![Discord](https://img.shields.io/discord/1440563800930652235?style=for-the-badge&logo=discord&label=Discord&color=5865F2)](https://discord.gg/aPk7Qs5d4H)
[![GitHub](https://img.shields.io/badge/GitHub-Repository-black?style=for-the-badge&logo=github)](https://github.com/yigit-guven/Death-Outcomes)
[![Wiki](https://img.shields.io/badge/Documentation-Wiki-blue?style=for-the-badge&logo=gitbook)](https://github.com/yigit-guven/Death-Outcomes/wiki)

<br />

**Trophies, tracking compasses, and meaningful consequences for player deaths in Minecraft.**

</div>

---

## Overview

**Death Outcomes** is a lightweight, server-friendly Minecraft mod that introduces meaningful rewards, trophies, and tracking mechanics when players die:

* **Player Head Drops** - Slain players drop authentic player heads preserving their exact skins, usernames, and 3D hat layers.
* **Smart PvP Validation** - Detects both direct melee combat and indirect projectile/potion kills while filtering out accidental environmental deaths and self-damage.
* **Player's Compass** - Craft an enchanted compass bound to any player head that tracks the target player live across the world, even retaining their coordinates when offline.
* **100% Server-Side** - Only required on the server. Players can join using standard vanilla Minecraft clients without installing any client-side mod or resource pack.

---

## Features

### 1. Player Head Trophies

<div align="center">
<table>
  <tr>
    <td align="center"><img src="https://raw.githubusercontent.com/yigit-guven/Death-Outcomes/main/docs/assets/player_head_3d.png" width="96" height="102" /><br /><b>Player Head</b></td>
    <td align="center"><img src="https://raw.githubusercontent.com/yigit-guven/Death-Outcomes/main/docs/assets/skeleton_head_3d.png" width="96" height="102" /><br /><b>Skeleton Skull</b></td>
    <td align="center"><img src="https://raw.githubusercontent.com/yigit-guven/Death-Outcomes/main/docs/assets/pumpkin_head_3d.png" width="96" height="102" /><br /><b>Carved Pumpkin</b></td>
  </tr>
</table>
</div>

* **Authentic Profile Resolution**: Heads store the player's profile data via Minecraft's Data Component system, ensuring the head renders identically to the fallen player's actual skin.
* **Combat Validation**:
  * **Direct Kills**: Swords, axes, fists, and direct melee weapon strikes.
  * **Indirect Kills**: Bows, crossbows, tridents, lingering potions, splash potions, thrown projectiles, fireworks, or indirect magical damage originating from another player.
* **Suicide and Environment Protection**: Self-inflicted deaths (such as falling, drowning, or harming yourself with an arrow or firework) will not drop heads when PvP restriction is active.
* **One-Time Head Drop Option**: Can be configured so each player's head can only drop once ever in the world's lifetime, turning heads into ultra-rare bounty trophies.

---

### 2. Player's Compass (Tracking Compass)

<div align="center">
  <img src="https://raw.githubusercontent.com/yigit-guven/Death-Outcomes/main/docs/assets/enchanted_compass.gif" width="96" height="96" />
  <br />
  <b><i>Player's Compass</i></b>
  <br />
  <sub><i>Epic Rarity - Enchanted Glint - Real-time Target Tracking</i></sub>
</div>

Combine a standard **Compass** with any **Player Head** in any 2x2 or 3x3 crafting grid to craft a **Player's Compass**:

<div align="center">
  <img src="https://raw.githubusercontent.com/yigit-guven/Death-Outcomes/main/docs/assets/crafting_player_compass.png" width="416" height="197" />
  <p><i>Crafting Recipe: Compass + Player Head = Player's Compass</i></p>
</div>

* **Live Position Tracking**: Points continuously toward the target player's current location in real-time.
* **Offline Location Persistence**: If the tracked player disconnects from the server, the compass seamlessly retains their last-known position and dimension.
* **Cross-Dimension Handling**: If the tracked player is in a different dimension, the needle spins randomly until they return or you enter the same dimension.
* **Head Recovery / Dismantling (Optional)**: When enabled in server settings, placing a Player's Compass alone into any crafting grid dismantles it and returns the original player head:

<div align="center">
  <img src="https://raw.githubusercontent.com/yigit-guven/Death-Outcomes/main/docs/assets/crafting_dismantle.png" width="416" height="197" />
  <p><i>Dismantle Recipe: Player's Compass = Player Head</i></p>
</div>

---

## Configuration

Settings are saved per world at:
```text
<world_directory>/serverconfig/death_outcomes-server.toml
```

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

---

## Links and Community

* **Documentation & Wiki**: [https://github.com/yigit-guven/Death-Outcomes/wiki](https://github.com/yigit-guven/Death-Outcomes/wiki)
* **Discord Community**: [https://discord.gg/aPk7Qs5d4H](https://discord.gg/aPk7Qs5d4H)
* **Issue Tracker**: [https://github.com/yigit-guven/Death-Outcomes/issues](https://github.com/yigit-guven/Death-Outcomes/issues)

---

## License

This project is licensed under the MIT License.
