

package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.dto.SysDeptDTO;
import io.renren.modules.sys.service.SysDeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Department Management
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/sys/dept")
@Tag(name = "Department Management")
@AllArgsConstructor
public class SysDeptController {
    private final SysDeptService sysDeptService;

    @GetMapping("list")
    @Operation(summary = "List")
    @RequiresPermissions("sys:dept:list")
    public Result<List<SysDeptDTO>> list() {
        List<SysDeptDTO> list = sysDeptService.list(new HashMap<>(1));

        return new Result<List<SysDeptDTO>>().ok(list);
    }

    @GetMapping("{id}")
    @Operation(summary = "information")
    @RequiresPermissions("sys:dept:info")
    public Result<SysDeptDTO> get(@PathVariable("id") Long id) {
        SysDeptDTO data = sysDeptService.get(id);

        return new Result<SysDeptDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "save")
    @LogOperation("save")
    @RequiresPermissions("sys:dept:save")
    public Result save(@RequestBody SysDeptDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

        sysDeptService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "Revise")
    @LogOperation("Revise")
    @RequiresPermissions("sys:dept:update")
    public Result update(@RequestBody SysDeptDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDeptService.update(dto);

        return new Result();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "delete")
    @LogOperation("delete")
    @RequiresPermissions("sys:dept:delete")
    public Result delete(@PathVariable("id") Long id) {
        //Validity data
        AssertUtils.isNull(id, "id");

        sysDeptService.delete(id);

        return new Result();
    }

}
