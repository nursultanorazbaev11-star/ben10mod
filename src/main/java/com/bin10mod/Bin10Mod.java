package com.bin10mod;

import com.bin10mod.dimension.WorldProviderNullVoid;
import com.bin10mod.handler.PlayerTickHandler;
import com.bin10mod.init.ModBlocks;
import com.bin10mod.init.ModEntities;
import com.bin10mod.init.ModItems;
import com.bin10mod.proxy.CommonProxy;
import com.bin10mod.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Bin10Mod {

    @Instance(Reference.MOD_ID)
    public static Bin10Mod instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ModItems.init();
        ModBlocks.init();
        ModEntities.init();

        if (!DimensionManager.isDimensionRegistered(Reference.NULL_VOID_DIMENSION_ID)) {
            DimensionManager.registerProviderType(Reference.NULL_VOID_DIMENSION_ID, WorldProviderNullVoid.class, true);
            DimensionManager.registerDimension(Reference.NULL_VOID_DIMENSION_ID, Reference.NULL_VOID_DIMENSION_ID);
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(new PlayerTickHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ModItems.registerRecipes();
    }
}
