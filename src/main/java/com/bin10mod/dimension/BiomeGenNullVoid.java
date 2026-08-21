package com.bin10mod.dimension;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenNullVoid extends BiomeGenBase {

    // Use a free custom biome id slot; adjust in config if it conflicts with another mod.
    public static final int BIOME_ID = 190;

    public BiomeGenNullVoid() {
        super(BIOME_ID);
        setBiomeName("Null Void");
        setDisableRain();
        setTemperatureRainfall(0.5F, 0.0F);
        setColor(0x1A0A2E);
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }
}
