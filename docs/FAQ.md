# Frequently Asked Questions (FAQ)

### Does this mod need to be installed on both the client and the server?
**No.** Death Outcomes is 100% server-side. It can be installed exclusively on the server, and vanilla or modded Minecraft clients can join and interact with dropped player heads, tracking compasses, and crafting recipes without needing any client-side mod or resource pack.

---

### Do dropped heads keep the player's custom skin?
**Yes.** Dropped heads store the player's profile data (UUID, skin texture, and outer hat layer) using Minecraft's Data Component system, ensuring the head renders with the victim's exact skin when placed or held in inventory.

---

### How does the Player's Compass work?
When crafted by combining a **Compass** and a **Player Head**, the resulting compass is bound to the target player:
* **Online Tracking**: The compass needle points continuously to the tracked player's real-time position.
* **Offline Tracking**: If the target player disconnects, the compass retains their last-known position and dimension.
* **Cross-Dimension**: If the tracked player is in another dimension (e.g., the Nether while you are in the Overworld), the compass needle will spin randomly until both players share the same dimension.

---

### What counts as an "indirect" player kill?
An indirect kill includes:
* Projectiles fired by another player (arrows, spectral arrows, thrown tridents, wind charges, fireworks).
* Potions / lingering effects thrown or placed by another player (Harming, Poison, etc.).
* Combat situations where another player is credited as the true damage source.

---

### What happens if a player dies to fall damage while fighting someone?
If Minecraft's combat tracker credits the attacking player as the cause of the death (e.g., knocked off a cliff within the combat tag window), the death source retains the attacking player and the head will drop.

---

### Can players retrieve their player head back from a Player's Compass?
Yes, if the server configuration has `enablePlayerCompassDismantleRecipe = true`. When enabled, placing a Player's Compass into any crafting grid will dismantle it and return the original player head.

---

### How do I edit configs on a server without restarting?
Server configurations in `serverconfig/death_outcomes-server.toml` are loaded on world/server launch. If you modify settings, reloading world data or restarting the server ensures the updated settings take effect immediately.
