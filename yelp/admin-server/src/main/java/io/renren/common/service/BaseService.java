

package io.renren.common.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;

import java.io.Serializable;
import java.util.Collection;

/**
 * Basic service interface, all Service interfaces must be inherited
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface BaseService<T> {
    Class<T> currentModelClass();

    /**
     * <p>
     * Insert a record (select field, policy insertion)
     * </p>
     *
     * @param entity entity object
     */
    boolean insert(T entity);

    /**
     * <p>
     * Insert (batch), this method does not support Oracle and SQL Server
     * </p>
     *
     * @param entityList Entity object collection
     */
    boolean insertBatch(Collection<T> entityList);

    /**
     * <p>
     * Insert (batch), this method does not support Oracle and SQL Server
     * </p>
     *
     * @param entityList Entity object collection
     * @param batchSize Number of insert batches
     */
    boolean insertBatch(Collection<T> entityList, int batchSize);

    /**
     * <p>
     * Select Modify according to ID
     * </p>
     *
     * @param entity entity object
     */
    boolean updateById(T entity);

    /**
     * <p>
     * Update records according to whereEntity conditions
     * </p>
     *
     * @param entity entity object
     * @param updateWrapper Entity object encapsulation operation class {@link com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper}
     */
    boolean update(T entity, Wrapper<T> updateWrapper);

    /**
     * <p>
     * Batch updates by ID
     * </p>
     *
     * @param entityList Entity object collection
     */
    boolean updateBatchById(Collection<T> entityList);

    /**
     * <p>
     * Batch updates by ID
     * </p>
     *
     * @param entityList Entity object collection
     * @param batchSize Update batch count
     */
    boolean updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * <p>
     * Query by ID
     * </p>
     *
     * @param id Primary key ID
     */
    T selectById(Serializable id);

    /**
     * <p>
     * Delete by ID
     * </p>
     *
     * @param id Primary key ID
     */
    boolean deleteById(Serializable id);

    /**
     * <p>
     * Delete (batch delete according to ID)
     * </p>
     *
     * @param idList Primary Key ID List
     */
    boolean deleteBatchIds(Collection<? extends Serializable> idList);
}