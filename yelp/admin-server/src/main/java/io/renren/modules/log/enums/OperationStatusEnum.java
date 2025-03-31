

package io.renren.modules.log.enums;

/**
 * Operation status enumeration
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public enum OperationStatusEnum {
    /**
     * fail
     */
    FAIL(0),
    /**
     * success
     */
    SUCCESS(1);

    private int value;

    OperationStatusEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}