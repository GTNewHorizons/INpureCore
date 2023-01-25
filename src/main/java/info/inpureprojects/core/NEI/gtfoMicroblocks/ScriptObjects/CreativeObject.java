package info.inpureprojects.core.NEI.gtfoMicroblocks.ScriptObjects;

import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeObject {
    private final LinkedHashMap<String, CreativeTabs> tabs = new LinkedHashMap<String, CreativeTabs>();

    public CreativeObject() {
        NEIINpureConfig.logger.debug("Setting up CreativeTab library...");
        for (String s : NEIINpureConfig.reg) {
            NEIObject.UniqueIDSettable id = NEIObject.getUniqueID(s);
            ItemStack i = null;
            try {
                i = GameRegistry.findItemStack(id.getModId(), id.getName(), 1);
            } catch (Throwable t) {
                continue;
            }
            if (i != null) {
                CreativeTabs t = i.getItem().getCreativeTab();
                if (t != null) {
                    this.tabs.put(t.getTabLabel(), t);
                }
            }
        }
        for (CreativeTabs t : this.tabs.values()) {
            NEIINpureConfig.logger.debug("Found creative tab: " + t.getTabLabel());
        }
    }

    public String[] getTabList() {
        ArrayList<String> list = new ArrayList<String>();
        Collections.sort(list);
        return list.toArray(new String[list.size()]);
    }

    public void set_tab(String domain, String tab) {
        ItemStack i = NEIINpureConfig.NEILib.getStack(domain);
        if (this.tabs.get(tab) != null && i != null) {
            i.getItem().setCreativeTab(this.tabs.get(tab));
        }
    }

    public void remove_tab(String domain) {
        ItemStack i = NEIINpureConfig.NEILib.getStack(domain);
        if (i == null) {
            return;
        }
        i.getItem().setCreativeTab(null);
    }
}
