package info.inpureprojects.core.Scripting;

import java.io.InputStream;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import info.inpureprojects.core.INpureCore;
import info.inpureprojects.core.Preloader.JavaDetection;
import info.inpureprojects.core.Scripting.Objects.JavaScriptCompressor;

public enum EnumScripting {

    JAVASCRIPT(".js", (JavaDetection.detectJava()).JavaScript_Callsign, new jsHandler());

    static {
        m = new ScriptEngineManager(EnumScripting.class.getClassLoader());
    }

    public static ScriptEngineManager m;
    private final handler handler;
    private final String engine;
    private final String extension;

    EnumScripting(String extension, String engine, handler h) {
        this.extension = extension;
        this.engine = engine;
        this.handler = h;
    }

    public String getEngine() {
        return this.engine;
    }

    public handler getHandler() {
        return this.handler;
    }

    public boolean isCompatible(String fileName) {
        return fileName.contains(this.extension);
    }

    public ScriptEngine getScriptEngine() {
        ScriptEngine engine = m.getEngineByName(this.engine);
        if (engine == null) {
            // Try any other engine supporting the given extension
            engine = m.getEngineByExtension(StringUtils.removeStart(extension, "."));
        }
        if (engine == null) {
            INpureCore.log.warn(
                    "Could not find a scripting engine for handling %s files. Available engine names: %s",
                    extension,
                    m.getEngineFactories().stream().map(ScriptEngineFactory::getEngineName)
                            .collect(Collectors.joining(", ")));
        }
        return engine;
    }

    public abstract static class handler {

        public abstract String Import(InputStream param1InputStream);
    }

    public static class jsHandler extends handler {

        public String Import(InputStream stream) {
            try {
                String in = IOUtils.toString(stream);
                String compressed = JavaScriptCompressor.compress(in);
                return compressed;
            } catch (Throwable t) {
                t.printStackTrace();

                return null;
            }
        }
    }
}
