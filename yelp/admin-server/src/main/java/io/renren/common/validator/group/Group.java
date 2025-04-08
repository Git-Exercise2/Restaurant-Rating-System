

package io.renren.common.validator.group;


import jakarta.validation.GroupSequence;

/**
 * Define the verification order. If the AddGroup group fails, the UpdateGroup group will not be verified again.
 *
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
