package info.inpureprojects.core.Preloader;

import info.inpureprojects.core.API.IINpureSubmoduleExpanded;

import java.io.File;


public class ModulePreloader
    implements IINpureSubmoduleExpanded {
    public void onServerAboutToStart() {
        INpurePreLoader.fmlLogInterceptor.unhook();
    }

    public void pre(File configFolder) {
    }

    public void init() {
    }

    public void post() {
    }
}
