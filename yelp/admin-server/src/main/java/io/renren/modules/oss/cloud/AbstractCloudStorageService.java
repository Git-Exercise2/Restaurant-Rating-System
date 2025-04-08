

package io.renren.modules.oss.cloud;

import io.renren.common.utils.DateUtils;
import cn.hutool.core.util.StrUtil;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Cloud storage (supports Qiniu, Alibaba Cloud, Tencent Cloud)
 *
 * @author Mark sunlightcs@gmail.com
 */
public abstract class AbstractCloudStorageService {
    /** Cloud storage configuration information */
    CloudStorageConfig config;

    /**
     * File path
     * @param prefix prefix
     * @param suffix suffix
     * @return Return to upload path
     */
    public String getPath(String prefix, String suffix) {
        //generateuuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //File path
        String path = DateUtils.format(new Date(), "yyyyMMdd") + "/" + uuid;

        if(StrUtil.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path + "." + suffix;
    }

    /**
     * File upload
     * @param data File byte array
     * @param path File path, including file name
     * @return Return http address
     */
    public abstract String upload(byte[] data, String path);

    /**
     * File upload
     * @param data File byte array
     * @param suffix suffix
     * @return Return http address
     */
    public abstract String uploadSuffix(byte[] data, String suffix);

    /**
     * File upload
     * @param inputStream byte stream
     * @param path File path, including file name
     * @return Return http address
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * File upload
     * @param inputStream byte stream
     * @param suffix suffix
     * @return Return http address
     */
    public abstract String uploadSuffix(InputStream inputStream, String suffix);

}
