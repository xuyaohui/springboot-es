package cn.momosv.es.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ElasticIndex{
    String index() default "default" ;
    String type() default "" ;
}
