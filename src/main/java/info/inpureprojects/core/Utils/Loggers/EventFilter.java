package info.inpureprojects.core.Utils.Loggers;

import info.inpureprojects.core.API.Events.INpureEventBus;
import info.inpureprojects.core.Utils.Events.EventFMLMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

public class EventFilter implements Filter {
    private final INpureEventBus bus = new INpureEventBus();

    public Filter.Result getOnMismatch() {
        return Filter.Result.NEUTRAL;
    }

    public Filter.Result getOnMatch() {
        return Filter.Result.NEUTRAL;
    }

    public Filter.Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
        return Filter.Result.NEUTRAL;
    }

    public Filter.Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
        return Filter.Result.NEUTRAL;
    }

    public Filter.Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
        return Filter.Result.NEUTRAL;
    }

    public Filter.Result filter(LogEvent event) {
        EventFMLMessage evt =
                new EventFMLMessage(event.getLevel(), event.getMessage().getFormattedMessage());
        getBus().post(evt);
        if (evt.isCanceled()) {
            return Filter.Result.DENY;
        }
        return Filter.Result.NEUTRAL;
    }

    public INpureEventBus getBus() {
        return this.bus;
    }
}
