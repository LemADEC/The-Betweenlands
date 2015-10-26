package thebetweenlands.herblore.aspects.list;

import net.minecraft.nbt.NBTTagCompound;
import thebetweenlands.herblore.aspects.IAspect;

public class AspectByrginaz implements IAspect {
	public String getName() {
		return "Byrginaz";
	}

	public String getType() {
		return "Water";
	}

	public String getDescription() {
		return "Magical property which relates to water. Any combination with this effect can be related to water.";
	}

	public void readFromNBT(NBTTagCompound tagCompound) {

	}

	public void writeToNBT(NBTTagCompound tagCompound) {

	}
}