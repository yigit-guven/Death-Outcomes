# Features

Death Outcomes introduces customizable mechanics that trigger when players die.

---

## 1. Player Head Drops

Whenever a player dies, the mod can drop an authentic **Player Head** item representing the fallen player.

### Key Aspects:
- **Skin Profile Resolution**: The head uses Minecraft's Data Component system to preserve the victim's exact skin and username.
- **PvP Detection (Direct & Indirect)**:
  - **Direct Kills**: Melee combat, direct weapon hits.
  - **Indirect Kills**: Bows, crossbows, tridents, splash potions, thrown projectiles, or indirect magical damage originating from another player.
- **Suicide / Self-Damage Protection**: If PvP restriction is enabled, self-inflicted deaths (such as falling, drowning, or harming yourself with an arrow/firework) will **not** trigger a head drop.

---

## 2. Player Tracking Compass

Combine a **Compass** and any **Player Head** in any 2x2 or 3x3 crafting grid to craft a **Player's Compass** (can be toggled in the server configuration).

### Key Aspects:
- **Appearance**: Displays in legendary dark purple (`Epic` rarity), with an enchanted shimmer/glint, and named `<Player>'s Compass`.
- **Live Location Tracking**: Uses vanilla Lodestone tracking data components (`tracked: false`) to point directly to the tracked player in real-time.
- **Offline Location Persistence**: If the tracked player disconnects, the compass seamlessly retains their last known position and dimension (saved in world data across server restarts).
- **100% Server-Side**: Functions cleanly with vanilla clients without needing any client-side mod installation.

---

## 3. Server-Side Compatibility

- **Vanilla Client Friendly**: When installed on a dedicated server, players joining with vanilla Minecraft clients (or any client without Death Outcomes installed) will receive the drops and crafting capabilities correctly.
- **Multiplayer & SMP Ready**: Tailor the behavior to match your server's playstyle (e.g., hard-earned trophies in PvP or fun cosmetics in PvE).
