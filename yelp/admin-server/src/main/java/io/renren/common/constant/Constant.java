

package io.renren.common.constant;

/**
 * Constant
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface Constant {
    /**
     * success
     */
    int SUCCESS = 1;
    /**
     * fail
     */
    int FAIL = 0;
    /**
     * Menu root node identification
     */
    Long MENU_ROOT = 0L;
    /**
     * Department root node identification
     */
    Long DEPT_ROOT = 0L;
    /**
     * Ascending order
     */
    String ASC = "asc";
    /**
     * descending order
     */
    String DESC = "desc";
    /**
     * Create time field name
     */
    String CREATE_DATE = "create_date";

    /**
     * Data permission filtering
     */
    String SQL_FILTER = "sqlFilter";
    /**
     * Current page number
     */
    String PAGE = "page";
    /**
     * Number of records displayed per page
     */
    String LIMIT = "limit";
    /**
     * Sort fields
     */
    String ORDER_FIELD = "orderField";
    /**
     * sort by
     */
    String ORDER = "order";
    /**
     * token header
     */
    String TOKEN_HEADER = "token";

    /**
     * Cloud storage configuration KEY
     */
    String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";
    String DELETED = "1";
    int USER_STATUS_DISABLED = 0;
    String NORMAL = "0";
    int USER_STATUS_NORMAL = 1;

    /**
     * Timing task status
     */
    enum ScheduleStatus {
        /**
         * pause
         */
        PAUSE(0),
        /**
         * normal
         */
        NORMAL(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Cloud service provider
     */
    enum CloudService {
        /**
         * Qiniu Cloud
         */
        QINIU(1),
        /**
         * Alibaba Cloud
         */
        ALIYUN(2),
        /**
         * Tencent Cloud
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    interface AuditStatus {
        String FAIL = "0";
        String PASS = "1";
    }

    interface ProcessResult {
        String DELETE_COMMENT = "1";
        String DELETE_COMMENT_AND_BAN_USER = "2";
        String DO_NOTHING = "3";
    }
}