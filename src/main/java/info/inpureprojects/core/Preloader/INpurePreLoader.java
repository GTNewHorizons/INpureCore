package info.inpureprojects.core.Preloader;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModClassLoader;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.MCVersion;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.Name;
import info.inpureprojects.core.API.PreloaderAPI;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MCVersion("1.7.10")
@Name("INpurePreLoader")
public class INpurePreLoader implements IFMLLoadingPlugin {
    public static boolean isDev;
    public static File mc;
    public static File source;
    public static File versionFolder;
    public static FMLLogInterceptor fmlLogInterceptor;
    private static final Logger log = LogManager.getLogger("INpurePreloader");

    public static void forceLoad(File file) {
        try {
            Field mLoader = Class.forName("cpw.mods.fml.common.Loader").getDeclaredField("modClassLoader");
            mLoader.setAccessible(true);
            ModClassLoader loader = (ModClassLoader) mLoader.get(Loader.instance());
            loader.addFile(file);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        print("Attempting to detect JVM version...");
        print("Detected JVM: " + JavaDetection.detectJava().getProp());
        if (!((Boolean) data.get("runtimeDeobfuscationEnabled"))) {
            print("We are in dev mode!");
        }
        source = (File) data.get("coremodLocation");
        PreloaderAPI.modules = new ModuleManager();
        fmlLogInterceptor = (new FMLLogInterceptor()).setup();
        PreloaderAPI.modules.register("info.inpureprojects.core.Preloader.ModulePreloader");
    }

    public static void print(String msg) {
        log.info(msg);
    }

    public String getAccessTransformerClass() {
        return null;
    }
}
