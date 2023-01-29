package info.inpureprojects.core.Preloader;

import java.io.File;

import info.inpureprojects.core.API.IINpureSubmoduleExpanded;

public class ModulePreloader implements IINpureSubmoduleExpanded {

    public void onServerAboutToStart() {
        INpurePreLoader.fmlLogInterceptor.unhook();
    }

    public void pre(File configFolder) {}

    public void init() {}

    public void post() {}
}
