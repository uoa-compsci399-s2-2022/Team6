

package com.project.common.annotation;

import java.lang.annotation.*;

/**
 * Data filtering Annotations
 *
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {
    /**
     * The table alias
     */
    String tableAlias() default "";

    /**
     * USER ID
     */
    String userId() default "creator";

    /**
     * DEPT ID
     */
    String deptId() default "dept_id";

}