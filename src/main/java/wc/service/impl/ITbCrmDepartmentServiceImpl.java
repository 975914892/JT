package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmDepartmentDao;
import wc.entity.TbCrmDepartment;
import wc.service.ITbCrmDepartmentService;
@Service
public class ITbCrmDepartmentServiceImpl implements ITbCrmDepartmentService {
	
	@Resource
	public ITbCrmDepartmentDao tbCrmDepartmentDao;
	public List<TbCrmDepartment> findAll() {
		// TODO Auto-generated method stub
		return tbCrmDepartmentDao.selectAll();
	}

}
