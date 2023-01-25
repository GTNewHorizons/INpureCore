package info.inpureprojects.core.NEI.gtfoMicroblocks.ScriptObjects;

import codechicken.nei.api.API;
import info.inpureprojects.core.INpureCore;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import java.util.AbstractList;
import java.util.Arrays;
import net.minecraft.item.ItemStack;

public class BCObject {
    public BCObject() {
        NEIINpureConfig.logger.debug("Setting up BuildCraft Library...");
    }

    @SuppressWarnings("unchecked")
    public void obliterate_facades(int index) {
        NEIINpureConfig.logger.debug(
                "obliterate_microblocks called (version in %s). Params: %s",
                getClass().getName(), String.valueOf(index));
        try {
            AbstractList<ItemStack> facades = (AbstractList<ItemStack>) Class.forName("buildcraft.transport.ItemFacade")
                    .getDeclaredField("allFacades")
                    .get(null);
            API.setItemListEntries(facades.get(0).getItem(), Arrays.asList(facades.get(index)));
        } catch (Throwable t) {
            INpureCore.proxy.warning("Failed to hook bc!");
            t.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public int getFacadesSize() {
        try {
            AbstractList<ItemStack> facades = (AbstractList<ItemStack>) Class.forName("buildcraft.transport.ItemFacade")
                    .getDeclaredField("allFacades")
                    .get(null);
            NEIINpureConfig.logger.debug("getFacadesSize called. Returned: %s", String.valueOf(facades.size()));
            return facades.size();
        } catch (Throwable t) {
            t.printStackTrace();

            return 0;
        }
    }
}
