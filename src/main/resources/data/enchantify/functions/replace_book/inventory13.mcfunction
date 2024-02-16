advancement revoke @s only enchantify:has_book/inventory13

data modify storage enchantify:item StoredEnchantments set from entity @s Inventory[{Slot:22b}].tag.StoredEnchantments

function enchantify:replace_book/common

execute unless score enchantCount enchantify matches 0 run item modify entity @s inventory.13 enchantify:remove_from_book
execute if score enchantCount enchantify matches 0 run item replace entity @s inventory.13 with minecraft:book