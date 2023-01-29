package info.inpureprojects.core.Client;

import info.inpureprojects.core.API.Events.INpureEventBus.INpureSubscribe;
import info.inpureprojects.core.Utils.Events.EventFMLMessage;

public class ClientLogListener {

    @INpureSubscribe
    public void onEvent(EventFMLMessage evt) {
        evt.setCanceled(true);
    }
}
