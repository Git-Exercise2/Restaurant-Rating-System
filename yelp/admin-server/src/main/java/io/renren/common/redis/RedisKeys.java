

package io.renren.common.redis;

/**
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public class RedisKeys {
    /**
     * System parameters Key
     */
    public static String getSysParamsKey(){
        return "sys:params";
    }

    /**
     * Verification code Key
     */
    public static String getCaptchaKey(String uuid){
        return "sys:captcha:" + uuid;
    }

    /**
     * Login user Key
     */
    public static String getSecurityUserKey(Long id){
        return "sys:security:user:" + id;
    }

    /**
     * System Log Key
     */
    public static String getSysLogKey(){
        return "sys:log";
    }

    /**
     * System Resource Key
     */
    public static String getSysResourceKey(){
        return  "sys:resource";
    }

    /**
     * User menu navigation Key
     */
    public static String getUserMenuNavKey(Long userId){
        return "sys:user:nav:" + userId;
    }

    /**
     * User permissions identification Key
     */
    public static String getUserPermissionsKey(Long userId){
        return "sys:user:permissions:" + userId;
    }
}
