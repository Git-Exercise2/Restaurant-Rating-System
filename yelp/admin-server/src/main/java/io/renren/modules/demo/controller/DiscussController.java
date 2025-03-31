package io.renren.modules.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.demo.dao.DiscussDao;
import io.renren.modules.demo.dto.DiscussDTO;
import io.renren.modules.demo.entity.DiscussEntity;
import io.renren.modules.demo.excel.DiscussExcel;
import io.renren.modules.demo.service.DiscussService;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.http.HttpRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0 2025-03-01
 */
@RestController
@RequestMapping("demo/discuss")
@Tag(name="")
public class DiscussController {
    @Autowired
    private DiscussService discussService;

    @Resource
    private DiscussDao discussDao;

    @Resource
    private SysUserService sysUserService;

    @GetMapping("page")
    public Result<PageData<DiscussDTO>> page(HttpRequest httpRequest, @Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<DiscussDTO> page = null;

        UserDetail user = SecurityUser.getUser();

        if (user.getSuperAdmin() == null || user.getSuperAdmin() != 1){
            params.put("restaurant_name", user.getUsername());
        }
        page = discussService.page(params);

        return new Result<PageData<DiscussDTO>>().ok(page);
    }

    @GetMapping("queryAllById")
    public Result queryAllById(@RequestParam(name = "restaurantId") Long restaurantId) {
        return new Result<>().ok(discussDao.selectList(new QueryWrapper<DiscussEntity>().eq("restaurant_id", restaurantId).eq("is_delete", "0")));
    }

    @GetMapping("queryAllById2")
    public Result queryAllById2(@RequestParam(name = "userId") Long userId) {
        return new Result<>().ok(discussDao.selectList(new QueryWrapper<DiscussEntity>().eq("user_id", userId)));
    }

    @GetMapping("{id}")
    public Result<DiscussDTO> get(@PathVariable("id") Long id){
        DiscussDTO data = discussService.get(id);

        return new Result<DiscussDTO>().ok(data);
    }

    @PostMapping
    @LogOperation("save")
    public Result save(@RequestBody DiscussDTO dto){

        dto.setUserName(sysUserService.get(dto.getUserId()).getUsername());
        dto.setCreateDate(new Date());

        discussService.save(dto);

        return new Result();
    }

    @PutMapping
    @LogOperation("Revise")
    public Result update(@RequestBody DiscussDTO dto){
        //Validity data
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        discussService.update(dto);

        return new Result();
    }

    @DeleteMapping
    @LogOperation("delete")
    public Result delete(@RequestBody Long[] ids){
        //Validity data
        AssertUtils.isArrayEmpty(ids, "id");

        discussService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    @LogOperation("Export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<DiscussDTO> list = discussService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "", list, DiscussExcel.class);
    }

}
