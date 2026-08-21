package com.bin10mod.item;

import com.bin10mod.alien.AlienForm;
import com.bin10mod.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Right-click (short tap): cycles through the 10 aliens.
 * Sneak + right-click: transforms into the currently selected alien
 * (or reverts back to human form if already transformed).
 */
public class ItemOmnitrix extends Item {

    @SideOnly(Side.CLIENT)
    private IIcon iconOff;
    @SideOnly(Side.CLIENT)
    private IIcon iconOn;

    public ItemOmnitrix() {
        setUnlocalizedName("omnitrix");
        setMaxStackSize(1);
        setCreativeTab(net.minecraft.creativetab.CreativeTabs.tabTools);
    }

    private NBTTagCompound getTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        return stack.getTagCompound();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        NBTTagCompound tag = getTag(stack);

        if (player.isSneaking()) {
            boolean transformed = tag.getBoolean(Reference.NBT_TRANSFORMED);
            if (transformed) {
                revert(player, tag);
            } else {
                int cooldown = tag.getInteger(Reference.NBT_COOLDOWN);
                if (cooldown > 0) {
                    if (!world.isRemote) {
                        player.addChatMessage(new net.minecraft.util.ChatComponentText(
                                "\u00a7cOmnitrix perezaryazhaetsya: " + (cooldown / 20) + "s"));
                    }
                    return stack;
                }
                int selected = tag.getInteger(Reference.NBT_SELECTED);
                AlienForm form = AlienForm.byIndex(selected);
                tag.setBoolean(Reference.NBT_TRANSFORMED, true);
                tag.setString(Reference.NBT_ALIEN, form.name());
                tag.setInteger(Reference.NBT_TRANSFORM_TIME, Reference.TRANSFORM_DURATION_TICKS);
                if (!world.isRemote) {
                    player.addChatMessage(new net.minecraft.util.ChatComponentText(
                            "\u00a7aTransformed: " + form.getDisplayName() + " (" + form.getSpecies() + ")"));
                }
            }
        } else {
            int selected = tag.getInteger(Reference.NBT_SELECTED);
            selected = (selected + 1) % AlienForm.values().length;
            tag.setInteger(Reference.NBT_SELECTED, selected);
            AlienForm form = AlienForm.byIndex(selected);
            if (!world.isRemote) {
                player.addChatMessage(new net.minecraft.util.ChatComponentText(
                        "\u00a7bSelected: " + form.getDisplayName()));
            }
        }
        return stack;
    }

    public static void revert(EntityPlayer player, NBTTagCompound tag) {
        tag.setBoolean(Reference.NBT_TRANSFORMED, false);
        tag.removeTag(Reference.NBT_TRANSFORM_TIME);
        tag.setInteger(Reference.NBT_COOLDOWN, 40);
        if (!player.worldObj.isRemote) {
            player.addChatMessage(new net.minecraft.util.ChatComponentText("\u00a76Reverted to human form."));
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        NBTTagCompound tag = getTag(stack);
        int selected = tag.getInteger(Reference.NBT_SELECTED);
        AlienForm form = AlienForm.byIndex(selected);
        list.add("\u00a77Selected: \u00a7f" + form.getDisplayName());
        list.add("\u00a77Right-click: cycle alien");
        list.add("\u00a77Shift+Right-click: transform / revert");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        iconOff = reg.registerIcon(Reference.MOD_ID + ":omnitrix_off");
        iconOn = reg.registerIcon(Reference.MOD_ID + ":omnitrix_on");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        return damage == 1 ? iconOn : iconOff;
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        return tag != null && tag.getBoolean(Reference.NBT_TRANSFORMED);
    }
}
