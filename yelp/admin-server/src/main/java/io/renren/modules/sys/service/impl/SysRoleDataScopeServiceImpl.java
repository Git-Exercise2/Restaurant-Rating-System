

package io.renren.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.modules.sys.dao.SysRoleDataScopeDao;
import io.renren.modules.sys.entity.SysRoleDataScopeEntity;
import io.renren.modules.sys.service.SysRoleDataScopeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Role data permissions
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysRoleDataScopeServiceImpl extends BaseServiceImpl<SysRoleDataScopeDao, SysRoleDataScopeEntity>
        implements SysRoleDataScopeService {

    @Override
    public List<Long> getDeptIdList(Long roleId) {
        return baseDao.getDeptIdList(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> deptIdList) {
        //Delete the role data permission relationship first
        deleteByRoleIds(new Long[]{roleId});

        //The role does not have a data permission
        if(CollUtil.isEmpty(deptIdList)){
            return ;
        }

        //Save role data permission relationship
        for(Long deptId : deptIdList){
            SysRoleDataScopeEntity sysRoleDataScopeEntity = new SysRoleDataScopeEntity();
            sysRoleDataScopeEntity.setDeptId(deptId);
            sysRoleDataScopeEntity.setRoleId(roleId);

            //save
            insert(sysRoleDataScopeEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }
}