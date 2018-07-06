package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbSystemUserDao;
import wc.entity.TbSystemUser;
import wc.service.ITbSystemUserService;

@Service("TbSystemUserService")
public class ITbSystemUserServiceImpl implements ITbSystemUserService {
	
	@Resource
	private ITbSystemUserDao userDao;

	public TbSystemUser login(String username, String password) {
		return userDao.login(username, password);
	}

	public int findUserId(String username) {
		return userDao.findUserId(username);
	}

	public List<TbSystemUser> selectAll() {
		// TODO Auto-generated method stub
		return userDao.selectAll();
	}

	public List<TbSystemUser> findByPage(int page, int pagesize) {
		// TODO Auto-generated method stub
		return userDao.findByPage(page, pagesize);
	}

	public int findTotal() {
		// TODO Auto-generated method stub
		return userDao.findTotal();
	}

	public TbSystemUser listByid(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.listByid(userId);
	}

	public int updateByPrimaryKey(TbSystemUser user) {
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKey(user);
	}

	public boolean deleteById(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.deleteById(userId);
	}

	public int insert(TbSystemUser record) {
		// TODO Auto-generated method stub
		return userDao.insert(record);
	}

	
}
