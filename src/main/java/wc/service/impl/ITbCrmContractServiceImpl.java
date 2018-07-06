package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmBusinessDao;
import wc.dao.ITbCrmContractDao;
import wc.dao.ITbSystemUserDao;
import wc.entity.ContractVO;
import wc.entity.ContractVO2;
import wc.entity.PositionVO;
import wc.entity.TbCrmContract;
import wc.service.ITbCrmContractService;
@Service
public class ITbCrmContractServiceImpl implements ITbCrmContractService {
	@Resource
	public ITbSystemUserDao tbSystemUserDao;
	@Resource
	public ITbCrmContractDao tbCrmContractDao;
	@Resource
	public ITbCrmBusinessDao tbCrmBusinessDao;
	
	public List<ContractVO> findByPage(int startNo, int endNo) {
		// TODO Auto-generated method stub
		return tbCrmContractDao.findByPage(startNo,endNo);
	}

	public int findtotal() {
		// TODO Auto-generated method stub
		return tbCrmContractDao.findtotal();
	}

	public List<ContractVO2> findByContractVO2() {
		// TODO Auto-generated method stub
		return tbCrmBusinessDao.findByContractVO2();
	}

	public ContractVO2 findByIdContractVO2(int tcbsId) {
		// TODO Auto-generated method stub
		return tbCrmBusinessDao.findByIdContractVO2(tcbsId);
	}

	public Long findByUsername(String temp) {
		// TODO Auto-generated method stub
		return tbSystemUserDao.findByName(temp);
	}

	public int insert(TbCrmContract tcc) {
		// TODO Auto-generated method stub
		return tbCrmContractDao.insert(tcc);
	}

	public TbCrmContract findById(int id) {
		// TODO Auto-generated method stub
		return tbCrmContractDao.selectByPrimaryKey(Long.valueOf(id));
	}

	public int update(TbCrmContract tcc) {
		// TODO Auto-generated method stub
		return tbCrmContractDao.updateByPrimaryKey(tcc);
	}

	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return tbCrmContractDao.deleteByPrimaryKey(Long.valueOf(id));
	}

	public int findByNumberTotal(String number) {
		// TODO Auto-generated method stub
		return tbCrmContractDao.findByNumberTotal(number);
	}

	public List<ContractVO> findByNumberPage(int startNo, int endNo, String number) {
		// TODO Auto-generated method stub
		return tbCrmContractDao.findByNumberPage(startNo,endNo,number);
	}

}
