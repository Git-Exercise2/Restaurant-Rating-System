

package io.renren.modules.security.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Verification code
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface CaptchaService {

    /**
     * Image verification code
     */
    void create(HttpServletResponse response, String uuid) throws IOException;

    /**
     * Verification code validation
     * @param uuid uuid
     * @param code verification code
     * @return true: Success false: Failed
     */
    boolean validate(String uuid, String code);
}
