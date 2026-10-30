# Frequently Asked Questions (FAQ)

### Does this mod need to be installed on both the client and the server?
**No.** Death Outcomes is primarily a server-side mod. It can be installed exclusively on the server, and vanilla or modded clients can join and interact with the dropped player heads without needing the mod installed on their machines.

---

### Do dropped heads keep the player's custom skin?
**Yes.** Dropped heads store the player's profile data (UUID and skin textures), so the head renders with the victim's exact skin when placed or held in inventory.

---

### What counts as an "indirect" player kill?
An indirect kill includes:
- Projectiles fired by another player (arrows, spectral arrows, thrown tridents, wind charges).
- Potions / lingering effects thrown or placed by another player (Harming, Poison, etc.).
- Combat situations where another player is credited as the true damage source.

---

### What happens if a player dies to fall damage while fighting someone?
If Minecraft's combat tracker credits the attacking player as the cause of the death (e.g. knocked off a cliff within the combat tag window), the death source retains the attacking player and the head will drop.

---

### How do I edit configs on a server without restarting?
Server configurations in `serverconfig/` are typically loaded on world/server launch. If you modify `death_outcomes-server.toml`, reloading or restarting the server ensures the updated settings take effect immediately.

