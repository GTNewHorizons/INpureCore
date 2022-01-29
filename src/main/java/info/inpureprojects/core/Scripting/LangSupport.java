package info.inpureprojects.core.Scripting;

import java.net.URL;


public class LangSupport {
    private final String[] fileName = new String[2];
    private final URL[] urls = new URL[2];
    private boolean hasSecondaryDep = false;

    public LangSupport(String fileName, String url) {
        this.fileName[0] = fileName;
        try {
            this.urls[0] = new URL(url);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String getFileName() {
        return this.fileName[0];
    }

    public URL getUrl() {
        return this.urls[0];
    }

    public URL getSecondaryUrl() {
        return this.urls[1];
    }

    public LangSupport setSecondaryURL(String url) {
        try {
            this.urls[1] = new URL(url);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        this.hasSecondaryDep = true;
        return this;
    }

    public String getSecondaryFileName() {
        return this.fileName[1];
    }

    public LangSupport setSecondaryFileName(String secondaryFileName) {
        this.fileName[1] = secondaryFileName;
        this.hasSecondaryDep = true;
        return this;
    }

    public boolean isHasSecondaryDep() {
        return this.hasSecondaryDep;
    }
}
