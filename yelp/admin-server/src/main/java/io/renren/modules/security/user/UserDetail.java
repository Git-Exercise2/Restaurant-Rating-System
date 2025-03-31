

package io.renren.modules.security.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Login user information
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String realName;
    private String headUrl;
    private Integer gender;
    private String email;
    private String mobile;
    private Long deptId;
    private String password;
    private Integer status;
    private Integer superAdmin;
    /**
     * Department data permissions
     */
    private List<Long> deptIdList;

}