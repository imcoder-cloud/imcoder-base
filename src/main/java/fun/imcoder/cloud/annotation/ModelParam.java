package fun.imcoder.cloud.annotation;

import fun.imcoder.cloud.enums.ModelParamType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelParam {
    ModelParamType value() default ModelParamType.MODEL;
}
