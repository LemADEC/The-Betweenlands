package thebetweenlands.common.registries;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import thebetweenlands.common.herblore.Amounts;
import thebetweenlands.common.herblore.aspect.AspectManager;
import thebetweenlands.common.herblore.aspect.AspectManager.AspectEntry;
import thebetweenlands.common.herblore.aspect.AspectManager.AspectGroup;
import thebetweenlands.common.herblore.aspect.AspectManager.AspectItem;
import thebetweenlands.common.herblore.aspect.AspectManager.AspectItemEntry;
import thebetweenlands.common.herblore.aspect.AspectManager.AspectTier;
import thebetweenlands.common.herblore.aspect.type.AspectArmaniis;
import thebetweenlands.common.herblore.aspect.type.AspectAzuwynn;
import thebetweenlands.common.herblore.aspect.type.AspectByariis;
import thebetweenlands.common.herblore.aspect.type.AspectByrginaz;
import thebetweenlands.common.herblore.aspect.type.AspectCelawynn;
import thebetweenlands.common.herblore.aspect.type.AspectDayuniis;
import thebetweenlands.common.herblore.aspect.type.AspectFergalaz;
import thebetweenlands.common.herblore.aspect.type.AspectFirnalaz;
import thebetweenlands.common.herblore.aspect.type.AspectFreiwynn;
import thebetweenlands.common.herblore.aspect.type.AspectGeoliirgaz;
import thebetweenlands.common.herblore.aspect.type.AspectOrdaniis;
import thebetweenlands.common.herblore.aspect.type.AspectYeowynn;
import thebetweenlands.common.herblore.aspect.type.AspectYihinren;
import thebetweenlands.common.herblore.aspect.type.AspectYunugaz;
import thebetweenlands.common.herblore.aspect.type.IAspectType;
import thebetweenlands.common.item.herblore.ItemCrushed.EnumItemCrushed;

public class AspectRegistry {
	public static final List<IAspectType> ASPECT_TYPES = new ArrayList<IAspectType>();

	public static final IAspectType AZUWYNN = new AspectAzuwynn();
	public static final IAspectType ARMANIIS = new AspectArmaniis();
	public static final IAspectType BYARIIS = new AspectByariis();
	public static final IAspectType BYRGINAZ = new AspectByrginaz();
	public static final IAspectType CELAWYNN = new AspectCelawynn();
	public static final IAspectType DAYUNIIS = new AspectDayuniis();
	public static final IAspectType FERGALAZ = new AspectFergalaz();
	public static final IAspectType FIRNALAZ = new AspectFirnalaz();
	public static final IAspectType FREIWYNN = new AspectFreiwynn();
	public static final IAspectType GEOLIIRGAZ = new AspectGeoliirgaz();
	public static final IAspectType ORDANIIS = new AspectOrdaniis();
	public static final IAspectType YEOWYNN = new AspectYeowynn();
	public static final IAspectType YUNUGAZ = new AspectYunugaz();
	public static final IAspectType YIHINREN = new AspectYihinren();

