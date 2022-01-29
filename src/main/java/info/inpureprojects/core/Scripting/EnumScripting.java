package info.inpureprojects.core.Scripting;

import info.inpureprojects.core.Preloader.JavaDetection;
import info.inpureprojects.core.Scripting.Objects.JavaScriptCompressor;
import org.apache.commons.io.IOUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStream;


public enum EnumScripting {
    JAVASCRIPT(".js", (JavaDetection.detectJava()).JavaScript_Callsign, new jsHandler());
    static {
        m = new ScriptEngineManager(null);
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
        return m.getEngineByName(this.engine);
    }

    public static abstract class handler {
        public abstract String Import(InputStream param1InputStream);
    }

    public static class jsHandler
        extends handler {
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
