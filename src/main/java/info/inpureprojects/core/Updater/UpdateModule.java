package info.inpureprojects.core.Updater;

import info.inpureprojects.core.API.IINpureSubmodule;
import info.inpureprojects.core.API.IUpdateCheck;
import info.inpureprojects.core.API.ReleaseLevel;
import info.inpureprojects.core.API.Updater;
import info.inpureprojects.core.modInfo;
import java.io.File;

public class UpdateModule implements IINpureSubmodule, IUpdateCheck {
    public void pre(File configFolder) {
        Updater.register(this);
    }

    public void init() {}

    public void post() {}

    public String getVersion() {
        return "1.7.10R1.0.0B9";
    }

    public String getModId() {
        return "inpure|core";
    }

    public String getUpdateUrl() {
        return "https://raw.githubusercontent.com/INpureProjects/INpureCore/master/VERSION";
    }

    public String getModName() {
        return "INpureCore";
    }

    public ReleaseLevel getLevel() {
        return modInfo.release;
    }
}
