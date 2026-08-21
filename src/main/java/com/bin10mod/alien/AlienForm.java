package com.bin10mod.alien;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

/**
 * The 10 classic Omnitrix aliens. Since MC 1.7.10 does not make it practical
 * (in a mod of this scope) to swap the player's model/hitbox on the fly,
 * each form is represented gameplay-wise through attributes / potion effects
 * applied every tick while transformed (see PlayerTickHandler). This keeps
 * every alien mechanically distinct and useful without requiring a custom
 * player renderer.
 */
public enum AlienForm {

    HEATBLAST("Heatblast", "Pyronite", 0xE25822) {
        @Override
        public void applyEffects(EntityPlayer player) {
            player.extinguish();
            player.setFire(0);
            addEffect(player, Potion.fireResistance, 1);
            addEffect(player, Potion.damageBoost, 0);
        }
    },
    XLR8("XLR8", "Kineceleran", 0x1B57B0) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.moveSpeed, 4);
            addEffect(player, Potion.digSpeed, 2);
        }
    },
    FOUR_ARMS("Four Arms", "Tetramand", 0xC0392B) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.damageBoost, 2);
        }
    },
    DIAMONDHEAD("Diamondhead", "Petrosapien", 0x58D3F7) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.resistance, 1);
            addEffect(player, Potion.digSpeed, 1);
        }
    },
    WILDMUTT("Wildmutt", "Vulpimancer", 0x8E6E53) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.nightVision, 0);
            addEffect(player, Potion.moveSpeed, 1);
            addEffect(player, Potion.damageBoost, 1);
        }
    },
    GREY_MATTER("Grey Matter", "Galvan", 0x808080) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.digSpeed, 3);
            addEffect(player, Potion.moveSpeed, 0);
        }
    },
    STINKFLY("Stinkfly", "Lepidopterran", 0x27AE60) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.jump, 3);
            player.fallDistance = 0.0f;
        }
    },
    UPGRADE("Upgrade", "Galvanic Mechamorph", 0x2ECC71) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.absorption, 0);
            addEffect(player, Potion.digSpeed, 1);
        }
    },
    RIPJAWS("Ripjaws", "Piscciss Volann", 0x2980B9) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.waterBreathing, 0);
            addEffect(player, Potion.moveSpeed, 1);
            if (player.isInWater()) {
                addEffect(player, Potion.damageBoost, 1);
            }
        }
    },
    GHOSTFREAK("Ghostfreak", "Ectonurite", 0x1C2833) {
        @Override
        public void applyEffects(EntityPlayer player) {
            addEffect(player, Potion.invisibility, 0);
            addEffect(player, Potion.nightVision, 0);
        }
    };

    private final String displayName;
    private final String species;
    private final int color;

    AlienForm(String displayName, String species, int color) {
        this.displayName = displayName;
        this.species = species;
        this.color = color;
    }

    public abstract void applyEffects(EntityPlayer player);

    protected static void addEffect(EntityPlayer player, Potion potion, int amplifier) {
        // duration 60 ticks (3s), refreshed every tick by the handler so it never runs out mid-transformation
        player.addPotionEffect(new PotionEffect(potion.id, 60, amplifier, true));
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSpecies() {
        return species;
    }

    public int getColor() {
        return color;
    }

    public static AlienForm byIndex(int index) {
        AlienForm[] values = values();
        return values[((index % values.length) + values.length) % values.length];
    }

    public static AlienForm byName(String name) {
        for (AlienForm form : values()) {
            if (form.name().equals(name)) {
                return form;
            }
        }
        return HEATBLAST;
    }
}
