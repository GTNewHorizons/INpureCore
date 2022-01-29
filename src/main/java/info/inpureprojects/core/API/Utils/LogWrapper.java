package info.inpureprojects.core.API.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class LogWrapper {
    private final Logger log;
    private final File debug;

    public LogWrapper(Logger log, File debug) {
        this.log = log;
        this.debug = debug;
        if (this.debug != null &&
            this.debug.exists()) {
            this.debug.delete();
        }
    }


    public Logger getLog() {
        return this.log;
    }

    public String IntArrayToString(int[] array) {
        String s = "";
        for (int i : array) {
            s = s + String.format("%s,", Integer.valueOf(i));
        }
        return s.substring(0, s.length() - 1);
    }

    public void info(String msg) {
        this.log.info(msg);
        writeToLog(String.format(msg));
    }

    private void writeToLog(String msg) {
        try {
            if (this.debug == null) {
                return;
            }
            FileUtils.writeStringToFile(this.debug, msg.concat(SystemUtils.LINE_SEPARATOR), true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void info(String msg, Object... data) {
        this.log.log(Level.INFO, String.format(msg, data));
        writeToLog(String.format(msg, data));
    }

    public void warn(String msg) {
        this.log.warn(msg);
        writeToLog(String.format(msg));
    }

    public void warn(String msg, Object... data) {
        this.log.log(Level.WARN, String.format(msg, data));
        writeToLog(String.format(msg, data));
    }

    public void debug(String msg) {
        this.log.debug(msg);
        writeToLog(msg);
    }

    public void debug(String msg, Object... data) {
        this.log.log(Level.DEBUG, String.format(msg, data));
        writeToLog(String.format(msg, data));
    }

    public void bigWarning(String msg) {
        this.log.warn("--------------------------");
        this.log.warn(String.format(msg));
        this.log.warn("--------------------------");
        writeToLog("--------------------------");
        writeToLog(String.format(msg));
        writeToLog("--------------------------");
    }

    public void bigWarning(String msg, Object... data) {
        this.log.warn("--------------------------");
        this.log.warn(String.format(msg, data));
        this.log.warn("--------------------------");
        writeToLog("--------------------------");
        writeToLog(String.format(msg, data));
        writeToLog("--------------------------");
    }
}
