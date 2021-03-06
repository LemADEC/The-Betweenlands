package thebetweenlands.common.herblore.book;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import thebetweenlands.common.herblore.aspect.AspectManager;
import thebetweenlands.common.herblore.aspect.type.IAspectType;
import thebetweenlands.common.herblore.book.widgets.*;
import thebetweenlands.common.herblore.book.widgets.text.FormatTags;
import thebetweenlands.common.herblore.book.widgets.text.TextContainer;
import thebetweenlands.common.herblore.book.widgets.text.TextWidget;

import java.util.ArrayList;

public class PageCreators {

    /**
     * Creates the button pages for a category
     *
     * @param pages      the pages that need a button
     * @param manualType the type of manual the pages are in
     * @return a array of button pages
     */
    public static ArrayList<Page> pageCreatorButtons(ArrayList<Page> pages, Item manualType) {
        ArrayList<Page> newPages = new ArrayList<>();
        int pageAmount = pages.size();
        int times = 0;
        while (pageAmount > 0) {
            ArrayList<Page> pagesTemp = new ArrayList<>();
            pagesTemp.addAll(pages.subList(7 * times, (pageAmount > 7 ? 7 + 7 * times : 7 * times + pageAmount)));
            int height = 0;
            ArrayList<ManualWidgetBase> widgets = new ArrayList<>();
            for (Page page : pagesTemp) {
                widgets.add(new ButtonWidget(15, 10 + height, page));
                height += 20;
            }
            newPages.add(new Page("index" + times, (ArrayList<ManualWidgetBase>) widgets.clone(), false, manualType));
            widgets.clear();
            pagesTemp.clear();
            pageAmount -= 7;
            times++;
        }

        return newPages;
    }


    /**
     * Creates pages containing text
     *
     * @param x               the x coordinate for starting the text
     * @param y               the y coordinate for starting the text
     * @param unlocalizedName the unlocalized text
     * @param pageName        the page name
     * @param isHidden        whether or not the pages should be hidden until discovery
     * @param manualType      the type of manual they are in
     * @return an array of text pages
     */
    public static ArrayList<Page> TextPages(int x, int y, String unlocalizedName, String pageName, boolean isHidden, Item manualType) {
        ArrayList<Page> newPages = new ArrayList<>();
        String text = I18n.translateToLocal(unlocalizedName);
        TextContainer textContainer = parseTextContainer(new TextContainer(116, 144, text, Minecraft.getMinecraft().fontRendererObj));

        for (int i = 0; i < textContainer.getPages().size(); i++) {
            newPages.add(new Page(pageName, isHidden, manualType, new TextWidget(x, y, unlocalizedName, i)));
        }
        return newPages;
    }


