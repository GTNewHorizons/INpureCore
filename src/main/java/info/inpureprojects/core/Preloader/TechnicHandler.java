package info.inpureprojects.core.Preloader;

import cpw.mods.fml.common.registry.GameData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class TechnicHandler {
    public static final TechnicHandler instance = new TechnicHandler();

    @SuppressWarnings("unchecked")
    public void reparse(ArrayList<String> list) {
        Set<String> set = new HashSet<String>();
        set.addAll(GameData.getItemRegistry().getKeys());
        set.addAll(GameData.getBlockRegistry().getKeys());
        for (String key : set)
            list.add(key);
    }
}
