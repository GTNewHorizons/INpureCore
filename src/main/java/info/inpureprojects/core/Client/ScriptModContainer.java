package info.inpureprojects.core.Client;

import java.io.File;
import java.lang.reflect.Field;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModMetadata;
import info.inpureprojects.core.API.Scripting.IScriptingCore;
import info.inpureprojects.core.API.Scripting.Toc.TocManager;
import info.inpureprojects.core.INpureCore;

public class ScriptModContainer extends DummyModContainer {

    private final File source;
    private final IScriptingCore core;

    public ScriptModContainer(TocManager.TableofContents toc, File source, IScriptingCore core) {
        ModMetadata META = new ModMetadata();
        META.authorList.add(toc.getAuthor());
        META.autogenerated = true;
        META.credits = "";
        META.description = "Fake mod container generated by the INpureCore script engine.";
        META.modId = toc.getTitle();
        META.name = toc.getTitle();
        META.parent = "inpure|core";
        META.parentMod = FMLCommonHandler.instance().findContainerFor(INpureCore.instance);
        META.version = toc.getVersion();

        try {
            Field f = getClass().getSuperclass().getDeclaredField("md");
            f.setAccessible(true);
            f.set(this, META);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        this.source = source;
        this.core = core;
    }

    public File getSource() {
        return this.source;
    }

    public Class<?> getCustomResourcePackClass() {
        return ResourcePackScript.class;
    }

    public IScriptingCore getCore() {
        return this.core;
    }
}
