package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbSystemUserRoleDao;
import wc.entity.TbSystemUserRole;
import wc.service.ITbSystemUserRoleService;

@Service("UserRoleService")
public class ITbSystemUserRoleServiceImpl implements ITbSystemUserRoleService {
	
	@Resource
	private ITbSystemUserRoleDao urDao;
	
	public List<TbSystemUserRole> findRoleByUserId(int id) {
		return urDao.findRoleByUserId(id);
	}

	public List<TbSystemUserRole> findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return urDao.findByUserId(userId);
	}

	public int deleteByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return urDao.deleteByUserId(userId);
	}

	public int insert(TbSystemUserRole record) {
		// TODO Auto-generated method stub
		return urDao.insert(record);
	}

}
