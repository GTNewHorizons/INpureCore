package info.inpureprojects.core.Preloader;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class JavaDetection {
    public static VERSION detectJava() {
        for (VERSION v : VERSION.values()) {
            if (v.compare(System.getProperty("java.specification.version"))) {
                return v;
            }
        }
        return VERSION.UNKNOWN;
    }

    public enum VERSION {
        JAVA7("1.7", "JavaScript"),
        JAVA8("1.8", "nashorn"),
        UNKNOWN("Unknown", "JavaScript");
        public String JavaScript_Callsign;
        private final String prop;

        VERSION(String prop, String javaScript_Callsign) {
            this.prop = prop;
            this.JavaScript_Callsign = javaScript_Callsign;
        }

        public boolean compare(String v) {
            return v.equals(this.prop);
        }

        public String getProp() {
            return this.prop;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface injectJSCallsign {
    }
}
