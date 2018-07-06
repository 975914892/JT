package wc.service;

import java.util.List;
import java.util.Map;

import wc.entity.BusinessVO;
import wc.entity.TbCrmBusiness;

public interface ITbCrmBusinessService {

	TbCrmBusiness findById(long id);

	List<BusinessVO> findAllBusinessBy(int userId, int start, int end, String field, String value);

	int getAllBusinessCount(int userId, String field, String value);

	List<Map<String, Object>> getAllUsername();

	List<Map<String, Object>> getAllCustomerName();

	String findBNameByCId(int customerId);

	int getContactsId(String contactsName);

	List<Map<String, Object>> getAllContactsName();

	int addBusiness(TbCrmBusiness tcb);

	int updateDelete(List<Integer> list);

	int updateDeleteOne(int id);

	TbCrmBusiness getOneById(long l);

	BusinessVO getOneBusinessVO(long l);

	int updateOne(TbCrmBusiness tcb);

	int getCustomerId(String customerName);

	List<BusinessVO> findBusinessRB(String value, String field, int i, int rows, String startTime, String endTime);

	int findBusinessRBCount(String value, String field, int i, int rows, String startTime, String endTime);

	List<Map<String, Object>> findBusinessLogById(int id);

	Map<String, Object> findContractById(int id);

	int updateStatusBy(TbCrmBusiness b);

	int getStatusIdBy(int id);

	
	
	
	
	
	
	
	
	

}
