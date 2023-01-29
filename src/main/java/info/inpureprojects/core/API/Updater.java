package info.inpureprojects.core.API;

public class Updater {

    public static void register(IUpdateCheck update) {
        try {
            Class.forName("info.inpureprojects.core.INpureCore")
                    .getDeclaredMethod("registerManager", new Class[] { IUpdateCheck.class }).invoke(null, update);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
