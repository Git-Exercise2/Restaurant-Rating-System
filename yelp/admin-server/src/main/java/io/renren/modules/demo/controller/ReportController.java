package io.renren.modules.demo.controller;

import io.renren.common.annotation.LogOperation;
import io.renren.common.annotation.LoginUser;
import io.renren.common.constant.Constant;
import io.renren.common.page.PageData;
import io.renren.common.utils.ConvertUtils;
import io.renren.common.utils.ExcelUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.AssertUtils;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.validator.group.AddGroup;
import io.renren.common.validator.group.DefaultGroup;
import io.renren.common.validator.group.UpdateGroup;
import io.renren.modules.demo.dao.DiscussDao;
import io.renren.modules.demo.dto.ReportDTO;
import io.renren.modules.demo.entity.DiscussEntity;
import io.renren.modules.demo.entity.ReportEntity;
import io.renren.modules.demo.excel.ReportExcel;
import io.renren.modules.demo.service.ReportService;
import io.renren.modules.security.entity.SysUserTokenEntity;
import io.renren.modules.security.user.SecurityUser;
import io.renren.modules.security.user.UserDetail;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.dto.SysUserDTO;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleUserService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.http.util.TextUtils;
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
@RequestMapping("demo/report")
@Tag(name="")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private DiscussDao discussDao;

    @GetMapping("page")
    public Result<PageData<ReportDTO>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params){
        PageData<ReportDTO> page = null;

        UserDetail user = SecurityUser.getUser();

       if (user.getSuperAdmin() == null || user.getSuperAdmin() != 1){
           params.put("report_user_id", user.getId());
       }
       page = reportService.page(params);

        return new Result<PageData<ReportDTO>>().ok(page);
    }

    @GetMapping("{id}")
    public Result<ReportDTO> get(@PathVariable("id") Long id){
        ReportDTO data = reportService.get(id);

        return new Result<ReportDTO>().ok(data);
    }

    @PostMapping
    public Result save(@RequestBody ReportDTO dto){

        dto.setCreateDate(new Date());
        reportService.save(dto);

        return new Result();
    }

    @PutMapping
    public Result update(@RequestBody ReportDTO dto){
        ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

        dto.setUpdateDate(new Date());

        if (Constant.ProcessResult.DELETE_COMMENT.equals(dto.getReportHandleType())){
            if(!deleteComment(dto.getDiscussId())){
                return new Result().error("Comment not exist");
            }
        }
        else if (Constant.ProcessResult.DELETE_COMMENT_AND_BAN_USER.equals(dto.getReportHandleType())){
            if(!deleteComment(dto.getDiscussId())){
                return new Result().error("Comment not exist");
            }
            // update user disable
            if (!banUser(dto.getAttr2())){
                return new Result().error("User not exist");
            }
        }

        reportService.update(dto);

        return new Result();
    }

    private boolean deleteComment(Long discussId){
        if (discussId == null){
            return false;
        }
        DiscussEntity discussEntity = discussDao.selectById(discussId);
        if (discussEntity == null){
            return false;
        }
        discussEntity.setIsDelete(Constant.DELETED);
        discussDao.updateById(discussEntity);
        return true;
    }

    private boolean banUser(String username){
        if (username == null || TextUtils.isEmpty(username)){
            return false;
        }
        SysUserEntity sysUserEntity = sysUserDao.getByUsername(username);
        if (sysUserEntity == null){
            return false;
        }
        sysUserEntity.setStatus(Constant.USER_STATUS_DISABLED);
        sysUserDao.updateById(sysUserEntity);
        return true;
    }

    @DeleteMapping
    public Result delete(@RequestBody Long[] ids){
        //Validity data
        AssertUtils.isArrayEmpty(ids, "id");

        for (Long id : ids) {
            ReportEntity reportEntity = reportService.selectById(id);
            if (reportEntity == null){
                continue;
            }
            if (Constant.AuditStatus.PASS.equals(reportEntity.getAudit())){
                return new Result().error("Reports that have been reviewed cannot be deleted");
            }
        }

        reportService.delete(ids);

        return new Result();
    }

    @GetMapping("export")
    public void export(@Parameter(hidden = true) @RequestParam Map<String, Object> params, HttpServletResponse response) throws Exception {
        List<ReportDTO> list = reportService.list(params);

        ExcelUtils.exportExcelToTarget(response, null, "", list, ReportExcel.class);
    }

}
