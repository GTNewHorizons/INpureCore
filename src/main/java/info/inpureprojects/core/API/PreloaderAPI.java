package info.inpureprojects.core.API;

import info.inpureprojects.core.API.Events.INpureEventBus;


public class PreloaderAPI {
    public static IModuleManager modules;
    public static INpureEventBus preLoaderEvents = new INpureEventBus();

    public static boolean isDev() {
        try {
            return ((Boolean) Class.forName("info.inpureprojects.core.Preloader.INpurePreLoader").getDeclaredField("isDev").get(null)).booleanValue();
        } catch (Throwable t) {
            t.printStackTrace();

            return false;
        }
    }
}
