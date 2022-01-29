package info.inpureprojects.core.API.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;


public class Downloader {
    public static final Downloader instance = new Downloader();

    public void download(String url, File f) {
        try {
            URL download = new URL(url);
            if (!f.exists()) {
                System.out.println("Downloading: " + url);
                FileUtils.copyURLToFile(download, f);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
