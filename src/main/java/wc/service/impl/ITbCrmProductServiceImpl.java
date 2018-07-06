package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmProductDao;
import wc.entity.TbCrmProduct;
import wc.service.ITbCrmProductService;

@Service
public class ITbCrmProductServiceImpl implements ITbCrmProductService {

	@Resource
	private ITbCrmProductDao proSer;
	
	public int insert(TbCrmProduct record) {
		// TODO Auto-generated method stub
		return proSer.insert(record);
	}

	public int updateByPrimaryKey(TbCrmProduct record) {
		// TODO Auto-generated method stub
		return proSer.updateByPrimaryKey(record);
	}

	public TbCrmProduct listByid(Integer id) {
		// TODO Auto-generated method stub
		return proSer.listByid(id);
	}

	public List<TbCrmProduct> findByPage(int page, int pagesize) {
		// TODO Auto-generated method stub
		return proSer.findByPage(page, pagesize);
	}

	public int findTotal() {
		// TODO Auto-generated method stub
		return proSer.findTotal();
	}

	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return proSer.deleteById(id);
	}

	public List<TbCrmProduct> findByNamePage(int page, int pagesize, String name) {
		// TODO Auto-generated method stub
		return proSer.findByNamePage(page, pagesize, name);
	}

	public int findByNameTotal(String name) {
		// TODO Auto-generated method stub
		return proSer.findByNameTotal(name);
	}

}
