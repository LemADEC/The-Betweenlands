package thebetweenlands.common.entity.projectiles;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import thebetweenlands.common.entity.mobs.EntityBloodSnail;

public class EntitySnailPoisonJet extends EntityThrowable {

    public EntitySnailPoisonJet(World world) {
        super(world);
        setSize(0.7F, 0.7F);
    }

    public EntitySnailPoisonJet(World world, EntityLiving entity) {
        super(world, entity);
    }

    public EntitySnailPoisonJet(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public EntitySnailPoisonJet(World world, EntityPlayer player) {
        super(world, player);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        /*if (worldObj.isRemote)
            trailParticles(worldObj, posX, posY + 0.35D, posZ, rand);*/

        if (ticksExisted > 140)
            setDead();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.entityHit != null) {

            if (result.entityHit instanceof EntityLivingBase && !(result.entityHit instanceof EntityBloodSnail)) {
                if (!worldObj.isRemote) {
                    ((EntityLivingBase) result.entityHit).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), 5 * 20, 0));
                    result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), 1.0F);
                }
                setDead();
            }
        } else
            setDead();
    }

    @Override
    protected float getGravityVelocity() {
        return 0.02F;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    public boolean attackEntityFrom(DamageSource source, int amount) {
        return false;
    }

    /*TODO add effect when SNAIL_POISON effect is added
    @SideOnly(Side.CLIENT)
    public void trailParticles(World world, double x, double y, double z, Random rand) {
        for (int count = 0; count < 5; ++count)
            BLParticle.SNAIL_POSION.spawn(worldObj, x, y, z, 0.0D, 0.0D, 0.0D, 0, 0);
    }*/
}
