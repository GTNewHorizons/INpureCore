package info.inpureprojects.core.Proxy;

public abstract class Proxy {

    public abstract void warning(String paramString);

    public abstract void print(String paramString);

    public abstract void severe(String paramString);

    public abstract void setupAPI();

    public abstract void client();

    public abstract void sendMessageToPlayer(String paramString);

    public abstract void onServerStartClient();
}
