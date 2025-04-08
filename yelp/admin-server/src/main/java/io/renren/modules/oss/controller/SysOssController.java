

package io.renren.modules.oss.controller;

import cn.hutool.core.io.file.FileNameUtil;
import io.renren.common.annotation.LogOperation;
import io.renren.common.constant.Constant;
import io.renren.common.exception.ErrorCode;
import io.renren.common.page.PageData;
import io.renren.common.utils.JsonUtils;
import io.renren.common.utils.Result;
import io.renren.common.validator.ValidatorUtils;
import io.renren.common.group.AliyunGroup;
import io.renren.common.group.QcloudGroup;
import io.renren.common.group.QiniuGroup;
import io.renren.modules.oss.cloud.CloudStorageConfig;
import io.renren.modules.oss.cloud.OSSFactory;
import io.renren.modules.oss.entity.SysOssEntity;
import io.renren.modules.oss.service.SysOssService;
import io.renren.modules.sys.service.SysParamsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * File upload
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("sys/oss")
@Tag(name = "File upload")
@AllArgsConstructor
public class SysOssController {
    private final SysOssService sysOssService;
    private final SysParamsService sysParamsService;

    private final static String KEY = Constant.CLOUD_STORAGE_CONFIG_KEY;

    @GetMapping("page")
    @Operation(summary = "Pagination")
    @RequiresPermissions("sys:oss:all")
    public Result<PageData<SysOssEntity>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        PageData<SysOssEntity> page = sysOssService.page(params);

        return new Result<PageData<SysOssEntity>>().ok(page);
    }

    @GetMapping("info")
    @Operation(summary = "Cloud storage configuration information")
    @RequiresPermissions("sys:oss:all")
    public Result<CloudStorageConfig> info() {
        CloudStorageConfig config = sysParamsService.getValueObject(KEY, CloudStorageConfig.class);

        return new Result<CloudStorageConfig>().ok(config);
    }

    @PostMapping
    @Operation(summary = "保存Cloud storage configuration information")
    @LogOperation("保存Cloud storage configuration information")
    @RequiresPermissions("sys:oss:all")
    public Result saveConfig(@RequestBody CloudStorageConfig config) {
        //Verification type
        ValidatorUtils.validateEntity(config);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            //Verify Qiniu data
            ValidatorUtils.validateEntity(config, QiniuGroup.class);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            //Verify Alibaba Cloud data
            ValidatorUtils.validateEntity(config, AliyunGroup.class);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            //Verify Tencent Cloud Data
            ValidatorUtils.validateEntity(config, QcloudGroup.class);
        }

        sysParamsService.updateValueByCode(KEY, JsonUtils.toJsonString(config));

        return new Result();
    }

    @PostMapping("upload")
    @Operation(summary = "Upload file")
    @RequiresPermissions("sys:oss:all")
    public Result<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return new Result<Map<String, Object>>().error(ErrorCode.UPLOAD_FILE_EMPTY);
        }

        //Upload file
        String suffix = FileNameUtil.getSuffix(file.getOriginalFilename());
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

        //Save file information
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateDate(new Date());
        sysOssService.insert(ossEntity);

        Map<String, Object> data = new HashMap<>(1);
        data.put("src", url);

        return new Result<Map<String, Object>>().ok(data);
    }

    @DeleteMapping
    @Operation(summary = "delete")
    @LogOperation("delete")
    @RequiresPermissions("sys:oss:all")
    public Result delete(@RequestBody Long[] ids) {
        sysOssService.deleteBatchIds(Arrays.asList(ids));

        return new Result();
    }

}
