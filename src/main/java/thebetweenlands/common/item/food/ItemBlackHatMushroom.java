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

public class ItemBlackHatMushroom extends ItemPlantableFood {
	public ItemBlackHatMushroom() {
		super(3, 0.6f);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
		super.onFoodEaten(stack, world, player);
		if (player != null) {
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("poison"), 500, 1));
		}
	}

	@Override
	protected Block getBlock(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos) {
		return BlockRegistry.BLACK_HAT_MUSHROOM;
	}
}
