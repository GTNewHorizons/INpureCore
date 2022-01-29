package info.inpureprojects.core.NEI.gtfoMicroblocks;

import codechicken.nei.api.IConfigureNEI;
import codechicken.nei.guihook.GuiContainerManager;
import codechicken.nei.guihook.IContainerTooltipHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;

import java.util.List;


public class NEIINpureTooltipConfig
    implements IConfigureNEI {
    public static boolean tooltips_enabled = false;

    public void loadConfig() {
        GuiContainerManager.addTooltipHandler(new TooltipHandler());
    }


    public String getName() {
        return "INpure Tooltip Tweaks";
    }


    public String getVersion() {
        return "1.7.10R1.0.0B9";
    }

    public static class TooltipHandler
        implements IContainerTooltipHandler {
        public List<String> handleTooltip(GuiContainer guiContainer, int i, int i2, List<String> strings) {
            return strings;
        }


        public List<String> handleItemDisplayName(GuiContainer guiContainer, ItemStack itemStack, List<String> strings) {
            if (NEIINpureTooltipConfig.tooltips_enabled) {
                GameRegistry.UniqueIdentifier i = GameRegistry.findUniqueIdentifierFor(itemStack.getItem());
                strings.add(String.format("%s:%s", i.modId, i.name));
            }
            return strings;
        }


        public List<String> handleItemTooltip(GuiContainer guiContainer, ItemStack itemStack, int i, int i2, List<String> strings) {
            return strings;
        }
    }
}
