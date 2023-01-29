package info.inpureprojects.core.Utils.Events;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.eventhandler.Event;

public class EventFMLMessage extends Event {

    private Level level;
    private String message;

    public EventFMLMessage(Level level, String message) {
        this.level = level;
        this.message = message;
    }

    public Level getLevel() {
        return this.level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCancelable() {
        return true;
    }
}