    /**
     * Creates the pages for a aspect entry
     *
     * @param aspect     the aspect to create the pages for
     * @param manualType the type of manual they are in
     * @return an array for the entry
     */
    public static ArrayList<Page> AspectPages(IAspectType aspect, Item manualType) {
        ArrayList<Page> newPages = new ArrayList<>();
        int height = 0;
        ArrayList<ManualWidgetBase> widgets = new ArrayList<>();
        widgets.add(new AspectWidget(18, 12, aspect, 1f));
        widgets.add(new TextWidget(38, 14, "<font:custom>" + aspect.getName() + "</font>", true));
        height += 24;
        widgets.add(new TextWidget(18, 12 + height, "manual." + aspect.getName().toLowerCase() + ".description"));
        TextContainer textContainer = parseTextContainer(new TextContainer(116, 144, I18n.translateToLocal("manual." + aspect.getName().toLowerCase() + ".description"), Minecraft.getMinecraft().fontRendererObj));

        height += textContainer.getPages().get(0).getSegments().get(textContainer.getPages().get(0).getSegments().size() - 1).y + 18;

        if (height + 18 + 16 < 152) {
            widgets.add(new TextWidget(18, 12 + height, "manual.aspect.found.in"));
            height += 16;
            widgets.add(new AspectItemSlideShowWidget(18, 12 + height, aspect));
            height += 18;
        } else {
            newPages.add(new Page(aspect.getName().toLowerCase(), widgets, false, manualType).setParent().setAspect(aspect).setLocalizedPageName(aspect.getName()));
            widgets.add(new TextWidget(18, 12 + height, "manual.aspect.found.in"));
            height += 16;
            widgets.add(new AspectItemSlideShowWidget(18, 12 + height, aspect));
            height += 18;
        }

        /*TODO add when elixirs are added
        if (height + 10 + 18 < 152) {
            widgets.add(new TextWidget(18, 12 + height, "manual.aspect.used.in"));
            height += 10;
            ArrayList<ItemStack> items = new ArrayList<>();
            for (ElixirRecipe recipe : ElixirRecipes.getFromAspect(aspect)) {
                items.add(BLItemRegistry.elixir.getElixirItem(recipe.positiveElixir, recipe.baseDuration, 1, 0));
                items.add(BLItemRegistry.elixir.getElixirItem(recipe.negativeElixir, recipe.baseDuration, 1, 1));
            }
            widgets.add(new AspectItemSlideShowWidget(18, 12 + height, items));
            height += 18;
        } else {
            if (newPages.size() > 0)
                newPages.add(new Page(aspect.getName().toLowerCase(), widgets, false, manualType).setAspect(aspect).setLocalizedPageName(aspect.getName()));
            else
                newPages.add(new Page(aspect.getName().toLowerCase(), widgets, false, manualType).setParent().setAspect(aspect).setLocalizedPageName(aspect.getName()));
            widgets.add(new TextWidget(18, 12 + height, "manual.aspect.found.in"));
            height += 10;
            ArrayList<ItemStack> items = new ArrayList<>();
            for (ElixirRecipe recipe : ElixirRecipes.getFromAspect(aspect)) {
                items.add(BLItemRegistry.elixir.getElixirItem(recipe.positiveElixir, recipe.baseDuration, 1, 0));
                items.add(BLItemRegistry.elixir.getElixirItem(recipe.negativeElixir, recipe.baseDuration, 1, 1));
            }
            widgets.add(new AspectItemSlideShowWidget(18, 12 + height, items));
            height += 18;
        }*/

        if (widgets.size() > 0) {
            if (newPages.size() > 0)
                newPages.add(new Page(aspect.getName().toLowerCase(), widgets, false, manualType).setAspect(aspect).setLocalizedPageName(aspect.getName()));
            else
                newPages.add(new Page(aspect.getName().toLowerCase(), widgets, false, manualType).setParent().setAspect(aspect).setLocalizedPageName(aspect.getName()));
        }
        return newPages;
    }

    /**
     * Creates the pages for the ground times
     *
     * @param item       the item
     * @param manualType the type of manual they are in
     * @return an array for the entry
     */
    public static ArrayList<Page> AspectItemPages(AspectManager.AspectItem item, Item manualType) {
        ArrayList<Page> newPages = new ArrayList<>();
        int height = 0;
        ItemStack itemStack = new ItemStack(item.item, 1, item.damage);
        ArrayList<ManualWidgetBase> widgets = new ArrayList<>();
        widgets.add(new ItemWidget(18, 12, itemStack, 1f));
        //TODO add when pam is added
        //widgets.add(new ItemWidget(118, 12, PestleAndMortarRecipe.getInput(itemStack), 1f));
        widgets.add((new TextWidget(38, 16, itemStack.getDisplayName(), true)).setWidth(70));
        height += 28;
        widgets.add(new TextWidget(18, 12 + height, "manual." + itemStack.getUnlocalizedName() + ".description"));
        TextContainer textContainer = parseTextContainer(new TextContainer(116, 144, I18n.translateToLocal("manual." + itemStack.getUnlocalizedName() + ".description"), Minecraft.getMinecraft().fontRendererObj));

        height += 18 + textContainer.getPages().get(0).getSegments().get(textContainer.getPages().get(0).getSegments().size() - 1).y;
        widgets.add(new TextWidget(18, 12 + height, "manual.has.aspects"));
        height += 18;
        widgets.add(new AspectSlideShowWidget(18, 12 + height, itemStack));
        //TODO add when pem is added
        // /newPages.add(new Page(itemStack.getDisplayName().toLowerCase().replace(" ", ""), widgets, true, manualType).setParent().addItem(itemStack).addItem(PestleAndMortarRecipe.getInput(itemStack)).setLocalizedPageName(itemStack.getDisplayName()));
        return newPages;
    }

