package thebetweenlands.client.particle.entity;

import net.minecraft.block.Block;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thebetweenlands.client.particle.ParticleFactory;
import thebetweenlands.client.particle.ParticleTextureStitcher;
import thebetweenlands.client.particle.ParticleTextureStitcher.IParticleSpriteReceiver;
import thebetweenlands.common.block.terrain.BlockSwampWater;

public class ParticleBug  extends Particle implements IParticleSpriteReceiver {
	private float scale;
	private float jitter;
	private float speed;
	private boolean underwater;
	private double tx, ty, tz;

	protected ParticleBug(World world, double x, double y, double z, double mx, double my, double mz, int maxAge, float speed, float jitter, float scale, boolean underwater) {
		super(world, x, y, z, 0, 0, 0);
		this.posX = this.prevPosX = this.tx = x;
		this.posY = this.prevPosY = this.ty = y;
		this.posZ = this.prevPosZ = this.tz = z;
		this.motionX = mx;
		this.motionY = my;
		this.motionZ = mz;
		this.particleMaxAge = maxAge;
		//this.noClip = true;
		this.scale = scale;
		this.jitter = jitter;
		this.speed = speed;
		this.underwater = underwater;
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.moveEntity(this.worldObj.rand.nextFloat()*this.jitter*2-this.jitter, this.worldObj.rand.nextFloat()*this.jitter*2-this.jitter, this.worldObj.rand.nextFloat()*this.jitter*2-this.jitter);
		double distToTarget = Math.sqrt((this.tx-this.posX)*(this.tx-this.posX)+(this.ty-this.posY)*(this.ty-this.posY)+(this.tz-this.posZ)*(this.tz-this.posZ));
		Block currBlock = this.worldObj.getBlockState(new BlockPos((int)Math.floor(this.posX), (int)Math.floor(this.posY), (int)Math.floor(this.posZ))).getBlock();
		if(this.underwater == (currBlock instanceof BlockSwampWater == false)) {
			this.motionY -= 0.08D;
			if(this.isCollided)
				this.motionY += 0.25D;
			this.tx = this.posX;
			this.ty = this.posY;
			this.tz = this.posZ;
		} else {
			if(distToTarget <= this.speed + this.jitter) {
				this.tx = this.posX + this.worldObj.rand.nextFloat()*2.0F-1.0F;
				this.ty = this.posY + this.worldObj.rand.nextFloat()*2.0F-1.0F;
				this.tz = this.posZ + this.worldObj.rand.nextFloat()*2.0F-1.0F;
				Block targetBlock = this.worldObj.getBlockState(new BlockPos((int)Math.floor(this.tx), (int)Math.floor(this.ty + 0.5D), (int)Math.floor(this.tz))).getBlock();
				if(this.underwater == (targetBlock instanceof BlockSwampWater == false)) {
					this.tx = this.posX;
					this.ty = this.posY;
					this.tz = this.posZ;
				}
			} else {
				this.moveEntity(-(this.posX-this.tx)/distToTarget*this.speed, -(this.posY-this.ty)/distToTarget*this.speed, -(this.posZ-this.tz)/distToTarget*this.speed);
			}
		}
	}

	public static final class FlyFactory extends ParticleFactory<FlyFactory, ParticleBug> {
		public FlyFactory() {
			super(ParticleBug.class, ParticleTextureStitcher.create(ParticleBug.class, new ResourceLocation("thebetweenlands:particle/fly")));
		}

		@Override
		public ParticleBug createParticle(ImmutableParticleArgs args) {
			return new ParticleBug(args.world, args.x, args.y, args.z, args.motionX, args.motionY, args.motionZ, args.data.getInt(0), args.data.getFloat(1), args.data.getFloat(2), args.scale, args.data.getBool(3));
		}

		@Override
		protected void setBaseArguments(ParticleArgs args) {
			args.withScale(0.06F).withData(400, 0.05F, 0.025F, false);
		}

		@Override
		protected void setDefaultArguments(World world, double x, double y, double z, ParticleArgs args) {
			args.withScale(0.06F * world.rand.nextFloat());
		}
	}

	public static final class MosquitoFactory extends ParticleFactory<MosquitoFactory, ParticleBug> {
		public MosquitoFactory() {
			super(ParticleBug.class, ParticleTextureStitcher.create(ParticleBug.class, new ResourceLocation("thebetweenlands:particle/mosquito")));
		}

		@Override
		public ParticleBug createParticle(ImmutableParticleArgs args) {
			return new ParticleBug(args.world, args.x, args.y, args.z, args.motionX, args.motionY, args.motionZ, args.data.getInt(0), args.data.getFloat(1), args.data.getFloat(2), args.scale, args.data.getBool(3));
		}

		@Override
		protected void setBaseArguments(ParticleArgs args) {
			args.withScale(0.1F).withData(400, 0.05F, 0.025F, false);
		}

		@Override
		protected void setDefaultArguments(World world, double x, double y, double z, ParticleArgs args) {
			args.withScale(0.1F * world.rand.nextFloat());
		}
	}

	public static final class WaterBugFactory extends ParticleFactory<WaterBugFactory, ParticleBug> {
		public WaterBugFactory() {
			super(ParticleBug.class, ParticleTextureStitcher.create(ParticleBug.class, new ResourceLocation("thebetweenlands:particle/water_bug")));
		}

		@Override
		public ParticleBug createParticle(ImmutableParticleArgs args) {
			return new ParticleBug(args.world, args.x, args.y, args.z, args.motionX, args.motionY, args.motionZ, args.data.getInt(0), args.data.getFloat(1), args.data.getFloat(2), args.scale, args.data.getBool(3));
		}

		@Override
		protected void setBaseArguments(ParticleArgs args) {
			args.withScale(0.2F).withData(400, 0.03F, 0.002F, true);
		}

		@Override
		protected void setDefaultArguments(World world, double x, double y, double z, ParticleArgs args) {
			args.withScale(0.2F * world.rand.nextFloat());
		}
	}
}
