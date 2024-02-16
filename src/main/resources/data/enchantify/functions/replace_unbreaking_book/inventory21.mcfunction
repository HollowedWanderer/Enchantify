advancement revoke @s only enchantify:has_unbreaking_book/inventory21

data modify storage enchantify:item StoredEnchantments set from entity @s Inventory[{Slot:30b}].tag.StoredEnchantments

function enchantify:replace_unbreaking_book/common

execute unless score enchantCount enchantify matches 0 run item modify entity @s inventory.21 enchantify:remove_from_book
execute if score enchantCount enchantify matches 0 run item replace entity @s inventory.21 with minecraft:book