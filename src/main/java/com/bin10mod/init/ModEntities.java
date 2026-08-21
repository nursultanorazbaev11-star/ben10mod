package com.bin10mod.init;

import com.bin10mod.Bin10Mod;
import com.bin10mod.entity.EntityDNAlien;
import com.bin10mod.entity.EntityHighbreed;
import com.bin10mod.entity.boss.EntityVilgax;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ModEntities {

    private static int entityId = 0;

    public static void init() {
        EntityRegistry.registerModEntity(EntityDNAlien.class, "dna_alien", ++entityId, Bin10Mod.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityHighbreed.class, "highbreed", ++entityId, Bin10Mod.instance, 64, 1, true);
        EntityRegistry.registerModEntity(EntityVilgax.class, "vilgax", ++entityId, Bin10Mod.instance, 80, 1, true);
    }
}
