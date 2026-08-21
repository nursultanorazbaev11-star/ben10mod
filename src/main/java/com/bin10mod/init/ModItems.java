package com.bin10mod.init;

import com.bin10mod.item.ItemDNASample;
import com.bin10mod.item.ItemOmnitrix;
import com.bin10mod.item.ItemVoidProjector;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

public class ModItems {

    public static Item omnitrix;
    public static Item dnaSample;
    public static Item voidProjector;

    public static void init() {
        omnitrix = new ItemOmnitrix().setUnlocalizedName("omnitrix");
        dnaSample = new ItemDNASample();
        voidProjector = new ItemVoidProjector().setUnlocalizedName("void_projector");

        GameRegistry.registerItem(omnitrix, "omnitrix");
        GameRegistry.registerItem(dnaSample, "dna_sample");
        GameRegistry.registerItem(voidProjector, "void_projector");
    }

    public static void registerRecipes() {
        // Omnitrix: gold ingots around a redstone core with a diamond on top
        GameRegistry.addRecipe(new ItemStack(omnitrix),
                " G ",
                "GRG",
                " D ",
                'G', Items.gold_ingot,
                'R', Items.redstone,
                'D', Items.diamond);

        // Void Projector: DNA samples + ender pearl + iron
        GameRegistry.addRecipe(new ItemStack(voidProjector),
                "IDI",
                "DED",
                "IDI",
                'I', Items.iron_ingot,
                'D', dnaSample,
                'E', Items.ender_pearl);
    }
}
