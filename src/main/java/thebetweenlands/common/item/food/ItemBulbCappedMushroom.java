package thebetweenlands.common.item.food;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import thebetweenlands.common.item.misc.ItemPlantableFood;
import thebetweenlands.common.registries.BlockRegistry;

public class ItemBulbCappedMushroom extends ItemPlantableFood {
	public ItemBulbCappedMushroom() {
		super(3, 0.6F);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		if (player != null) {
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("nausea"), 200, 1));
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("night_vision"), 200, 1));
		}
	}

	@Override
	protected Block getBlock(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos) {
		return BlockRegistry.BULB_CAPPED_MUSHROOM;
	}
}
