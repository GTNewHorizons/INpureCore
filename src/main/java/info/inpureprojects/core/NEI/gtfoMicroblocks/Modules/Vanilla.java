package info.inpureprojects.core.NEI.gtfoMicroblocks.Modules;

import info.inpureprojects.core.NEI.gtfoMicroblocks.IGtfoModule;
import info.inpureprojects.core.NEI.gtfoMicroblocks.NEIINpureConfig;
import net.minecraft.init.Blocks;

@Deprecated
public class Vanilla implements IGtfoModule {
    public void run() {
        NEIINpureConfig.hideBlock(Blocks.mob_spawner);
        NEIINpureConfig.hideBlock(Blocks.water);
        NEIINpureConfig.hideBlock(Blocks.lava);
        NEIINpureConfig.hideBlock(Blocks.portal);
        NEIINpureConfig.hideBlock(Blocks.end_portal);
        NEIINpureConfig.hideBlock(Blocks.fire);
    }
}
