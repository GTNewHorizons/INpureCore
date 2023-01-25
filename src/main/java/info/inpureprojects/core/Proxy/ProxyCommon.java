package info.inpureprojects.core.Proxy;

import info.inpureprojects.core.API.Events.EventScriptAPIReady;
import info.inpureprojects.core.API.INpureAPI;
import info.inpureprojects.core.API.PreloaderAPI;
import info.inpureprojects.core.API.Scripting.IScriptingCore;
import info.inpureprojects.core.API.Scripting.IScriptingManager;
import info.inpureprojects.core.INpureCore;
import info.inpureprojects.core.Scripting.Dynamic.DynamicFactory;
import info.inpureprojects.core.Scripting.ScriptingCore;

public class ProxyCommon extends Proxy {
    public void warning(String msg) {
        INpureCore.log.warn(msg);
    }

    public void print(String msg) {
        INpureCore.log.info(msg);
    }

    public void severe(String msg) {
        INpureCore.log.bigWarning(msg);
    }

    public void setupAPI() {
        INpureAPI.manager = new IScriptingManager() {
            public IScriptingCore create(IScriptingManager.SupportedLanguages lang) {
                return new ScriptingCore(lang);
            }

            @SuppressWarnings("rawtypes")
            public Object WrapScript(IScriptingCore core, Object obj, Class Interface) {
                return DynamicFactory.instance.create(core, obj, Interface);
            }
        };
        PreloaderAPI.preLoaderEvents.post(new EventScriptAPIReady());
    }

    public void client() {}

    public void sendMessageToPlayer(String msg) {
        print(msg);
    }

    public void onServerStartClient() {}
}