	static {
		try {
			for(Field f : AspectRegistry.class.getDeclaredFields()) {
				if(f.getType() == IAspectType.class) {
					Object obj = f.get(null);
					if(obj instanceof IAspectType) {
						ASPECT_TYPES.add((IAspectType)obj);
					}
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static IAspectType getAspectTypeFromName(String name) {
		for(IAspectType type : ASPECT_TYPES) {
			if(type.getName().equals(name)) return type;
		}
		return null;
	}

	public void init() {
		registerItems();
		registerAspects();
	}

	private void registerItems() {
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_ALGAE.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_ARROW_ARUM.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BLACKHAT_MUSHROOM.create(1)), 	AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BOG_BEAN.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BONESET.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BOTTLE_BRUSH_GRASS.create(1)), 	AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BROOM_SEDGE.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BUTTON_BUSH.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_CATTAIL.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_CAVE_GRASS.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_CAVE_MOSS.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_COPPER_IRIS.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_DRIED_SWAMP_REED.create(1)), 	AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_FLATHEAD_MUSHROOM.create(1)), 	AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_HANGER.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_LICHEN.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_MARSH_HIBISCUS.create(1)), 		AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_MARSH_MALLOW.create(1)), 		AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_MILKWEED.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_MOSS.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_NETTLE.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_PHRAGMITES.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_PICKEREL_WEED.create(1)), 		AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_SHOOTS.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_SLUDGECREEP.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_SOFT_RUSH.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_SWAMP_KELP.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_SWAMP_GRASS_TALL.create(1)), 	AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_TANGLED_ROOTS.create(1)), 		AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_WEEDWOOD_BARK.create(1)), 		AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_WATER_WEEDS.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_VOLARPAD.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_THORNS.create(1)), 				AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_POISON_IVY.create(1)), 			AspectTier.COMMON, AspectGroup.HERB, 0.45F, 1.0F), 3);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_GENERIC_LEAF.create(1)), 		AspectTier.COMMON, AspectGroup.HERB, 0.15F, 0.2F), 4);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BLOOD_SNAIL_SHELL.create(1)), 	AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 1.0F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BLUE_IRIS.create(1)), 			AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 1.0F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BLUE_EYED_GRASS.create(1)), 		AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_CARDINAL_FLOWER.create(1)), 		AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_MIRE_CORAL.create(1)), 			AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_MARSH_MARIGOLD.create(1)), 		AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_GOLDEN_CLUB.create(1)), 			AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_DEEP_WATER_CORAL.create(1)), 	AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_MIRE_SNAIL_SHELL.create(1)), 	AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_BULB_CAPPED_MUSHROOM.create(1)),	AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_ANGLER_TOOTH.create(1)), 		AspectTier.UNCOMMON, AspectGroup.HERB, 1.6F, 0.65F), 2);
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_SUNDEW.create(1)), 				AspectTier.RARE, AspectGroup.HERB, 2.0F, 0.5F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_PITCHER_PLANT.create(1)), 		AspectTier.RARE, AspectGroup.HERB, 2.0F, 0.5F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_VENUS_FLY_TRAP.create(1)), 		AspectTier.RARE, AspectGroup.HERB, 2.0F, 0.5F));

		//Gems
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_GREEN_MIDDLE_GEM.create(1)), 	AspectTier.UNCOMMON, AspectGroup.GEM_FERGALAZ, 2.0F, 0.25F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_CRIMSON_MIDDLE_GEM.create(1)), 	AspectTier.UNCOMMON, AspectGroup.GEM_FIRNALAZ, 2.0F, 0.25F));
		AspectManager.addStaticAspectsToItem(new AspectItemEntry(new AspectItem(EnumItemCrushed.GROUND_AQUA_MIDDLE_GEM.create(1)), 		AspectTier.UNCOMMON, AspectGroup.GEM_BYRGINAZ, 2.0F, 0.25F));
	}

	private void registerAspects() {
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.BYARIIS, 	AspectTier.COMMON, 		AspectGroup.HERB, Amounts.HIGH));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.AZUWYNN, 	AspectTier.COMMON, 		AspectGroup.HERB, Amounts.LOW_MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.CELAWYNN, 	AspectTier.COMMON, 		AspectGroup.HERB, Amounts.LOW_MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.ORDANIIS, 	AspectTier.COMMON, 		AspectGroup.HERB, Amounts.LOW_MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.YEOWYNN, 	AspectTier.COMMON, 		AspectGroup.HERB, Amounts.LOW_MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.ARMANIIS, 	AspectTier.UNCOMMON, 	AspectGroup.HERB, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.BYRGINAZ, 	AspectTier.UNCOMMON, 	AspectGroup.HERB, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.DAYUNIIS, 	AspectTier.UNCOMMON, 	AspectGroup.HERB, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.FERGALAZ, 	AspectTier.UNCOMMON, 	AspectGroup.HERB, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.FIRNALAZ, 	AspectTier.UNCOMMON, 	AspectGroup.HERB, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.FREIWYNN, 	AspectTier.UNCOMMON, 	AspectGroup.HERB, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.YUNUGAZ, 	AspectTier.UNCOMMON, 	AspectGroup.HERB, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.GEOLIIRGAZ,	AspectTier.RARE, 		AspectGroup.HERB, Amounts.MEDIUM_HIGH));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.YIHINREN, 	AspectTier.RARE, 		AspectGroup.HERB, Amounts.MEDIUM_HIGH));

		//For middle gems
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.BYRGINAZ, 	AspectTier.UNCOMMON, 	AspectGroup.GEM_BYRGINAZ, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.FERGALAZ, 	AspectTier.UNCOMMON, 	AspectGroup.GEM_FERGALAZ, Amounts.MEDIUM));
		AspectManager.registerAspect(new AspectEntry(AspectRegistry.FIRNALAZ, 	AspectTier.UNCOMMON, 	AspectGroup.GEM_FIRNALAZ, Amounts.MEDIUM));
	}
}
