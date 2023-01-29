package info.inpureprojects.core.API.Utils;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.compress.utils.IOUtils;

public class Streams {

    public static final Streams instance = new Streams();

    public PrintWriter getFilePrintWriter(File f) {
        try {
            return new PrintWriter(f);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public Reader getFileReader(File f) {
        try {
            return new FileReader(f);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public Reader getReader(InputStream in) {
        try {
            return new InputStreamReader(in);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public Writer getWriter(File f) {
        try {
            return new FileWriter(f);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public OutputStream getStreamOut(File f) {
        try {
            return new FileOutputStream(f);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public void IO(InputStream i, OutputStream o) {
        try {
            IOUtils.copy(i, o);
            close(i);
            close(o);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public void close(Closeable c) {
        try {
            c.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public InputStream getByteStream(byte[] bytes) {
        try {
            return new ByteArrayInputStream(bytes);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public byte[] getBytesFromFile(File f) {
        return getBytes(getStream(f));
    }

    public byte[] getBytes(InputStream in) {
        try {
            return IOUtils.toByteArray(in);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }

    public InputStream getStream(File f) {
        try {
            return new FileInputStream(f);
        } catch (Throwable t) {
            t.printStackTrace();

            return null;
        }
    }
}
