package com.bin10mod.entity.boss;

import com.bin10mod.entity.EntityDNAlien;
import com.bin10mod.init.ModItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Vilgax - the mod's main boss. Spawns rarely, hits hard, periodically
 * calls in DNAlien reinforcements, and drops a large DNA-sample + XP reward.
 */
public class EntityVilgax extends EntityMob {

    private int summonCooldown = 0;

    public EntityVilgax(World world) {
        super(world);
        setSize(1.4F, 3.6F);
        this.experienceValue = 100;

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, true));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.24D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!worldObj.isRemote) {
            if (summonCooldown > 0) {
                summonCooldown--;
            } else if (this.getAttackTarget() != null && this.getHealth() < this.getMaxHealth()) {
                summonMinions();
                summonCooldown = 20 * 25; // every 25s while engaged
            }
        }
    }

    private void summonMinions() {
        for (int i = 0; i < 2; i++) {
            EntityDNAlien minion = new EntityDNAlien(worldObj);
            minion.setLocationAndAngles(posX + (rand.nextDouble() - 0.5D) * 4.0D, posY, posZ + (rand.nextDouble() - 0.5D) * 4.0D, rotationYaw, 0.0F);
            worldObj.spawnEntityInWorld(minion);
        }
    }

    @Override
    public boolean attackEntityAsMob(net.minecraft.entity.Entity target) {
        boolean result = super.attackEntityAsMob(target);
        if (result && target instanceof EntityPlayer) {
            target.motionY += 0.4D; // signature Vilgax knockback
        }
        return result;
    }

    @Override
    protected void dropFewItems(boolean recentlyHit, int looting) {
        dropItem(ModItems.dnaSample, 8 + rand.nextInt(5));
    }

    @Override
    public boolean isAIEnabled() {
        return true;
    }
}
