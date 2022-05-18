package info.inpureprojects.core.NEI.gtfoMicroblocks.ScriptObjects;

import appeng.api.AEApi;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Field;
import java.util.List;


public class AEObject {
    public AEObject() {
        NEIINpureConfig.logger.debug("Setting up Applied Energistics 2 Library...");
    }

    public int getNumberOfTypes() {
        int i = getSubTypes().size();
        NEIINpureConfig.logger.debug("getNumberOfTypes called. Returned: %s", String.valueOf(i));
        return i;
    }

    @SuppressWarnings("unchecked")
    public List<ItemStack> getSubTypes() {
        NEIINpureConfig.logger.debug("getSubTypes called.");
        try {
            Field f = AEApi.instance().definitions().items().facade().maybeItem().get().getClass().getDeclaredField("subTypes");
            f.setAccessible(true);
            List<ItemStack> list = (List<ItemStack>) f.get((AEApi.instance().definitions().items()).facade().maybeItem().get());
            return list;
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public String getFacadeItem() {
        NEIObject.UniqueIDSettable id = new NEIObject.UniqueIDSettable(GameRegistry.findUniqueIdentifierFor(AEApi.instance().definitions().items().facade().maybeItem().get()));
        NEIINpureConfig.logger.debug("getFacadeItem called. Returned: %s:%s", id.getModId(), id.getName());
        return String.format("%s:%s", id.getModId(), id.getName());
    }
}
