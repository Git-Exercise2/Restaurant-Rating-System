

package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.dto.SysDictTypeDTO;
import io.renren.modules.sys.entity.DictType;
import io.renren.modules.sys.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Dictionary Type
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/dict/type")
@Tag(name = "Dictionary Type")
@AllArgsConstructor
public class SysDictTypeController {
    private final SysDictTypeService sysDictTypeService;

    @GetMapping("page")
    @Operation(summary = "Dictionary Type")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "Current page number, starting from 1", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "Number of records displayed per page", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "Sort fields", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "Sort method, optional values ​​(asc, desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictType", description = "Dictionary Type", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictName", description = "Dictionary name", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:dict:page")
    public Result<PageData<SysDictTypeDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        //Dictionary Type
        PageData<SysDictTypeDTO> page = sysDictTypeService.page(params);

        return new Result<PageData<SysDictTypeDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "information")
    @RequiresPermissions("sys:dict:info")
    public Result<SysDictTypeDTO> get(@PathVariable("id") Long id) {
        SysDictTypeDTO data = sysDictTypeService.get(id);

        return new Result<SysDictTypeDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "save")
    @LogOperation("save")
    @RequiresPermissions("sys:dict:save")
    public Result save(@RequestBody SysDictTypeDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictTypeService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "Revise")
    @LogOperation("Revise")
    @RequiresPermissions("sys:dict:update")
    public Result update(@RequestBody SysDictTypeDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictTypeService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "delete")
    @LogOperation("delete")
    @RequiresPermissions("sys:dict:delete")
    public Result delete(@RequestBody Long[] ids) {
        //Validity data
        AssertUtils.isArrayEmpty(ids, "id");

        sysDictTypeService.delete(ids);

        return new Result();
    }

    @GetMapping("all")
    @Operation(summary = "All dictionary data")
    public Result<List<DictType>> all() {
        List<DictType> list = sysDictTypeService.getAllList();

        return new Result<List<DictType>>().ok(list);
    }

}
