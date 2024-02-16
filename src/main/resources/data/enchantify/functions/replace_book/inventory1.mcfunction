advancement revoke @s only enchantify:has_book/inventory1

data modify storage enchantify:item StoredEnchantments set from entity @s Inventory[{Slot:10b}].tag.StoredEnchantments

function enchantify:replace_book/common

execute unless score enchantCount enchantify matches 0 run item modify entity @s inventory.1 enchantify:remove_from_book
execute if score enchantCount enchantify matches 0 run item replace entity @s inventory.1 with minecraft:book