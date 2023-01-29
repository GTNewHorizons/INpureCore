package info.inpureprojects.core.Config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DeprecatedOption {

    String category();

    String key();
}
