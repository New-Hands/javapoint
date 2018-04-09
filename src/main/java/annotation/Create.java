package annotation;


import java.lang.annotation.*;


/**
 * @author lst
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
@Documented
public @interface Create {
    public String time() default "dfsf";
}
