package com.bin10mod.proxy;

import com.bin10mod.entity.EntityDNAlien;
import com.bin10mod.entity.EntityHighbreed;
import com.bin10mod.entity.boss.EntityVilgax;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.model.ModelZombie;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        // Placeholder renders reuse the vanilla biped/zombie model so the mod
        // is fully playable before custom models/textures are authored.
        // Replace with dedicated ModelBase + textures for the real look.
        RenderingRegistry.registerEntityRenderingHandler(EntityDNAlien.class,
                new RenderBiped(new ModelZombie(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityHighbreed.class,
                new RenderBiped(new ModelZombie(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityVilgax.class,
                new RenderBiped(new ModelZombie(), 1.2F));
    }
}
