advancement revoke @s only enchantify:has_unbreaking_book/offhand

data modify storage enchantify:item StoredEnchantments set from entity @s Inventory[{Slot:-106b}].tag.StoredEnchantments

function enchantify:replace_unbreaking_book/common

execute unless score enchantCount enchantify matches 0 run item modify entity @s weapon.offhand enchantify:remove_from_book
execute if score enchantCount enchantify matches 0 run item replace entity @s weapon.offhand with minecraft:book