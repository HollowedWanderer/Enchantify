advancement revoke @s only enchantify:has_book/inventory24

data modify storage enchantify:item StoredEnchantments set from entity @s Inventory[{Slot:33b}].tag.StoredEnchantments

function enchantify:replace_book/common

execute unless score enchantCount enchantify matches 0 run item modify entity @s inventory.24 enchantify:remove_from_book
execute if score enchantCount enchantify matches 0 run item replace entity @s inventory.24 with minecraft:book