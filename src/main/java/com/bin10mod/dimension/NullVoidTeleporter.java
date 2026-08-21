package com.bin10mod.dimension;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

/**
 * Minimal teleporter: just places the entity safely without vanilla's
 * nether-portal frame search/creation logic.
 */
public class NullVoidTeleporter extends Teleporter {

    public NullVoidTeleporter(WorldServer world) {
        super(world);
    }

    @Override
    public void placeInPortal(Entity entity, double x, double y, double z, float rotationYaw) {
        entity.setLocationAndAngles(x, 64.0D, z, rotationYaw, 0.0F);
        entity.motionX = 0.0D;
        entity.motionY = 0.0D;
        entity.motionZ = 0.0D;
    }

    @Override
    public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotationYaw) {
        placeInPortal(entity, x, y, z, rotationYaw);
        return true;
    }

    @Override
    public void placeInPortal(Entity entity, float rotationYaw) {
        placeInPortal(entity, entity.posX, 64.0D, entity.posZ, rotationYaw);
    }
}
