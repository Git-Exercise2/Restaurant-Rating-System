

package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.exception.ErrorCode;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.modules.security.service.ShiroService;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.dto.SysMenuDTO;
import io.renren.modules.sys.enums.MenuTypeEnum;
import io.renren.modules.sys.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Menu Management
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/menu")
@Tag(name = "Menu Management")
@AllArgsConstructor
public class SysMenuController {
    private final SysMenuService sysMenuService;
    private final ShiroService shiroService;

    @GetMapping("nav")
    @Operation(summary = "navigation")
    public Result<List<SysMenuDTO>> nav() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, MenuTypeEnum.MENU.value());

        return new Result<List<SysMenuDTO>>().ok(list);
    }

    @GetMapping("permissions")
    @Operation(summary = "Permission ID")
    public Result<Set<String>> permissions() {
        UserDetail user = SecurityUser.getUser();
        Set<String> set = shiroService.getUserPermissions(user);

        return new Result<Set<String>>().ok(set);
    }

    @GetMapping("list")
    @Operation(summary = "List")
    @Parameter(name = "type", description = "Menu Type 0: Menu 1: Button null: All", in = ParameterIn.QUERY, ref = "int")
    public Result<List<SysMenuDTO>> list(Integer type) {
        List<SysMenuDTO> list = sysMenuService.getAllMenuList(type);

        return new Result<List<SysMenuDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "information")
    public Result<SysMenuDTO> get(@PathVariable("id") Long id) {
        SysMenuDTO data = sysMenuService.get(id);

        return new Result<SysMenuDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "save")
    @LogOperation("save")
    public Result save(@RequestBody SysMenuDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "Revise")
    @LogOperation("Revise")
    public Result update(@RequestBody SysMenuDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysMenuService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete")
    @LogOperation("delete")
    public Result delete(@PathVariable("id") Long id) {
        //Validity data
        AssertUtils.isNull(id, "id");

        //Determine whether there are submenu or buttons
        List<SysMenuDTO> list = sysMenuService.getListPid(id);
        if (list.size() > 0) {
            return new Result().error(ErrorCode.SUB_MENU_EXIST);
        }

        sysMenuService.delete(id);

        return new Result();
    }

    @GetMapping("select")
    @Operation(summary = "Role menu permissions")
    public Result<List<SysMenuDTO>> select() {
        UserDetail user = SecurityUser.getUser();
        List<SysMenuDTO> list = sysMenuService.getUserMenuList(user, null);

        return new Result<List<SysMenuDTO>>().ok(list);
    }
}
