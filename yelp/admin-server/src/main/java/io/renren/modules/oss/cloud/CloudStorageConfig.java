

package io.renren.modules.oss.cloud;

import io.renren.common.group.AliyunGroup;
import io.renren.common.group.QcloudGroup;
import io.renren.common.group.QiniuGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

/**
 * Cloud storage configuration information
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(title = "Cloud storage configuration information")
public class CloudStorageConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "Type 1: Qiniu 2: Alibaba Cloud 3: Tencent Cloud")
    @Range(min = 1, max = 6, message = "{oss.type.range}")
    private Integer type;

    @Schema(title = "The domain name bound to Qiniu")
    @NotBlank(message = "{qiniu.domain.require}", groups = QiniuGroup.class)
    @URL(message = "{qiniu.domain.url}", groups = QiniuGroup.class)
    private String qiniuDomain;

    @Schema(title = "Qiniu Path Prefix")
    private String qiniuPrefix;

    @Schema(title = "Qiniu ACCESS_KEY")
    @NotBlank(message = "{qiniu.accesskey.require}", groups = QiniuGroup.class)
    private String qiniuAccessKey;

    @Schema(title = "Qiniu SECRET_KEY")
    @NotBlank(message = "{qiniu.secretkey.require}", groups = QiniuGroup.class)
    private String qiniuSecretKey;

    @Schema(title = "Qiniu Storage Space Name")
    @NotBlank(message = "{qiniu.bucketName.require}", groups = QiniuGroup.class)
    private String qiniuBucketName;

    @Schema(title = "Alibaba Cloud-bound domain name")
    @NotBlank(message = "{aliyun.domain.require}", groups = AliyunGroup.class)
    @URL(message = "{aliyun.domain.url}", groups = AliyunGroup.class)
    private String aliyunDomain;

    @Schema(title = "Alibaba Cloud Path Prefix")
    private String aliyunPrefix;

    @Schema(title = "Alibaba Cloud EndPoint")
    @NotBlank(message = "{aliyun.endPoint.require}", groups = AliyunGroup.class)
    private String aliyunEndPoint;

    @Schema(title = "Alibaba Cloud AccessKeyId")
    @NotBlank(message = "{aliyun.accesskeyid.require}", groups = AliyunGroup.class)
    private String aliyunAccessKeyId;

    @Schema(title = "Alibaba Cloud AccessKeySecret")
    @NotBlank(message = "{aliyun.accesskeysecret.require}", groups = AliyunGroup.class)
    private String aliyunAccessKeySecret;

    @Schema(title = "Alibaba Cloud BucketName")
    @NotBlank(message = "{aliyun.bucketName.require}", groups = AliyunGroup.class)
    private String aliyunBucketName;

    @Schema(title = "Tencent Cloud-bound domain name")
    @NotBlank(message = "{qcloud.domain.require}", groups = QcloudGroup.class)
    @URL(message = "{qcloud.domain.url}", groups = QcloudGroup.class)
    private String qcloudDomain;

    @Schema(title = "Tencent Cloud Path Prefix")
    private String qcloudPrefix;

    @Schema(title = "Tencent Cloud AppId")
    @NotNull(message = "{qcloud.appid.require}", groups = QcloudGroup.class)
    private Integer qcloudAppId;

    @Schema(title = "Tencent Cloud SecretId")
    @NotBlank(message = "{qcloud.secretId.require}", groups = QcloudGroup.class)
    private String qcloudSecretId;

    @Schema(title = "Tencent Cloud SecretKey")
    @NotBlank(message = "{qcloud.secretkey.require}", groups = QcloudGroup.class)
    private String qcloudSecretKey;

    @Schema(title = "Tencent Cloud BucketName")
    @NotBlank(message = "{qcloud.bucketName.require}", groups = QcloudGroup.class)
    private String qcloudBucketName;

    @Schema(title = "Tencent Cloud COS Region")
    @NotBlank(message = "{qcloud.region.require}", groups = QcloudGroup.class)
    private String qcloudRegion;

}
