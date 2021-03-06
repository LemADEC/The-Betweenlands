package thebetweenlands.common.item.herblore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thebetweenlands.client.tab.BLCreativeTabs;
import thebetweenlands.common.TheBetweenlands;
import thebetweenlands.common.herblore.aspect.DiscoveryContainer;
import thebetweenlands.common.herblore.aspect.IDiscoveryProvider;
import thebetweenlands.common.proxy.CommonProxy;

public class ItemManualHL extends Item implements IDiscoveryProvider<ItemStack>{
    public ItemManualHL() {
        setMaxStackSize(1);
        this.setCreativeTab(BLCreativeTabs.HERBLORE);
    }

    @Override
    public DiscoveryContainer getContainer(ItemStack stack) {
        if(stack != null) {
            if(stack.getTagCompound() == null)
                stack.setTagCompound(new NBTTagCompound());
            return new DiscoveryContainer(this, stack).updateFromNBT(stack.getTagCompound(), false);
        }
        return null;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) {
        player.openGui(TheBetweenlands.INSTANCE, CommonProxy.GUI_HL, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public void saveContainer(ItemStack stack, DiscoveryContainer container) {
        if(stack != null) {
            if(stack.getTagCompound() == null)
                stack.setTagCompound(new NBTTagCompound());
            stack.setTagCompound(container.writeToNBT(stack.getTagCompound()));
        }
    }
}
