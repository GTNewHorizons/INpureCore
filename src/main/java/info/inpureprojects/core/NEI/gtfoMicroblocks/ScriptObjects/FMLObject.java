package info.inpureprojects.core.NEI.gtfoMicroblocks.ScriptObjects;

import cpw.mods.fml.common.Loader;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;


public class FMLObject {
    public FMLObject() {
        NEIINpureConfig.logger.debug("Setting up FML Library...");
    }

    public boolean isModLoaded(String modid) {
        return Loader.isModLoaded(modid);
    }
}
