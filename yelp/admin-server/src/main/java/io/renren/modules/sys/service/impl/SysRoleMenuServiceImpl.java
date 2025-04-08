

package io.renren.modules.sys.service.impl;

import cn.hutool.core.collection.CollUtil;
import io.renren.common.service.impl.BaseServiceImpl;
import io.renren.modules.sys.dao.SysRoleMenuDao;
import io.renren.modules.sys.entity.SysRoleMenuEntity;
import io.renren.modules.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Character and menu correspondence
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		//Delete the role menu relationship first
		deleteByRoleIds(new Long[]{roleId});

		//The role does not have a menu permission
		if(CollUtil.isEmpty(menuIdList)){
			return ;
		}

		//Save role menu relationships
		for(Long menuId : menuIdList){
			SysRoleMenuEntity sysRoleMenuEntity = new SysRoleMenuEntity();
			sysRoleMenuEntity.setMenuId(menuId);
			sysRoleMenuEntity.setRoleId(roleId);

			//save
			insert(sysRoleMenuEntity);
		}
	}

	@Override
	public List<Long> getMenuIdList(Long roleId){
		return baseDao.getMenuIdList(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByRoleIds(Long[] roleIds) {
		baseDao.deleteByRoleIds(roleIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteByMenuId(Long menuId) {
		baseDao.deleteByMenuId(menuId);
	}

}