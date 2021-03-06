package thebetweenlands.common.item.tools;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import thebetweenlands.common.item.corrosion.CorrosionHelper;
import thebetweenlands.common.item.corrosion.ICorrodible;


public class ItemBLAxe extends ItemTool implements ICorrodible {
	public ItemBLAxe(ToolMaterial material) {
		super(8.0F, -3.2F, material, Sets.newHashSet(new Block[]{}));

		CorrosionHelper.addCorrosionPropertyOverrides(this);
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}
}
