package com.bin10mod.init;

import com.bin10mod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {

    public static Block nullVoidStone;

    public static void init() {
        nullVoidStone = new Block(Material.rock)
                .setBlockName("null_void_stone")
                .setBlockTextureName(Reference.MOD_ID + ":null_void_stone")
                .setHardness(2.0F)
                .setResistance(10.0F)
                .setCreativeTab(net.minecraft.creativetab.CreativeTabs.tabBlock);

        GameRegistry.registerBlock(nullVoidStone, "null_void_stone");
    }
}
