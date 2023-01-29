package info.inpureprojects.core.NEI.gtfoMicroblocks.Modules;

import info.inpureprojects.core.NEI.gtfoMicroblocks.IGtfoModule;

@Deprecated
public abstract class GtfoFMPModule implements IGtfoModule {

    public String id;

    protected GtfoFMPModule(String id) {
        this.id = id;
    }

    public abstract void run();
}
