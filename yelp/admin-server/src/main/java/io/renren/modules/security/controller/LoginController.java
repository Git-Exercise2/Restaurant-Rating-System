

package io.renren.modules.security.controller;

import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.RenException;
import io.renren.common.utils.IpUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.log.entity.SysLogLoginEntity;
import io.renren.modules.log.enums.LoginOperationEnum;
import io.renren.modules.log.enums.LoginStatusEnum;
import io.renren.modules.log.service.SysLogLoginService;
import io.renren.modules.security.dto.LoginDTO;
import io.renren.modules.security.dto.RegisterDTO;
import io.renren.modules.security.password.PasswordUtils;
import io.renren.modules.security.service.CaptchaService;
import io.renren.modules.security.service.SysUserTokenService;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.dto.SysUserDTO;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.enums.SuperAdminEnum;
import io.renren.modules.sys.enums.UserStatusEnum;
import io.renren.modules.sys.service.SysRoleUserService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Log in
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@Tag(name = "Login Management")
@AllArgsConstructor
public class LoginController {
    private final SysUserService sysUserService;
    private final SysUserTokenService sysUserTokenService;
    private final CaptchaService captchaService;
    private final SysLogLoginService sysLogLoginService;
    private final SysRoleUserService sysRoleUserService;

    @GetMapping("captcha")
    @Operation(summary = "Verification code")
    @Parameter(in = ParameterIn.QUERY, ref = "string", name = "uuid", required = true)
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        //uuid cannot be empty
        AssertUtils.isBlank(uuid, ErrorCode.IDENTIFIER_NOT_NULL);

        //Generate verification code
        captchaService.create(response, uuid);
    }

    @PostMapping("login")
    @Operation(summary = "Log in")
    public Result login(HttpServletRequest request, @RequestBody LoginDTO login) {
        //Validity data
        ValidatorUtils.validateEntity(login);

        //Verification code is correct
        if (login.getCaptcha() != null) {
            boolean flag = captchaService.validate(login.getUuid(), login.getCaptcha());
            if (!flag) {
                return new Result().error(ErrorCode.CAPTCHA_ERROR);
            }
        }

        //User Information
        SysUserDTO user = sysUserService.getByUsername(login.getUsername());

        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGIN.value());
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

        //The user does not exist
        if (user == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //Error password
        if (!PasswordUtils.matches(login.getPassword(), user.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //Account disable
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_DISABLE);
        }

        //Users are not allowed to log in to the management background directly
        if (user.getRoleIdList() != null && user.getRoleIdList().contains(1895835058941677569L)){
            throw new RenException(ErrorCode.UNAUTHORIZED);
        }

        //Login successfully
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        sysLogLoginService.save(log);

        return sysUserTokenService.createToken(user.getId());
    }

    @PostMapping("loginInner")
    @Operation(summary = "Log in")
    public Result loginInner(HttpServletRequest request, @RequestBody LoginDTO login) {
        //Validity data
        ValidatorUtils.validateEntity(login);

        //User Information
        SysUserDTO user = sysUserService.getByUsername(login.getUsername());

        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGIN.value());
        log.setCreateDate(new Date());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));

        //The user does not exist
        if (user == null) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreatorName(login.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //Error password
        if (!PasswordUtils.matches(login.getPassword(), user.getPassword())) {
            log.setStatus(LoginStatusEnum.FAIL.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_PASSWORD_ERROR);
        }

        //Account disable
        if (user.getStatus() == UserStatusEnum.DISABLE.value()) {
            log.setStatus(LoginStatusEnum.LOCK.value());
            log.setCreator(user.getId());
            log.setCreatorName(user.getUsername());
            sysLogLoginService.save(log);

            throw new RenException(ErrorCode.ACCOUNT_DISABLE);
        }

        //Login successfully
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        sysLogLoginService.save(log);

        return new Result().ok(user);
    }

    @GetMapping("registerInner")
    public Result registerInner(@RequestParam String username, @RequestParam String password, @RequestParam String realName) {

        SysUserDTO user = sysUserService.getByUsername(username);

        if (user != null) {
            return new Result().error("Account already exists");
        }

        SysUserEntity entity = new SysUserEntity();
        entity.setUsername(username);
        entity.setRealName(realName);
        entity.setPassword(PasswordUtils.encode(password));
        entity.setSuperAdmin(SuperAdminEnum.NO.value());
        entity.setGender(0);
        entity.setStatus(1);
        sysUserService.insert(entity);

        //save user and role
        List<Long> rolesIds = new ArrayList<>();
        rolesIds.add(1895835058941677569L);
        sysRoleUserService.saveOrUpdate(entity.getId(), rolesIds);

        return new Result();
    }

    @PostMapping("logout")
    @Operation(summary = "quit")
    public Result logout(HttpServletRequest request) {
        UserDetail user = SecurityUser.getUser();

        //quit
        sysUserTokenService.logout(user.getId());

        //User Information
        SysLogLoginEntity log = new SysLogLoginEntity();
        log.setOperation(LoginOperationEnum.LOGOUT.value());
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setIp(IpUtils.getIpAddr(request));
        log.setStatus(LoginStatusEnum.SUCCESS.value());
        log.setCreator(user.getId());
        log.setCreatorName(user.getUsername());
        log.setCreateDate(new Date());
        sysLogLoginService.save(log);

        return new Result();
    }

}
