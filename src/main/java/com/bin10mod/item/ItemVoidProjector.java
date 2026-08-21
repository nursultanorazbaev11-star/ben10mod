package com.bin10mod.item;

import com.bin10mod.reference.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

/**
 * A hand-held device that teleports the player to the Null Void dimension,
 * and back to their original position when used again there.
 * This avoids needing a full portal-frame detection system while still
 * giving access to the custom dimension.
 */
public class ItemVoidProjector extends Item {

    public ItemVoidProjector() {
        setUnlocalizedName("void_projector");
        setTextureName(Reference.MOD_ID + ":void_projector");
        setMaxStackSize(1);
        setCreativeTab(net.minecraft.creativetab.CreativeTabs.tabTools);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            return stack;
        }
        if (!(player instanceof EntityPlayerMP)) {
            return stack;
        }
        EntityPlayerMP mp = (EntityPlayerMP) player;
        NBTTagCompound data = player.getEntityData();

        if (player.dimension == Reference.NULL_VOID_DIMENSION_ID) {
            // Return trip
            int dim = data.hasKey(Reference.NBT_VOID_RETURN_DIM) ? data.getInteger(Reference.NBT_VOID_RETURN_DIM) : 0;
            double x = data.getDouble(Reference.NBT_VOID_RETURN_X);
            double y = data.getDouble(Reference.NBT_VOID_RETURN_Y);
            double z = data.getDouble(Reference.NBT_VOID_RETURN_Z);
            FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager()
                    .transferPlayerToDimension(mp, dim, new com.bin10mod.dimension.NullVoidTeleporter(mp.mcServer.worldServerForDimension(dim)));
            mp.setPositionAndUpdate(x, y, z);
            mp.addChatMessage(new ChatComponentText("\u00a7bReturned from the Null Void."));
        } else {
            data.setDouble(Reference.NBT_VOID_RETURN_X, player.posX);
            data.setDouble(Reference.NBT_VOID_RETURN_Y, player.posY);
            data.setDouble(Reference.NBT_VOID_RETURN_Z, player.posZ);
            data.setInteger(Reference.NBT_VOID_RETURN_DIM, player.dimension);
            FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager()
                    .transferPlayerToDimension(mp, Reference.NULL_VOID_DIMENSION_ID,
                            new com.bin10mod.dimension.NullVoidTeleporter(mp.mcServer.worldServerForDimension(Reference.NULL_VOID_DIMENSION_ID)));
            mp.addChatMessage(new ChatComponentText("\u00a75You have been pulled into the Null Void..."));
        }
        return stack;
    }
}
