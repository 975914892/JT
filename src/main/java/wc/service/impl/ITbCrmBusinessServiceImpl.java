package wc.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmBusinessDao;
import wc.entity.BusinessVO;
import wc.entity.TbCrmBusiness;
import wc.service.ITbCrmBusinessService;
@Service
public class ITbCrmBusinessServiceImpl implements ITbCrmBusinessService {
	
	@Resource
	public ITbCrmBusinessDao tbCrmBusinessDao;
	
	public TbCrmBusiness findById(long id) {
		return tbCrmBusinessDao.selectByPrimaryKey(id);
	}
	public List<BusinessVO> findAllBusinessBy(int userId, int start, int end, String field, String value) {
		return tbCrmBusinessDao.findAllBusinessBy(userId, start, end, field, value);
	}
	public int getAllBusinessCount(int userId, String field, String value) {
		return tbCrmBusinessDao.getAllBusinessCount(userId, field, value);
	}
	public List<Map<String, Object>> getAllUsername() {
		return tbCrmBusinessDao.getAllUsername();
	}
	public List<Map<String, Object>> getAllCustomerName() {
		return tbCrmBusinessDao.getAllCustomerName();
	}
	public String findBNameByCId(int customerId) {
		return tbCrmBusinessDao.findBNameByCId(customerId);
	}
	public int getContactsId(String contactsName) {
		return tbCrmBusinessDao.getContactsId(contactsName);
	}
	public List<Map<String, Object>> getAllContactsName() {
		return tbCrmBusinessDao.getAllContactsName();
	}
	public int addBusiness(TbCrmBusiness tcb) {
		return tbCrmBusinessDao.insert(tcb);
	}
	public int updateDelete(List<Integer> list) {
		return tbCrmBusinessDao.updateDelete(list);
	}
	public int updateDeleteOne(int id) {
		return tbCrmBusinessDao.updateDeleteOne(id);
	}
	public TbCrmBusiness getOneById(long l) {
		return tbCrmBusinessDao.selectByPrimaryKey(l);
	}
	public BusinessVO getOneBusinessVO(long l) {
		return tbCrmBusinessDao.getOneBusinessVO(l);
	}
	public int updateOne(TbCrmBusiness tcb) {
		return tbCrmBusinessDao.updateByPrimaryKey(tcb);
	}
	public int getCustomerId(String customerName) {
		return tbCrmBusinessDao.getCustomerId(customerName);
	}
	public List<BusinessVO> findBusinessRB(String value, String field, int i, int rows, String startTime, String endTime) {
		return tbCrmBusinessDao.findBusinessRB(value,field,i,rows,startTime,endTime);
	}
	public int findBusinessRBCount(String value, String field, int i, int rows, String startTime, String endTime) {
		return tbCrmBusinessDao.findBusinessRBCount(value,field,i,rows,startTime,endTime);
	}
	public List<Map<String, Object>> findBusinessLogById(int id) {
		return tbCrmBusinessDao.findBusinessLogById(id);
	}
	public Map<String, Object> findContractById(int id) {
		return tbCrmBusinessDao.findContractById(id);
	}
	public int updateStatusBy(TbCrmBusiness b) {
		return tbCrmBusinessDao.updateStatusBy(b);
	}
	public int getStatusIdBy(int id) {
		return tbCrmBusinessDao.getStatusIdBy(id);
	}

}
