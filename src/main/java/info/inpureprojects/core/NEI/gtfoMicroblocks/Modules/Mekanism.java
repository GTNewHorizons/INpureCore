package info.inpureprojects.core.NEI.gtfoMicroblocks.Modules;

import codechicken.nei.api.API;
import info.inpureprojects.core.INpureCore;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

@Deprecated
public class Mekanism extends GtfoFMPModule {
    public Mekanism(String id) {
        super(id);
    }

    public void run() {
        INpureCore.proxy.print("Gas tanks!");
        try {
            Block b = (Block) Class.forName("mekanism.common.Mekanism")
                    .getDeclaredField("GasTank")
                    .get(null);
            ItemStack gasTank = new ItemStack(b);
            API.setItemListEntries(gasTank.getItem(), NEIINpureConfig.buildStackList(gasTank, new int[] {100}));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
