

package io.renren.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.modules.sys.dao.SysRoleUserDao;
import io.renren.modules.sys.entity.SysRoleUserEntity;
import io.renren.modules.sys.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Role User Relationship
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUserDao, SysRoleUserEntity> implements SysRoleUserService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //Delete the role user relationship first
        deleteByUserIds(new Long[]{userId});

        //If the user does not have a role permission
        if(CollUtil.isEmpty(roleIdList)){
            return ;
        }

        //Save role user relationships
        for(Long roleId : roleIdList){
            SysRoleUserEntity sysRoleUserEntity = new SysRoleUserEntity();
            sysRoleUserEntity.setUserId(userId);
            sysRoleUserEntity.setRoleId(roleId);

            //save
            insert(sysRoleUserEntity);
        }
    }

    @Override
    public void deleteByRoleIds(Long[] roleIds) {
        baseDao.deleteByRoleIds(roleIds);
    }

    @Override
    public void deleteByUserIds(Long[] userIds) {
        baseDao.deleteByUserIds(userIds);
    }

    @Override
    public List<Long> getRoleIdList(Long userId) {

        return baseDao.getRoleIdList(userId);
    }
}