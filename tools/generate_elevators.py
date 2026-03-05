import os
import json

colors = [
    "white", "orange", "magenta", "light_blue", "yellow", "lime", 
    "pink", "gray", "light_gray", "cyan", "purple", "blue", 
    "brown", "green", "red", "black"
]

base_dir = r"c:\Users\Walter\Documents\GitHub\ZCI-Mod\src\main"
assets_mnt = os.path.join(base_dir, "resources", "assets", "zelashsclutchitems")
data_mnt = os.path.join(base_dir, "resources", "data")

blocks_file = os.path.join(base_dir, "java", "com", "zelash", "zelashsclutchitems", "block", "ModBlocks.java")
items_file = os.path.join(base_dir, "java", "com", "zelash", "zelashsclutchitems", "item", "ModItems.java")
tabs_file = os.path.join(base_dir, "java", "com", "zelash", "zelashsclutchitems", "item", "ModCreativeTabs.java")
lang_file = os.path.join(assets_mnt, "lang", "en_us.json")

# 1. Update ModBlocks.java
with open(blocks_file, "r") as f:
    blocks_code = f.read()

block_decls = []
for c in colors:
    upper = c.upper()
    block_decls.append(f'    public static final DeferredBlock<Block> {upper}_ELEVATOR = BLOCKS.registerBlock("{c}_elevator", ElevatorBlock::new,\n            BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOL));')

blocks_replacement = "    public static final DeferredBlock<Block> ELEVATOR = BLOCKS.registerBlock(\"elevator\", ElevatorBlock::new,\n            BlockBehaviour.Properties.of()\n                    .strength(0.8F)\n                    .sound(SoundType.WOOL));\n\n" + "\n\n".join(block_decls)

blocks_code = blocks_code.replace('    public static final DeferredBlock<Block> ELEVATOR = BLOCKS.registerBlock("elevator", ElevatorBlock::new,\n            BlockBehaviour.Properties.of()\n                    .strength(0.8F)\n                    .sound(SoundType.WOOL));', blocks_replacement)

with open(blocks_file, "w") as f:
    f.write(blocks_code)

# 2. Update ModItems.java
with open(items_file, "r") as f:
    items_code = f.read()

item_decls = []
for c in colors:
    upper = c.upper()
    item_decls.append(f'    public static final DeferredItem<BlockItem> {upper}_ELEVATOR = ITEMS.registerSimpleBlockItem("{c}_elevator", ModBlocks.{upper}_ELEVATOR);')

items_replacement = '    public static final DeferredItem<BlockItem> ELEVATOR = ITEMS.registerSimpleBlockItem("elevator", ModBlocks.ELEVATOR);\n' + "\n".join(item_decls)

items_code = items_code.replace('    public static final DeferredItem<BlockItem> ELEVATOR = ITEMS.registerSimpleBlockItem("elevator", ModBlocks.ELEVATOR);', items_replacement)

with open(items_file, "w") as f:
    f.write(items_code)

# 3. Update ModCreativeTabs.java
with open(tabs_file, "r") as f:
    tabs_code = f.read()

tab_inserts = []
for c in colors:
    upper = c.upper()
    tab_inserts.append(f'                        output.accept(ModItems.{upper}_ELEVATOR.get());')

tabs_replacement = '                        output.accept(ModItems.ELEVATOR.get());\n' + "\n".join(tab_inserts)

tabs_code = tabs_code.replace('                        output.accept(ModItems.ELEVATOR.get());', tabs_replacement)

with open(tabs_file, "w") as f:
    f.write(tabs_code)

# 4. Update en_us.json
with open(lang_file, "r", encoding="utf-8") as f:
    lang_data = json.load(f)

for c in colors:
    c_name = c.replace("_", " ").title()
    lang_data[f"block.zelashsclutchitems.{c}_elevator"] = f"{c_name} Elevator"

with open(lang_file, "w", encoding="utf-8") as f:
    json.dump(lang_data, f, indent=2)

# 5. Generate JSON files
def create_json(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w", encoding="utf-8") as f:
        json.dump(content, f, indent=2)

for c in colors:
    # Blockstate
    bs_path = os.path.join(assets_mnt, "blockstates", f"{c}_elevator.json")
    create_json(bs_path, {"variants": {"": {"model": f"zelashsclutchitems:block/{c}_elevator"}}})
    
    # Block Model
    bm_path = os.path.join(assets_mnt, "models", "block", f"{c}_elevator.json")
    create_json(bm_path, {"parent": "minecraft:block/cube_all", "textures": {"all": f"zelashsclutchitems:block/{c}_elevator"}})
    
    # Item Model
    im_path = os.path.join(assets_mnt, "models", "item", f"{c}_elevator.json")
    create_json(im_path, {"parent": f"zelashsclutchitems:block/{c}_elevator"})
    
    # Recipe
    recipe_path = os.path.join(data_mnt, "zelashsclutchitems", "recipe", f"{c}_elevator.json")
    create_json(recipe_path, {
        "type": "minecraft:crafting_shaped",
        "pattern": [
            "###",
            "#O#",
            "###"
        ],
        "key": {
            "#": {
                "item": f"minecraft:{c}_wool"
            },
            "O": {
                "item": "minecraft:ender_pearl"
            }
        },
        "result": {
            "id": f"zelashsclutchitems:{c}_elevator",
            "count": 1
        }
    })
    
    # Loot Table
    lt_path = os.path.join(data_mnt, "zelashsclutchitems", "loot_table", "blocks", f"{c}_elevator.json")
    create_json(lt_path, {
      "type": "minecraft:block",
      "pools": [
        {
          "bonus_rolls": 0.0,
          "entries": [
            {
              "type": "minecraft:item",
              "name": f"zelashsclutchitems:{c}_elevator"
            }
          ],
          "rolls": 1.0,
          "conditions": [
            {
              "condition": "minecraft:survives_explosion"
            }
          ]
        }
      ]
    })

# 6. Update tags
minecraft_tags_dir = os.path.join(data_mnt, "minecraft", "tags", "block")

pickaxe_tag_path = os.path.join(minecraft_tags_dir, "mineable", "pickaxe.json")
needs_stone_tool_path = os.path.join(minecraft_tags_dir, "needs_stone_tool.json")

for tag_path in [pickaxe_tag_path, needs_stone_tool_path]:
    if os.path.exists(tag_path):
        with open(tag_path, "r", encoding="utf-8") as f:
            tag_data = json.load(f)
        
        for c in colors:
            block_id = f"zelashsclutchitems:{c}_elevator"
            if block_id not in tag_data.get("values", []):
                tag_data.setdefault("values", []).append(block_id)
                
        with open(tag_path, "w", encoding="utf-8") as f:
            json.dump(tag_data, f, indent=4)

print("Generation and updates successfully completed.")
