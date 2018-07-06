package wc.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import wc.dao.ITbSystemRoleFunctionDao;
import wc.entity.TbSystemRoleFunction;
import wc.service.ITbSystemRoleFunctionService;

@Service("RoleFunctionService")
public class ITbSystemRoleFunctionServiceImpl implements ITbSystemRoleFunctionService {

	@Resource
	private ITbSystemRoleFunctionDao rfDao;

	public int insert(TbSystemRoleFunction record) {
		// TODO Auto-generated method stub
		return rfDao.insert(record);
	}

	public List<TbSystemRoleFunction> findByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return rfDao.findByRoleId(roleId);
	}

	public int deleteByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return rfDao.deleteByRoleId(roleId);
	}




	



}
