

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
public class RegisterDTO implements Serializable {

    @NotBlank(message = "username is empty")
    private String username;

    @NotBlank(message = "nickname is empty")
    private String realName;

    @NotBlank(message = "password is empty")
    private String password;

}
