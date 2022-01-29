package info.inpureprojects.core.NEI.gtfoMicroblocks.Modules;

import codechicken.nei.api.API;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.core.INpureCore;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;


@Deprecated
public class ForgeMicroblock
    extends GtfoFMPModule {
    public ForgeMicroblock(String id) {
        super(id);
    }


    public void run() {
        INpureCore.proxy.print("Sweeping ForgeMicroblocks under the nearest rug...");
        Item i = GameRegistry.findItem("ForgeMicroblock", "microblock");
        ItemStack hideMe = new ItemStack(i, 1, 0);
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        for (ItemStack s : NEIINpureConfig.buildStackList(hideMe, new int[]{1, 2, 4, 257, 258, 260, 513, 514, 516, 769, 770, 772})) {
            try {
                stacks.add((ItemStack) i.getClass().getDeclaredMethod("create", new Class[]{int.class, String.class}).invoke(null, new Object[]{s.getItemDamage(), this.id}));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        API.setItemListEntries(i, stacks);
    }
}
