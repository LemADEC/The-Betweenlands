package thebetweenlands.common.herblore.aspect.type;

public class AspectYihinren implements IAspectType {
	@Override
	public String getName() {
		return "Yihinren";
	}

	@Override
	public String getType() {
		return "Form";
	}

	@Override
	public String getDescription() {
		return "This effect has influence on the form of things, both physical and psychological. A very rare aspect that is mainly used in the special potions.";
	}

	@Override
	public int getIconIndex() {
		return 12;
	}
}
