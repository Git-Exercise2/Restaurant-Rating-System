

package io.renren.modules.log.enums;

/**
 * Login status enumeration
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public enum LoginStatusEnum {
    /**
     * fail
     */
    FAIL(0),
    /**
     * success
     */
    SUCCESS(1),
    /**
     * Account is locked
     */
    LOCK(2);

    private int value;

    LoginStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
