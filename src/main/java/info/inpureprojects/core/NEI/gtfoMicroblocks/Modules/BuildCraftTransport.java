package info.inpureprojects.core.NEI.gtfoMicroblocks.Modules;

import codechicken.nei.api.API;
import info.inpureprojects.core.INpureCore;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.item.ItemStack;

@Deprecated
public class BuildCraftTransport extends GtfoFMPModule {
    public BuildCraftTransport(String id) {
        super(id);
    }

    @SuppressWarnings("unchecked")
    public void run() {
        INpureCore.proxy.print("Running all the BC facades off with a broom. (Who uses pipes, seriously?)");
        try {
            LinkedList<ItemStack> facades = (LinkedList<ItemStack>) Class.forName("buildcraft.transport.ItemFacade")
                    .getDeclaredField("allFacades")
                    .get(null);
            API.setItemListEntries(
                    facades.get(0).getItem(), Arrays.asList(facades.get((new Random()).nextInt(facades.size()))));
        } catch (Throwable t) {
            INpureCore.proxy.warning("Failed to hook bc!");
            t.printStackTrace();
        }
    }
}
