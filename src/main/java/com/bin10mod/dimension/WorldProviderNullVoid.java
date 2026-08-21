package com.bin10mod.dimension;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderNullVoid extends WorldProvider {

    public WorldProviderNullVoid() {
        this.hasNoSky = true;
    }

    @Override
    public String getDimensionName() {
        return "Null Void";
    }

    @Override
    public void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManager(new BiomeGenNullVoid(), 0.0F);
        this.dimensionId = com.bin10mod.reference.Reference.NULL_VOID_DIMENSION_ID;
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        // Thin bedrock floor, empty above it - gives a "void arena" feel without
        // needing a fully custom chunk provider implementation.
        return new net.minecraft.world.gen.ChunkProviderFlat(worldObj, worldObj.getSeed(), false, "3;7;1;");
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.5F; // permanently dark
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        return true;
    }

    @Override
    public WorldChunkManager getWorldChunkManager() {
        return this.worldChunkMgr;
    }
}
