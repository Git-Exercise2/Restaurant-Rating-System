package io.renren.modules.security.password;

/**
 * Password tool class
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public class PasswordUtils {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Encryption
     * @param str string
     * @return Return the encrypted string
     */
    public static String encode(String str){
        return passwordEncoder.encode(str);
    }


    /**
     * Compare whether the passwords are equal
     * @param str plaintext password
     * @param password Encrypted password
     * @return true: Success false: Failed
     */
    public static boolean matches(String str, String password){
        return passwordEncoder.matches(str, password);
    }


    public static void main(String[] args) {
        String str = "admin";
        String password = encode(str);

        System.out.println(password);
        System.out.println(matches(str, password));
    }

}
