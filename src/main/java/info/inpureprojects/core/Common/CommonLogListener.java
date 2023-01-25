package info.inpureprojects.core.Common;

import info.inpureprojects.core.API.Events.INpureEventBus.INpureSubscribe;
import info.inpureprojects.core.Utils.Events.EventFMLMessage;

public class CommonLogListener {
    @INpureSubscribe
    public void onEvent(EventFMLMessage evt) {
        if (evt.getMessage().contains("keep up")) evt.setCanceled(true);
    }
}
