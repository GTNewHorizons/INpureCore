package info.inpureprojects.core.Preloader.DepHandler;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class INpureDepHandler {
    public ArrayList<String> readStream(InputStream s) {
        try {
            List<String> in = IOUtils.readLines(s);
            String url = "";
            ArrayList<String> deps = new ArrayList<String>();
            for (String read : in) {
                if (read.contains("##url=")) {
                    url = read.split("=")[1].trim().replaceAll("\\s", "");
                    continue;
                }
                deps.add(url + read);
            }

            s.close();
            return deps;
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }
}
