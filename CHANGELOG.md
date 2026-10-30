# Changelog

All notable changes to this project will be documented in this file.

## [1.0.1] - 2026-10-30

### Added
- 

## [1.0.0] - 2026-10-30

### Added
- Player head drop mechanic on death with the victim's player skin profile.
- Server configuration options:
  - `dropPlayerHeadOnDeath`: Toggle whether players drop their head upon death (default: `true`).
  - `dropPlayerHeadOnlyWhenKilledByPlayer`: Restrict player head drops to deaths caused directly or indirectly by another player (default: `true`).
  - `dropPlayerHeadOnlyOnceEver`: Restrict each player's head to only ever drop once per world (default: `false`).
- Server-side Player Tracking Compass:
  - Crafting recipe combining a Compass and any Player Head in any 2x2 or 3x3 crafting grid.
  - Grants the compass an enchanted glint, Epic (purple) rarity, and a custom dark purple display name (`<Player>'s Compass`).
  - Added server config option `enablePlayerTrackingCompassRecipe` (default: `true`) to toggle crafting.
  - Added server config option `enablePlayerCompassDismantleRecipe` (default: `false`) to recover the original player's head by placing the compass in a crafting grid.
  - Dynamically points to the tracked player's real-time position, or their last known position when offline (persisted across restarts).
