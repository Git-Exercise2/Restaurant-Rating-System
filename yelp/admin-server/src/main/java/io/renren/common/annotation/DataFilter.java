

package io.renren.common.annotation;

import java.lang.annotation.*;

/**
 * Data filtering annotations
 *
 * @author Mark sunlightcs@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**
     * Alias ​​for the table
     */
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