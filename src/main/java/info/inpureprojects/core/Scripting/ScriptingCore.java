package info.inpureprojects.core.Scripting;

import cpw.mods.fml.common.FMLCommonHandler;
import info.inpureprojects.core.API.Events.EventScriptError;
import info.inpureprojects.core.API.Events.INpureEventBus;
import info.inpureprojects.core.API.Scripting.CanBeNull;
import info.inpureprojects.core.API.Scripting.ExposedObject;
import info.inpureprojects.core.API.Scripting.IScriptingCore;
import info.inpureprojects.core.API.Scripting.IScriptingManager;
import info.inpureprojects.core.API.Scripting.Toc.TocManager;
import info.inpureprojects.core.API.Utils.Downloader;
import info.inpureprojects.core.API.Utils.LogWrapper;
import info.inpureprojects.core.API.Utils.Streams;
import info.inpureprojects.core.Client.ScriptModContainer;
import info.inpureprojects.core.Preloader.INpurePreLoader;
import info.inpureprojects.core.Scripting.Objects.Exposed.Console;
import net.minecraftforge.common.config.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import javax.script.ScriptEngine;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class ScriptingCore
    implements IScriptingCore {
    private static final ExposedObject[] bundled = new ExposedObject[]{new ExposedObject("out", new Console())};
    private static final HashMap<String, LangSupport> supported = new HashMap<String, LangSupport>();
    private ScriptEngine engine;
    private ArrayList<TocManager.TableofContents> loaded = new ArrayList<TocManager.TableofContents>();
    private INpureEventBus bus = new INpureEventBus();
    private IScriptingManager.SupportedLanguages lang;
    private Configuration config;
    private LogWrapper logger;

    public ScriptingCore(IScriptingManager.SupportedLanguages lang) {
        this.lang = lang;
    }

    public void initialize(File workingDir, LogWrapper logger) {
        this.logger = logger;
        workingDir.mkdirs();
        for (EnumScripting s : EnumScripting.values()) {
            if (s.toString().equals(this.lang.toString())) {
                if (supported.containsKey(s.toString().toLowerCase())) {
                    LangSupport l = supported.get(s.toString().toLowerCase());
                    load_lang(l.getFileName(), l.getUrl().toString());
                    if (l.isHasSecondaryDep()) {
                        load_lang(l.getSecondaryFileName(), l.getSecondaryUrl().toString());
                    }
                }
                logger.info("Engine: %s", s.toString());
                this.engine = s.getScriptEngine();
                break;
            }
        }
        this.engine.put("workingDir", workingDir);
    }

    private void load_lang(String file, String url) {
        File f = new File(INpurePreLoader.versionFolder, file);
        if (!f.exists()) {
            Downloader.instance.download(url, f);
            INpurePreLoader.forceLoad(f);
        }
    }

    @CanBeNull
    public void exposeObjects(ArrayList<ExposedObject> objects) {
        for (ExposedObject o : bundled) {
            this.engine.put(o.getIdentifier(), o.getObj());
        }
        if (objects != null) {
            for (ExposedObject o : objects) {
                this.engine.put(o.getIdentifier(), o.getObj());
            }
        }
    }

    @CanBeNull
    public void loadPackagesInternal(List<String> list) throws Exception {
        if (list != null) {
            for (String str : list) {
                loadStream(getClass().getClassLoader().getResourceAsStream(str), str);
            }
        }
    }

    @CanBeNull
    public void loadSinglePackageInternal(String scriptFile) throws Exception {
        if (scriptFile != null) {
            loadPackagesInternal(Arrays.asList(scriptFile));
        }
    }

    private void loadStream(InputStream stream, String fileName) throws Exception {
        try {
            for (EnumScripting s : EnumScripting.values()) {
                if (s.isCompatible(fileName)) {
                    String script = s.getHandler().Import(stream);
                    this.engine.eval(script);
                    break;
                }
            }
        } catch (Throwable t) {
            throwScriptError(t);
        }
    }

    private void throwScriptError(Throwable t) {
        this.bus.post(new EventScriptError(t));
    }

    public void loadPackagesFromDir(File dir) {
        try {
            for (File f : FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
                if (!f.isDirectory() &&
                    f.getName().contains(".toc")) {
                    TocManager.TableofContents c = TocManager.instance.read(f);
                    this.logger.info("Loading table of contents for module: %s, %s, Author: %s", c.getTitle(), c.getVersion(), c.getAuthor());
                    FMLCommonHandler.instance().addModToResourcePack(new ScriptModContainer(c, dir, this));
                    getEngine().put(c.getTitle() + "_version", c.getVersion());
                    if (c.getBootstrap() != null) {
                        this.logger.info("Bootstrap setting found. Loading: %s", c.getBootstrap());
                        loadFile(new File(f.getParent() + "/" + c.getBootstrap()));
                    }
                    if (c.getSavedVariables() != null) {
                        this.config = new Configuration(new File(dir, c.getTitle() + ".cfg"));
                        this.config.load();
                        for (String s : c.getSavedVariables()) {
                            if (this.config.hasKey("scripting", s)) {
                                getEngine().put(s, this.config.get("scripting", s, ""));
                                continue;
                            }
                            this.config.get("scripting", s, getEngine().get(s).toString());
                        }

                        this.config.save();
                    }
                    for (String s : c.getScripts()) {
                        this.logger.info("Loading: %s", s);
                        loadFile(new File(f.getParent() + "/" + s));
                        this.loaded.add(c);
                    }

                }
            }
        } catch (Throwable t) {
            throwScriptError(t);
        }
    }

    private void loadFile(File file) {
        try {
            loadStream(Streams.instance.getStream(file), file.getName());
        } catch (Throwable t) {
            throwScriptError(t);
        }
    }

    public void loadScriptFromURL(URL url) {
        InputStream in = null;
        try {
            in = url.openStream();
            this.engine.eval(IOUtils.toString(in));
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public List<TocManager.TableofContents> getLoadedModules() {
        return this.loaded;
    }

    public ScriptEngine getEngine() {
        return this.engine;
    }

    public INpureEventBus getBus() {
        return this.bus;
    }

    public boolean shutdown() {
        this.logger.info("Shutdown request received!");
        this.engine = null;
        this.loaded.clear();
        this.loaded.trimToSize();
        this.loaded = null;
        this.bus = null;
        this.lang = null;
        this.config = null;
        this.logger.info("Shutdown complete!");
        this.logger = null;
        return true;
    }
}
