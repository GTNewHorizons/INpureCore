package info.inpureprojects.core.Scripting.Dynamic;

import info.inpureprojects.core.API.Scripting.IScriptingCore;

import javax.script.Invocable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class DynamicFactory {
    public static final DynamicFactory instance = new DynamicFactory();

    public Object create(IScriptingCore core, Object obj, Class Interface) {
        try {
            DynamicHandler h = new DynamicHandler(core, obj);
            return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{Interface}, h);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public static class DynamicHandler implements InvocationHandler {
        private final IScriptingCore core;
        private final Object scriptClass;

        public DynamicHandler(IScriptingCore core, Object scriptClass) {
            this.core = core;
            this.scriptClass = scriptClass;
        }


        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                if (this.core.getEngine() instanceof Invocable) {
                    Invocable invoc = (Invocable) this.core.getEngine();
                    return invoc.invokeMethod(this.scriptClass, method.getName(), args);
                }
            } catch (Throwable t) {
            }

            return null;
        }
    }

    public static abstract class specialHandler {
        public abstract void handle(Object param1Object, String param1String, Object[] param1ArrayOfObject);

        public abstract boolean isObjectCompatible(Object param1Object);
    }
}
