

package io.renren.common.utils;

import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Tree nodes, all those who need to implement tree nodes need to inherit this class
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
 @Data
public class TreeNode<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Primary key
     */
    private Long id;
    /**
     * Superior ID
     */
    private Long pid;
    /**
     * List of child nodes
     */
    private List<T> children = new ArrayList<>();
}