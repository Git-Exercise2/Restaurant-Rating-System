

package io.renren.common.validator.group;


import jakarta.validation.GroupSequence;

/**
 * Define the verification order. If the AddGroup group fails, the UpdateGroup group will not be verified again.
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
