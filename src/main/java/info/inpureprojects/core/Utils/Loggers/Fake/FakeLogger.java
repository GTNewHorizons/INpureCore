package info.inpureprojects.core.Utils.Loggers.Fake;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;

@Deprecated
public class FakeLogger implements Logger {

    public void catching(Level level, Throwable t) {}

    public void catching(Throwable t) {}

    public void debug(Marker marker, Message msg) {}

    public void debug(Marker marker, Message msg, Throwable t) {}

    public void debug(Marker marker, Object message) {}

    public void debug(Marker marker, Object message, Throwable t) {}

    public void debug(Marker marker, String message) {}

    public void debug(Marker marker, String message, Object... params) {}

    public void debug(Marker marker, String message, Throwable t) {}

    public void debug(Message msg) {}

    public void debug(Message msg, Throwable t) {}

    public void debug(Object message) {}

    public void debug(Object message, Throwable t) {}

    public void debug(String message) {}

    public void debug(String message, Object... params) {}

    public void debug(String message, Throwable t) {}

    public void entry() {}

    public void entry(Object... params) {}

    public void error(Marker marker, Message msg) {}

    public void error(Marker marker, Message msg, Throwable t) {}

    public void error(Marker marker, Object message) {}

    public void error(Marker marker, Object message, Throwable t) {}

    public void error(Marker marker, String message) {}

    public void error(Marker marker, String message, Object... params) {}

    public void error(Marker marker, String message, Throwable t) {}

    public void error(Message msg) {}

    public void error(Message msg, Throwable t) {}

    public void error(Object message) {}

    public void error(Object message, Throwable t) {}

    public void error(String message) {}

    public void error(String message, Object... params) {}

    public void error(String message, Throwable t) {}

    public void exit() {}

    public <R> R exit(R result) {
        return null;
    }

    public void fatal(Marker marker, Message msg) {}

    public void fatal(Marker marker, Message msg, Throwable t) {}

    public void fatal(Marker marker, Object message) {}

    public void fatal(Marker marker, Object message, Throwable t) {}

    public void fatal(Marker marker, String message) {}

    public void fatal(Marker marker, String message, Object... params) {}

    public void fatal(Marker marker, String message, Throwable t) {}

    public void fatal(Message msg) {}

    public void fatal(Message msg, Throwable t) {}

    public void fatal(Object message) {}

    public void fatal(Object message, Throwable t) {}

    public void fatal(String message) {}

    public void fatal(String message, Object... params) {}

    public void fatal(String message, Throwable t) {}

    public MessageFactory getMessageFactory() {
        return null;
    }

    public String getName() {
        return null;
    }

    public void info(Marker marker, Message msg) {}

    public void info(Marker marker, Message msg, Throwable t) {}

    public void info(Marker marker, Object message) {}

    public void info(Marker marker, Object message, Throwable t) {}

    public void info(Marker marker, String message) {}

    public void info(Marker marker, String message, Object... params) {}

    public void info(Marker marker, String message, Throwable t) {}

    public void info(Message msg) {}

    public void info(Message msg, Throwable t) {}

    public void info(Object message) {}

    public void info(Object message, Throwable t) {}

    public void info(String message) {}

    public void info(String message, Object... params) {}

    public void info(String message, Throwable t) {}

    public boolean isDebugEnabled() {
        return false;
    }

    public boolean isDebugEnabled(Marker marker) {
        return false;
    }

    public boolean isEnabled(Level level) {
        return false;
    }

    public boolean isEnabled(Level level, Marker marker) {
        return false;
    }

    public boolean isErrorEnabled() {
        return false;
    }

    public boolean isErrorEnabled(Marker marker) {
        return false;
    }

    public boolean isFatalEnabled() {
        return false;
    }

    public boolean isFatalEnabled(Marker marker) {
        return false;
    }

    public boolean isInfoEnabled() {
        return false;
    }

    public boolean isInfoEnabled(Marker marker) {
        return false;
    }

    public boolean isTraceEnabled() {
        return false;
    }

    public boolean isTraceEnabled(Marker marker) {
        return false;
    }

    public boolean isWarnEnabled() {
        return false;
    }

    public boolean isWarnEnabled(Marker marker) {
        return false;
    }

    public void log(Level level, Marker marker, Message msg) {}

    public void log(Level level, Marker marker, Message msg, Throwable t) {}

    public void log(Level level, Marker marker, Object message) {}

    public void log(Level level, Marker marker, Object message, Throwable t) {}

    public void log(Level level, Marker marker, String message) {}

    public void log(Level level, Marker marker, String message, Object... params) {}

    public void log(Level level, Marker marker, String message, Throwable t) {}

    public void log(Level level, Message msg) {}

    public void log(Level level, Message msg, Throwable t) {}

    public void log(Level level, Object message) {}

    public void log(Level level, Object message, Throwable t) {}

    public void log(Level level, String message) {}

    public void log(Level level, String message, Object... params) {}

    public void log(Level level, String message, Throwable t) {}

    public void printf(Level level, Marker marker, String format, Object... params) {}

    public void printf(Level level, String format, Object... params) {}

    public <T extends Throwable> T throwing(Level level, T t) {
        return null;
    }

    public <T extends Throwable> T throwing(T t) {
        return null;
    }

    public void trace(Marker marker, Message msg) {}

    public void trace(Marker marker, Message msg, Throwable t) {}

    public void trace(Marker marker, Object message) {}

    public void trace(Marker marker, Object message, Throwable t) {}

    public void trace(Marker marker, String message) {}

    public void trace(Marker marker, String message, Object... params) {}

    public void trace(Marker marker, String message, Throwable t) {}

    public void trace(Message msg) {}

    public void trace(Message msg, Throwable t) {}

    public void trace(Object message) {}

    public void trace(Object message, Throwable t) {}

    public void trace(String message) {}

    public void trace(String message, Object... params) {}

    public void trace(String message, Throwable t) {}

    public void warn(Marker marker, Message msg) {}

    public void warn(Marker marker, Message msg, Throwable t) {}

    public void warn(Marker marker, Object message) {}

    public void warn(Marker marker, Object message, Throwable t) {}

    public void warn(Marker marker, String message) {}

    public void warn(Marker marker, String message, Object... params) {}

    public void warn(Marker marker, String message, Throwable t) {}

    public void warn(Message msg) {}

    public void warn(Message msg, Throwable t) {}

    public void warn(Object message) {}

    public void warn(Object message, Throwable t) {}

    public void warn(String message) {}

    public void warn(String message, Object... params) {}

    public void warn(String message, Throwable t) {}
}
