package info.inpureprojects.core.API.Events;

import cpw.mods.fml.common.eventhandler.Event;

public class EventScriptError extends Event {
    private final Throwable t;

    public EventScriptError(Throwable t) {
        this.t = t;
    }

    public Throwable getT() {
        return this.t;
    }
}
