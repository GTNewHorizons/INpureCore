package info.inpureprojects.core.Preloader;

import info.inpureprojects.core.API.Events.INpureEventBus.INpureSubscribe;
import info.inpureprojects.core.API.PreloaderAPI;
import info.inpureprojects.core.API.Utils.LogWrapper;
import info.inpureprojects.core.Utils.Events.EventFMLMessage;
import info.inpureprojects.core.Utils.Events.EventNEIReady;
import info.inpureprojects.core.Utils.Loggers.EventFilter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Filter;

public class FMLLogInterceptor {
    public LogWrapper log = new LogWrapper(LogManager.getLogger("INpureLogInterceptor"), null);
    private Logger fmlOriginal;
    private Field myLog;
    private Object relaunch;
    private EventFilter filter;
    private final Set<String> registry = new LinkedHashSet<String>();

    @INpureSubscribe
    public void onFMLMessage(EventFMLMessage evt) {
        if (evt.getLevel().equals(Level.TRACE) && evt.getMessage().contains("Registry add: ")) {

            String copy = evt.getMessage().replace("Registry add: ", "");
            String[] split = copy.split("\\s+");
            copy = split[0];
            if (copy.contains(":")) {
                this.registry.add(copy);
            }
        }
    }

    @INpureSubscribe
    public void onNEIReady(EventNEIReady evt) {
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(this.registry);
        if (list.isEmpty()) {
            this.log.info("Log parsing appears to have failed. Attempting to dig the data out of FML directly...");
            TechnicHandler.instance.reparse(list);
        }
        Collections.sort(list);
        evt.setList(Collections.unmodifiableList(list));
        this.log.info(
                "NEI has entered the ready state. Sending data to culling system. List contains %s entries.",
                Integer.valueOf(this.registry.size()));
    }

    public FMLLogInterceptor setup() {
        this.filter = new EventFilter();
        this.filter.getBus().register(this);
        try {
            Class<?> c = Class.forName("cpw.mods.fml.relauncher.FMLRelaunchLog");
            this.relaunch = c.getDeclaredField("log").get(null);
            this.myLog = this.relaunch.getClass().getDeclaredField("myLog");
            this.myLog.setAccessible(true);
            this.fmlOriginal = (Logger) this.myLog.get(this.relaunch);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        ((org.apache.logging.log4j.core.Logger) this.fmlOriginal).addFilter((Filter) this.filter);
        PreloaderAPI.preLoaderEvents.register(this);
        this.log.info("System attached to FML. Now intercepting all logging calls.");
        return this;
    }

    public void unhook() {
        this.filter.getBus().unregister(this);
        PreloaderAPI.preLoaderEvents.unregister(this);
        this.log.info("System no longer monitoring FML console messages.");
    }
}