    /*TODO add when Elixirs are added
    public static ArrayList<Page> elixirPages(ItemStack item, Item manualType, ElixirEffect effect) {
        ArrayList<Page> newPages = new ArrayList<>();
        int height = 0;
        ArrayList<ManualWidgetBase> widgets = new ArrayList<>();
        widgets.add(new ItemWidget(18, 12, item, 1f));
        widgets.add(new TextWidget(38, 14, item.getDisplayName(), true));
        height += 32;
        TextContainer textContainer = new TextContainer(114, 130, I18n.translateToLocal("manual." + item.getUnlocalizedName() + ".description"), Minecraft.getMinecraft().fontRenderer);
        textContainer = parseTextContainer(textContainer);
        Page temp = null;
        if (textContainer.getPages().size() > 1) {
            widgets.add(new TextWidget(15, height, "manual." + item.getUnlocalizedName() + ".description", 0, 114, 130));
            newPages.add(new Page(item.getDisplayName().toLowerCase().replace(" ", ""), (ArrayList<ManualWidgetsBase>) widgets.clone(), false, manualType).setParent().setLocalizedPageName(item.getDisplayName()).addItem(item));
            widgets.clear();
            widgets.add(new TextWidget(15, 14, "manual." + item.getUnlocalizedName() + ".description", 1, 114, 130));
            if (ElixirRecipes.getFromEffect(effect) != null && ElixirRecipes.getFromEffect(effect).aspects != null) {
                if (textContainer.getPages().get(1).getSegments().get(textContainer.getPages().get(1).getSegments().size() - 1).y + 38 < 142)
                    widgets.add(new AspectSlideShowWidget(18, (int) (textContainer.getPages().get(1).getSegments().get(textContainer.getPages().get(1).getSegments().size() - 1).y + 22), ElixirRecipes.getFromEffect(effect).aspects));
                else
                    temp = new Page(item.getDisplayName().toLowerCase().replace(" ", ""), false, manualType, new AspectSlideShowWidget(15, 12, ElixirRecipes.getFromEffect(effect).aspects));
            }
            newPages.add(new Page(item.getDisplayName().toLowerCase().replace(" ", ""), widgets, false, manualType).setLocalizedPageName(item.getDisplayName()).addItem(item));
            if (temp != null)
                newPages.add(temp);
        } else {
            widgets.add(new TextWidget(15, height, "manual." + item.getUnlocalizedName() + ".description"));
            if (ElixirRecipes.getFromEffect(effect) != null && ElixirRecipes.getFromEffect(effect).aspects != null) {
                if (height + 24 < 142)
                    widgets.add(new AspectSlideShowWidget(18, height + (int) (textContainer.getPages().get(0).getSegments().get(textContainer.getPages().get(0).getSegments().size() - 1).y + 8), ElixirRecipes.getFromEffect(effect).aspects));
                else
                    temp = new Page(item.getDisplayName().toLowerCase().replace(" ", ""), false, manualType, new AspectSlideShowWidget(15, 12, ElixirRecipes.getFromEffect(effect).aspects));
            }
            newPages.add(new Page(item.getDisplayName().toLowerCase().replace(" ", ""), widgets, false, manualType).setParent().setLocalizedPageName(item.getDisplayName()).addItem(item));
            if (temp != null)
                newPages.add(temp);
        }

        return newPages;
    }*/

    /**
     * Parses the text container. Used to get the right width and height of the container
     *
     * @param textContainer a unparsed text container
     * @return a pars
     * ed text container
     */
    private static TextContainer parseTextContainer(TextContainer textContainer) {
        textContainer.setCurrentScale(1.0f).setCurrentColor(0x808080);
        textContainer.registerTag(new FormatTags.TagNewLine());
        textContainer.registerTag(new FormatTags.TagScale(1.0F));
        textContainer.registerTag(new FormatTags.TagColor(0x808080));
        textContainer.registerTag(new FormatTags.TagTooltip("N/A"));
        textContainer.registerTag(new FormatTags.TagSimple("bold", ChatFormatting.BOLD));
        textContainer.registerTag(new FormatTags.TagSimple("obfuscated", ChatFormatting.OBFUSCATED));
        textContainer.registerTag(new FormatTags.TagSimple("italic", ChatFormatting.ITALIC));
        textContainer.registerTag(new FormatTags.TagSimple("strikethrough", ChatFormatting.STRIKETHROUGH));
        textContainer.registerTag(new FormatTags.TagSimple("underline", ChatFormatting.UNDERLINE));
        textContainer.registerTag(new FormatTags.TagPagelink());
        textContainer.registerTag(new FormatTags.TagRainbow());
        try {
            textContainer.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textContainer;
    }
}
