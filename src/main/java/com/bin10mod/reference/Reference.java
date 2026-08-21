package com.bin10mod.reference;

public class Reference {
    public static final String MOD_ID = "bin10mod";
    public static final String MOD_NAME = "Ben 10: Omniverse Mod";
    public static final String VERSION = "1.0.0";
    public static final String MC_VERSION = "1.7.10";

    public static final String CLIENT_PROXY = "com.bin10mod.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.bin10mod.proxy.CommonProxy";

    // NBT keys used on ItemStack (Omnitrix) and on the player's entity data
    public static final String NBT_TRANSFORMED = "bin10_transformed";
    public static final String NBT_ALIEN = "bin10_alien";
    public static final String NBT_SELECTED = "bin10_selected";
    public static final String NBT_COOLDOWN = "bin10_cooldown";
    public static final String NBT_TRANSFORM_TIME = "bin10_transform_time";
    public static final String NBT_IN_VOID = "bin10_in_void";
    public static final String NBT_VOID_RETURN_X = "bin10_return_x";
    public static final String NBT_VOID_RETURN_Y = "bin10_return_y";
    public static final String NBT_VOID_RETURN_Z = "bin10_return_z";
    public static final String NBT_VOID_RETURN_DIM = "bin10_return_dim";

    // Custom dimension id for the Null Void (must not collide with other mods; user can change in config)
    public static final int NULL_VOID_DIMENSION_ID = -20;

    // Balance constants
    public static final int TRANSFORM_DURATION_TICKS = 20 * 60 * 5; // 5 minutes per transformation
    public static final int TRANSFORM_COOLDOWN_TICKS = 20 * 20; // 20 seconds recharge after timing out
}
