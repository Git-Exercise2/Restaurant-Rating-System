

package io.renren.modules.sys.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.sys.dto.SysDictDataDTO;
import io.renren.modules.sys.service.SysDictDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Dictionary data
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/dict/data")
@Tag(name = "Dictionary data")
@AllArgsConstructor
public class SysDictDataController {
    private final SysDictDataService sysDictDataService;

    @GetMapping("page")
    @Operation(summary = "Dictionary data")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "Current page number, starting from 1", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "Number of records displayed per page", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "Sort fields", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "Sort method, optional values ​​(asc, desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictLabel", description = "Dictionary tag", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "dictValue", description = "Dictionary value", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:dict:page")
    public Result<PageData<SysDictDataDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        //Dictionary Type
        PageData<SysDictDataDTO> page = sysDictDataService.page(params);

        return new Result<PageData<SysDictDataDTO>>().ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "information")
    @RequiresPermissions("sys:dict:info")
    public Result<SysDictDataDTO> get(@PathVariable("id") Long id) {
        SysDictDataDTO data = sysDictDataService.get(id);

        return new Result<SysDictDataDTO>().ok(data);
    }

    @PostMapping
    @Operation(summary = "save")
    @LogOperation("save")
    @RequiresPermissions("sys:dict:save")
    public Result save(@RequestBody SysDictDataDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, DefaultGroup.class);

        sysDictDataService.save(dto);

        return new Result();
    }

    @PutMapping
    @Operation(summary = "Revise")
    @LogOperation("Revise")
    @RequiresPermissions("sys:dict:update")
    public Result update(@RequestBody SysDictDataDTO dto) {
        //Validity data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        sysDictDataService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @Operation(summary = "delete")
    @LogOperation("delete")
    @RequiresPermissions("sys:dict:delete")
    public Result delete(@RequestBody Long[] ids) {
        //Validity data
        AssertUtils.isArrayEmpty(ids, "id");

        sysDictDataService.delete(ids);

        return new Result();
    }

}
