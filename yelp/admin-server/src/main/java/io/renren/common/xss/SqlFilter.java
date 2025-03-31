

package io.renren.common.xss;

import cn.hutool.core.util.StrUtil;
import io.renren.common.exception.ErrorCode;
import io.renren.common.exception.RenException;

/**
 * SQL filtering
 *
 * @author Mark sunlightcs@gmail.com
 */
public class SqlFilter {

    /**
     * SQL injection filtering
     *
     * @param str String to be verified
     */
    public static String sqlInject(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }
        //Remove'|"|;|\字符
        str = StrUtil.replace(str, "'", "");
        str = StrUtil.replace(str, "\"", "");
        str = StrUtil.replace(str, ";", "");
        str = StrUtil.replace(str, "\\", "");

        //Convert to lowercase
        str = str.toLowerCase();

        //Illegal characters
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"};

        //判断是否包含Illegal characters
        for (String keyword : keywords) {
            if (str.contains(keyword)) {
                throw new RenException(ErrorCode.INVALID_SYMBOL);
            }
        }

        return str;
    }
}
