package info.inpureprojects.core.NEI.gtfoMicroblocks.Modules;

import codechicken.nei.api.API;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.core.INpureCore;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Deprecated
public class ExtraUtilities extends GtfoFMPModule {
    public ExtraUtilities(String id) {
        super(id);
    }

    public void run() {
        INpureCore.proxy.print("Burying ExtraUtilities microblocks in the back yard...");
        Item i = GameRegistry.findItem("ExtraUtilities", "microblocks");
        ItemStack pipeJacket = new ItemStack(i, 1, 0);
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        for (ItemStack s : NEIINpureConfig.buildStackList(pipeJacket, new int[] {1, 2, 3})) {
            try {
                stacks.add((ItemStack) i.getClass()
                        .getDeclaredMethod("getStack", new Class[] {ItemStack.class, String.class})
                        .invoke(null, new Object[] {s, this.id}));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        API.setItemListEntries(i, stacks);
        Block drum = GameRegistry.findBlock("ExtraUtilities", "drum");
        ItemStack d = new ItemStack(drum, 1, 0);
        API.setItemListEntries(d.getItem(), NEIINpureConfig.buildStackList(d, new int[] {0, 1}));
    }
}
