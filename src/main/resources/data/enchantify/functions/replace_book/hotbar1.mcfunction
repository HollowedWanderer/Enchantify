advancement revoke @s only enchantify:has_book/hotbar1

data modify storage enchantify:item StoredEnchantments set from entity @s Inventory[{Slot:1b}].tag.StoredEnchantments

function enchantify:replace_book/common

execute unless score enchantCount enchantify matches 0 run item modify entity @s hotbar.1 enchantify:remove_from_book
execute if score enchantCount enchantify matches 0 run item replace entity @s hotbar.1 with minecraft:book