package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmPositionDao;
import wc.entity.PositionVO;
import wc.entity.TbCrmPosition;
import wc.service.ITbCrmPositionService;
@Service
public class ITbCrmPositionServiceImpl implements ITbCrmPositionService {
	@Resource
	public ITbCrmPositionDao tbCrmPositionDao;
	
	public List<PositionVO> findByPage(int startNo, int endNo) {
		
		return tbCrmPositionDao.findByPage(startNo,endNo);
	}

	public int findtotal() {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.findtotal();
	}

	public List<TbCrmPosition> findALL() {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.selectAll();
	}

	public int insert(TbCrmPosition tcp) {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.insert(tcp);
	}

	public TbCrmPosition findById(int id) {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.selectByPrimaryKey(Long.valueOf(id));
	}

	public int update(TbCrmPosition tcp) {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.updateByPrimaryKey(tcp);
	}

	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.deleteByPrimaryKey(Long.valueOf(id));
	}

	public int findByNameTotal(String name) {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.findByNameTotal(name);
	}

	public List<PositionVO> findByNamePage(int startNo, int endNo, String name) {
		// TODO Auto-generated method stub
		return tbCrmPositionDao.findByNamePage(startNo,endNo,name);
	}

}
