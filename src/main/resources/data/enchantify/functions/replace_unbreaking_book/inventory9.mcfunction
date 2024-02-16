advancement revoke @s only enchantify:has_unbreaking_book/inventory9

data modify storage enchantify:item StoredEnchantments set from entity @s Inventory[{Slot:18b}].tag.StoredEnchantments

function enchantify:replace_unbreaking_book/common

execute unless score enchantCount enchantify matches 0 run item modify entity @s inventory.9 enchantify:remove_from_book
execute if score enchantCount enchantify matches 0 run item replace entity @s inventory.9 with minecraft:book