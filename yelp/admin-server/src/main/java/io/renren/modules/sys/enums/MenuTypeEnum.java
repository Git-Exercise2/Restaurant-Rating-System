

package io.renren.modules.sys.enums;

/**
 * Menu type enumeration
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public enum MenuTypeEnum {
    /**
     * menu
     */
    MENU(0),
    /**
     * Button
     */
    BUTTON(1);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
