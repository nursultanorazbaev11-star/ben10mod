package com.bin10mod.item;

import com.bin10mod.reference.Reference;
import net.minecraft.item.Item;

public class ItemDNASample extends Item {
    public ItemDNASample() {
        setUnlocalizedName("dna_sample");
        setTextureName(Reference.MOD_ID + ":dna_sample");
        setMaxStackSize(64);
        setCreativeTab(net.minecraft.creativetab.CreativeTabs.tabMisc);
    }
}
