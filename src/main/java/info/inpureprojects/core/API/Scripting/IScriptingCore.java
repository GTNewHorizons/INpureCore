package info.inpureprojects.core.API.Scripting;

import info.inpureprojects.core.API.Events.INpureEventBus;
import info.inpureprojects.core.API.Scripting.Toc.TocManager;
import info.inpureprojects.core.API.Utils.LogWrapper;

import javax.script.ScriptEngine;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public interface IScriptingCore {
    void initialize(File paramFile, LogWrapper paramLogWrapper);

    @CanBeNull
    void exposeObjects(ArrayList<ExposedObject> paramArrayList);

    @CanBeNull
    void loadPackagesInternal(List<String> paramList) throws Exception;

    @CanBeNull
    void loadSinglePackageInternal(String paramString) throws Exception;

    void loadPackagesFromDir(File paramFile);

    void loadScriptFromURL(URL paramURL);

    List<TocManager.TableofContents> getLoadedModules();

    ScriptEngine getEngine();

    INpureEventBus getBus();

    boolean shutdown();
}
