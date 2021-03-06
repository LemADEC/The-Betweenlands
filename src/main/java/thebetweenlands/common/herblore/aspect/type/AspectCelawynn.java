package thebetweenlands.common.herblore.aspect.type;

public class AspectCelawynn implements IAspectType {
	@Override
	public String getName() {
		return "Celawynn";
	}

	@Override
	public String getType() {
		return "Stomach";
	}

	@Override
	public String getDescription() {
		return "Has effect on the stomach. So this could have effect on the hunger bar for example.";
	}

	@Override
	public int getIconIndex() {
		return 4;
	}
}
