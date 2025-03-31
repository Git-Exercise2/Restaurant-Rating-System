

package io.renren.modules.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * Login form
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(title = "Login form")
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "username", required = true)
    @NotBlank(message="{sysuser.username.require}")
    private String username;

    @Schema(title = "password")
    @NotBlank(message="{sysuser.password.require}")
    private String password;

    @Schema(title = "Verification code")
    private String captcha;

    @Schema(title = "Unique ID")
    private String uuid;

}
