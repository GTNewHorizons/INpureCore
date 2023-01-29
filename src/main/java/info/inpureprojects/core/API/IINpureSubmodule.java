package info.inpureprojects.core.API;

import java.io.File;

public interface IINpureSubmodule {

    void pre(File paramFile);

    void init();

    void post();
}
