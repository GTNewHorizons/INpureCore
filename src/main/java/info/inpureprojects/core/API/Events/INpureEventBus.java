package info.inpureprojects.core.API.Events;

import info.inpureprojects.core.API.Utils.LogWrapper;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;

public class INpureEventBus {
    private static final LogWrapper log = new LogWrapper(LogManager.getLogger("INpureEventBus"), null);
    private final CopyOnWriteArrayList<Listener> listeners = new CopyOnWriteArrayList<Listener>();

    public void register(Object o) {
        Listener l = new Listener(o);
        for (Method m : o.getClass().getDeclaredMethods()) {
            if (m.getAnnotation(INpureSubscribe.class) != null) {
                if ((m.getParameterTypes()).length > 1) {
                    log.warn("Cannot have an event handler with more than 1 parameter!");
                    return;
                }
                Class<?> eventType = m.getParameterTypes()[0];
                l.handlers.put(eventType, m);
            }
        }
        this.listeners.add(l);
    }

    public void unregister(Object o) {
        this.listeners.remove(o);
    }

    public void post(Object evt) {
        if (!(evt instanceof cpw.mods.fml.common.eventhandler.Event)) {
            log.warn("Cannot post object that does not extend event base class!");
        }
        for (Listener l : this.listeners) {
            l.handleEvent(evt);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface INpureSubscribe {}

    public static class Listener {
        private final Object instance;

        private final HashMap<Class<?>, Method> handlers = new HashMap<>();

        public Listener(Object instance) {
            this.instance = instance;
        }

        public void handleEvent(Object evt) {
            if (this.handlers.containsKey(evt.getClass()))
                try {
                    this.handlers.get(evt.getClass()).invoke(this.instance, evt);
                } catch (Throwable t) {
                    t.printStackTrace();
                }
        }
    }
}
