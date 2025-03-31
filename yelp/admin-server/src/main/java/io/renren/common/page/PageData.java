

package io.renren.common.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Pagination tool class
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@Schema(title = "Paginated data")
public class PageData<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(title = "Total records")
    private int total;

    @Schema(title = "List data")
    private List<T> list;

    /**
     * Pagination
     * @param list list data
     * @param total Total records
     */
    public PageData(List<T> list, long total) {
        this.list = list;
        this.total = (int)total;
    }
}
