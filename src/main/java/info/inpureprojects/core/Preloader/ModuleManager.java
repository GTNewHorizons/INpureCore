package info.inpureprojects.core.Preloader;

import info.inpureprojects.core.API.IModuleManager;
import java.util.ArrayList;
import java.util.Arrays;

public class ModuleManager implements IModuleManager {
    public static ArrayList<String> modules = new ArrayList<String>();

    public void register(String clazz) {
        modules.add(clazz);
    }

    public void registerAll(String[] clazzes) {
        modules.addAll(Arrays.asList(clazzes));
    }
}
