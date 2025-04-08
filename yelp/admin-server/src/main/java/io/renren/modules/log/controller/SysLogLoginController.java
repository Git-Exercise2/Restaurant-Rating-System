

package io.renren.modules.log.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.modules.log.dto.SysLogLoginDTO;
import io.renren.modules.log.excel.SysLogLoginExcel;
import io.renren.modules.log.service.SysLogLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * Login log
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("sys/log/login")
@Tag(name = "Login log")
@AllArgsConstructor
public class SysLogLoginController {
    private final SysLogLoginService sysLogLoginService;

    @GetMapping("page")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "Current page number, starting from 1", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.LIMIT, description = "Number of records displayed per page", in = ParameterIn.QUERY, required = true, ref = "int"),
            @Parameter(name = Constant.ORDER_FIELD, description = "Sort fields", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = Constant.ORDER, description = "Sort method, optional values ​​(asc, desc)", in = ParameterIn.QUERY, ref = "String"),
            @Parameter(name = "status", description = "Status 0: Failed 1: Success 2: Account is locked", in = ParameterIn.QUERY, ref = "int"),
            @Parameter(name = "creatorName", description = "Username", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:log:login")
    public Result<PageData<SysLogLoginDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysLogLoginDTO> page = sysLogLoginService.page(params);

        return new Result<PageData<SysLogLoginDTO>>().ok(page);
    }

    @GetMapping("export")
    @LogOperation("Export")
    @Parameters({
            @Parameter(name = "status", description = "Status 0: Failed 1: Success 2: Account is locked", in = ParameterIn.QUERY, ref = "int"),
            @Parameter(name = "creatorName", description = "Username", in = ParameterIn.QUERY, ref = "String")
    })
    @RequiresPermissions("sys:log:login")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<SysLogLoginDTO> list = sysLogLoginService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "Login log", list, SysLogLoginExcel.class);

    }

}
