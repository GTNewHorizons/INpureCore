package info.inpureprojects.core.NEI.gtfoMicroblocks.Modules;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.item.ItemStack;

import appeng.api.AEApi;
import codechicken.nei.api.API;
import info.inpureprojects.core.INpureCore;

@Deprecated
public class appliedenergistics2 extends GtfoFMPModule {

    public appliedenergistics2(String id) {
        super(id);
    }

    @SuppressWarnings("unchecked")
    public void run() {
        INpureCore.proxy
                .print("Oh look, more facades. Did we really need another version of these? (Hiding AE facades)");
        try {
            Field f = (AEApi.instance().items()).itemFacade.item().getClass().getDeclaredField("subTypes");
            f.setAccessible(true);
            List<ItemStack> list = (List<ItemStack>) f.get((AEApi.instance().items()).itemFacade.item());
            API.setItemListEntries(
                    (AEApi.instance().items()).itemFacade.item(),
                    Arrays.asList(list.get((new Random()).nextInt(list.size()))));
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
