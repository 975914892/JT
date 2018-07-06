package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbSystemRoleDao;
import wc.entity.TbSystemRole;
import wc.service.ITbSystemRoleService;

@Service("roleService")
public class ITbSystemRoleServiceImpl implements ITbSystemRoleService {
	
	@Resource
	private ITbSystemRoleDao roleDao;
	
	public List<TbSystemRole> findRolesByIds(List<Integer> list) {
		return roleDao.findRolesByIds(list);
	}

	public List<TbSystemRole> findByPage(int page, int pagesize) {
		// TODO Auto-generated method stub
		return roleDao.findByPage(page, pagesize);
	}

	public int findTotal() {
		// TODO Auto-generated method stub
		return roleDao.findTotal();
	}

	public TbSystemRole listByid(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.listByid(roleId);
	}

	public int updateByPrimaryKey(TbSystemRole role) {
		// TODO Auto-generated method stub
		return roleDao.updateByPrimaryKey(role);
	}

	public boolean deleteById(Integer roleId) {
		// TODO Auto-generated method stub
		return roleDao.deleteById(roleId);
	}

	public int insert(TbSystemRole record) {
		// TODO Auto-generated method stub
		return roleDao.insert(record);
	}

}
