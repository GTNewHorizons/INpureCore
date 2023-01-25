package info.inpureprojects.core.NEI.gtfoMicroblocks.ScriptObjects;

import codechicken.microblock.MicroMaterialRegistry;
import codechicken.nei.api.API;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ForgeMicroblockObject {
    public ForgeMicroblockObject() {
        NEIINpureConfig.logger.debug("Setting up ForgeMicroblock Library...");
    }

    public String getRandomMaterial() {
        try {
            String m = MicroMaterialRegistry.materialName((new Random()).nextInt(20));
            NEIINpureConfig.logger.debug("getRandomMaterial called. Returned: %s", m);
            return m;
        } catch (Throwable t) {
            return "";
        }
    }

    public void obliterate_microblocks(int[] metas, String id) {
        NEIINpureConfig.logger.debug(
                "obliterate_microblocks called (version in %s). Params: %s, %s",
                getClass().getName(), NEIINpureConfig.logger.IntArrayToString(metas), id);
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        Item i = GameRegistry.findItem("ForgeMicroblock", "microblock");
        for (ItemStack s : NEIINpureConfig.buildStackList(new ItemStack(i), metas)) {
            try {
                stacks.add((ItemStack) i.getClass()
                        .getDeclaredMethod("create", new Class[] {int.class, String.class})
                        .invoke(null, new Object[] {s.getItemDamage(), id}));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        API.setItemListEntries(i, stacks);
    }
}
