package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmProductCategoryDao;
import wc.entity.TbCrmProduct;
import wc.entity.TbCrmProductCategory;
import wc.service.ITbCrmProductCategoryService;

@Service
public class ITbCrmProductCategoryServiceImpl implements ITbCrmProductCategoryService {

	@Resource
	private ITbCrmProductCategoryDao pcDao;
	
	
	public int insert(TbCrmProductCategory record) {
		// TODO Auto-generated method stub
		return pcDao.insert(record);
	}

	public int updateByPrimaryKey(TbCrmProductCategory record) {
		// TODO Auto-generated method stub
		return pcDao.updateByPrimaryKey(record);
	}

	public TbCrmProductCategory listByid(Integer id) {
		// TODO Auto-generated method stub
		return pcDao.listByid(id);
	}

	public List<TbCrmProductCategory> findByPage(int page, int pagesize) {
		// TODO Auto-generated method stub
		return pcDao.findByPage(page, pagesize);
	}

	public int findTotal() {
		// TODO Auto-generated method stub
		return pcDao.findTotal();
	}

	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return pcDao.deleteById(id);
	}

	public List<TbCrmProductCategory> selectAll() {
		// TODO Auto-generated method stub
		return pcDao.selectAll();
	}

}
