data remove storage enchantify:item StoredEnchantments[{id:"minecraft:mending"}]

scoreboard players set enchantCount enchantify 0
execute store result score enchantCount enchantify run data get storage enchantify:item StoredEnchantments