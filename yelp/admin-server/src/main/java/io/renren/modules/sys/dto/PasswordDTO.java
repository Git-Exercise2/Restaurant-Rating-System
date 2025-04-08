

package io.renren.modules.sys.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * Change password
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Data
@Schema(title = "Modify password")
public class PasswordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "Original password")
    @NotBlank(message="{sysuser.password.require}")
    private String password;

    @Schema(title = "New Password")
    @NotBlank(message="{sysuser.password.require}")
    private String newPassword;

}
