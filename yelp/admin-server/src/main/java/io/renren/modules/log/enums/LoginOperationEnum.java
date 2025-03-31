

package io.renren.modules.log.enums;

/**
 * Login operation enumeration
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public enum LoginOperationEnum {
    /**
     * User login
     */
    LOGIN(0),
    /**
     * User logout
     */
    LOGOUT(1);

    private int value;

    LoginOperationEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}