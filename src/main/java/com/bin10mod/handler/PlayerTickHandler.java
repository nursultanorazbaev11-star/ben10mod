package com.bin10mod.handler;

import com.bin10mod.alien.AlienForm;
import com.bin10mod.init.ModItems;
import com.bin10mod.item.ItemOmnitrix;
import com.bin10mod.reference.Reference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerTickHandler {

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        EntityPlayer player = event.player;
        ItemStack omnitrix = findOmnitrix(player);
        if (omnitrix == null) {
            return;
        }
        NBTTagCompound tag = omnitrix.getTagCompound();
        if (tag == null) {
            return;
        }

        if (tag.getBoolean(Reference.NBT_TRANSFORMED)) {
            AlienForm form = AlienForm.byName(tag.getString(Reference.NBT_ALIEN));
            form.applyEffects(player);

            int timeLeft = tag.getInteger(Reference.NBT_TRANSFORM_TIME) - 1;
            if (timeLeft <= 0) {
                ItemOmnitrix.revert(player, tag);
            } else {
                tag.setInteger(Reference.NBT_TRANSFORM_TIME, timeLeft);
            }
        } else {
            int cooldown = tag.getInteger(Reference.NBT_COOLDOWN);
            if (cooldown > 0) {
                tag.setInteger(Reference.NBT_COOLDOWN, cooldown - 1);
            }
        }
    }

    private ItemStack findOmnitrix(EntityPlayer player) {
        if (player.inventory == null) {
            return null;
        }
        for (ItemStack stack : player.inventory.mainInventory) {
            if (stack != null && stack.getItem() == ModItems.omnitrix) {
                return stack;
            }
        }
        return null;
    }
}
