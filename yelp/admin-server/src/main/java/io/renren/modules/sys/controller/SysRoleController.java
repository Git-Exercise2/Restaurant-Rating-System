

package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.dto.SysRoleDTO;
import io.renren.modules.sys.service.SysRoleDataScopeService;
import io.renren.modules.sys.service.SysRoleMenuService;
import io.renren.modules.sys.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Role Management
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/role")
@Tag(name = "Role Management")
@AllArgsConstructor
public class SysRoleController {
    private final SysRoleService sysRoleService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleDataScopeService sysRoleDataScopeService;

    @GetMapping("page")
    @Operation(summary = "Pagination")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "Current page number, starting from 1", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "Number of records displayed per page", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "Sort fields", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "Sort method, optional values ​​(asc, desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "name", description = "role name", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:role:page")
    public Result<PageData<SysRoleDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysRoleDTO> page = sysRoleService.page(params);

        return new Result<PageData<SysRoleDTO>>().ok(page);
    }

    @GetMapping("list")
    @Operation(summary = "List")
    @RequiresPermissions("sys:role:list")
    public Result<List<SysRoleDTO>> list() {
        List<SysRoleDTO> data = sysRoleService.list(new HashMap<>(1));

        return new Result<List<SysRoleDTO>>().ok(data);
    }

    @GetMapping("{id}")
    @Operation(summary = "information")
    @RequiresPermissions("sys:role:info")
    public Result<SysRoleDTO> get(@PathVariable("id") Long id) {
        SysRoleDTO data = sysRoleService.get(id);

        //Query the menu corresponding to the role
        List<Long> menuIdList = sysRoleMenuService.getMenuIdList(id);
        data.setMenuIdList(menuIdList);

        //Query the data permissions corresponding to the role
        List<Long> deptIdList = sysRoleDataScopeService.getDeptIdList(id);
        data.setDeptIdList(deptIdList);

        return new Result<SysRoleDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "save")
    @LogOperation("save")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody SysRoleDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysRoleService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "Revise")
    @LogOperation("Revise")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody SysRoleDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysRoleService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "delete")
    @LogOperation("delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody Long[] ids) {
        //Validity data
        AssertUtils.isArrayEmpty(ids, "id");

        sysRoleService.delete(ids);

        return new Result();
    }
}
