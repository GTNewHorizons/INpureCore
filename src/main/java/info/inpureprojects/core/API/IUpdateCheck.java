package info.inpureprojects.core.API;

public interface IUpdateCheck {
    String getVersion();

    String getModId();

    String getUpdateUrl();

    String getModName();

    ReleaseLevel getLevel();
}
