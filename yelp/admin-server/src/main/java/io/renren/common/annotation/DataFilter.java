

package io.renren.common.annotation;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {

    String tableAlias() default "";

    /**
     * User ID
     */
    String userId() default "creator";

    /**
     * Department ID
     */
    String deptId() default "dept_id";

}