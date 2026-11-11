# Clue Teleport Helper

A RuneLite plugin that lists the best teleports for your current clue scroll step and highlights the teleport items you need.

## Features

- **Teleport panel**: the closest teleports to the clue (5 by default, 1 to 10 in settings), each with its item or method and the tile distance.
- **Hand-picked routes**: islands, rune altar interiors, dungeons, Prifddinas, Zanaris and other awkward spots get a suggested route and a short note, instead of a plain distance sort.
- **Kill-for-key medium clues**: shows where to kill for the key, then switches to the crate or drawer once the key is in your inventory.
- **Kill-a-monster cryptic clues**: names the monster and where to find it (for example "Kill a Bloodveld: Slayer Tower or Catacombs of Kourend").
- **Sherlock and Charlie challenges**: tells you where each skill challenge can be done, then suggests the trip back to Sherlock (Camelot or fairy ring BLR) or to Charlie in Varrock.
- **Three-step cryptic clues**: one teleport per step that is still left.
- **Viggora (master cryptic)**: works out which of his three spots he is at.
- **Same-spot teleports combined**: for example "Barbarian Assault (Games Neck / Minigame)" takes one line, not two.
- **Wilderness warning**: Wilderness teleports are left out by default. Turn that off and they show in red with "(Wildy)". Ferox Enclave counts as safe.
- **Item highlight**: suggested teleport items are outlined in your inventory, bank and worn equipment. Law runes light up only when a spell is suggested. A dramen or lunar staff lights up when a fairy ring is suggested.
- **Owned teleports (optional)**: hide or grey out teleports you cannot use, based on what is in your inventory, worn items and bank, your spellbook, and the quests that unlock fairy rings, spirit trees, quetzals and the Prifddinas tab.

Distances are straight-line tile counts ("as the crow flies"). They ignore rivers, walls and shortcuts, which is why some places get a hand-picked route.

## Settings

| Section | Setting | Default |
|---|---|---|
| Panel | Teleports shown | 5 |
| Panel | Show tile distance | On |
| Panel | Colour-code distances (20 tiles or less, 50 or less, further) | On |
| Panel | Show item or method | On |
| Panel | Combine same destination | On |
| Panel | Panel width | 280 |
| Teleport choices | Avoid Wilderness teleports | On |
| Teleport choices | Owned teleports (show all, only mine, grey out the rest) | Show all |
| Teleport choices | Include fairy rings, spirit trees, minigame teleports, levers | All on |
| Item highlight | Highlight in inventory, bank, worn items | All on |
| Item highlight | Outline colour, fill colour, fill opacity, outline width | Pink theme |
| Colours | Title, destination, item or method, distance, notes, Wilderness warning | Pink theme |

The "Only teleports I have" option needs to see your bank once. Open it once and the plugin remembers which teleport items are in it for that account. Only teleport-related item IDs are stored, in RuneLite's own per-account settings.

## Requirements

Requires the official Clue Scrolls plugin to be enabled.
