package info.inpureprojects.core;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import info.inpureprojects.core.API.Events.EventPreloaderRegister;
import info.inpureprojects.core.API.Events.INpureEventBus.INpureSubscribe;
import info.inpureprojects.core.API.IINpureSubmodule;
import info.inpureprojects.core.API.IINpureSubmoduleExpanded;
import info.inpureprojects.core.API.IUpdateCheck;
import info.inpureprojects.core.API.PreloaderAPI;
import info.inpureprojects.core.API.Utils.LogWrapper;
import info.inpureprojects.core.Config.PropertiesHolder;
import info.inpureprojects.core.NEI.gtfoMicroblocks.Commands.CommandReload;
import info.inpureprojects.core.Preloader.ModuleManager;
import info.inpureprojects.core.Proxy.Proxy;
import info.inpureprojects.core.Updater.UpdateManager;
import net.minecraft.command.ICommand;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;

@Mod(modid = "inpure|core", name = "INpureCore", version = "1.7.10R1.0.0B9", dependencies = "after:ExtraUtilities")
public class INpureCore {
    @Instance("inpure|core")
    public static INpureCore instance;
    public static ArrayList<IINpureSubmodule> modules = new ArrayList<IINpureSubmodule>();
    @SidedProxy(clientSide = "info.inpureprojects.core.Proxy.ProxyClient", serverSide = "info.inpureprojects.core.Proxy.ProxyCommon")
    public static Proxy proxy;
    public static PropertiesHolder properties;
    public static LogWrapper log;
    public static ArrayList<UpdateManager> managers = new ArrayList<UpdateManager>();
    public static File dir;

    public static void registerManager(IUpdateCheck check) {
        managers.add(new UpdateManager(check));
    }

    @EventHandler
    public void preinit(FMLPreInitializationEvent evt) {
        PreloaderAPI.preLoaderEvents.register(this);
        log = new LogWrapper(evt.getModLog(), null);
        properties = new PropertiesHolder(
            new Configuration(new File(new File(evt.getModConfigurationDirectory(), "INpureProjects/INpureCore"), "INpureCore.cfg")));
        proxy.client();
        proxy.setupAPI();
        PreloaderAPI.preLoaderEvents.post(new EventPreloaderRegister());
        for (String s : ModuleManager.modules) {
            if (!properties.silence_submodule_logging) {
                proxy.print("Constructing submodule " + s);
            }
            try {
                IINpureSubmodule m = (IINpureSubmodule) Class.forName(s).newInstance();
                modules.add(m);
            } catch (Throwable t) {
                proxy.severe("Failed to load submodule " + s + "!");
                t.printStackTrace();
            }
        }
        dir = new File(evt.getModConfigurationDirectory(), "INpureProjects");
        for (IINpureSubmodule s : modules) {
            if (!properties.silence_submodule_logging) {
                proxy.print("Processing preinit event for submodule " + s.getClass().getName());
            }
            s.pre(dir);
        }
    }

    @INpureSubscribe
    public void registerModules(EventPreloaderRegister evt) {
        if (Loader.isModLoaded("inpure|core") && properties.updateCheck) {
            PreloaderAPI.modules.register("info.inpureprojects.core.Updater.UpdateModule");
        }
        if (properties.extract_scripts) {
            PreloaderAPI.modules.register("info.inpureprojects.core.Scripting.ScriptExtractor");
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent evt) {
        for (IINpureSubmodule s : modules) {
            if (!properties.silence_submodule_logging) {
                proxy.print("Processing init event for submodule " + s.getClass().getName());
            }
            s.init();
        }
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent evt) {
        for (IINpureSubmodule s : modules) {
            if (!properties.silence_submodule_logging) {
                proxy.print("Processing postinit event for submodule " + s.getClass().getName());
            }
            s.post();
        }
        for (UpdateManager m : managers) {
            m.runCheck();
        }
    }

    @EventHandler
    public void onServer(FMLServerAboutToStartEvent evt) {
        proxy.onServerStartClient();
        for (IINpureSubmodule s : modules) {
            if (s instanceof IINpureSubmoduleExpanded) {
                if (!properties.silence_submodule_logging) {
                    proxy.print("Processing ServerAboutToStart event for submodule " + s.getClass().getName());
                }
                ((IINpureSubmoduleExpanded) s).onServerAboutToStart();
            }
        }
    }

    @EventHandler
    public void onServerStart(FMLServerStartingEvent evt) {
        evt.registerServerCommand(new CommandReload());
    }
}
