package info.inpureprojects.core.Config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Option {

    String category();

    String key();

    String comment() default "";

    boolean value() default true;

    boolean released() default true;
}
