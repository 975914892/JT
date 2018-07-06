package wc.service;

import java.util.List;

import wc.entity.AddLeadVO;
import wc.entity.LeadsFieldVO;
import wc.entity.LeadsVO;
import wc.entity.TbCrmLeadLog;
import wc.entity.TbCrmLeads;

public interface ITbCrmLeadsService {

	List<TbCrmLeads> findAllLeads();
	
	public int getCount(int userId, String value,String field,int page, int rows);

	List<LeadsVO> findLeadsAndUser(int userId, String value,String field,int page, int rows);

	List<LeadsFieldVO> getFields();

	int addLead(TbCrmLeads leads);

	int updateDelete(List<Integer> list);
	
	int updateDeleteOne(int id);

	TbCrmLeads getOneById(long l);

	int updateOne(TbCrmLeads leads);

	List<AddLeadVO> findLeadPool(String value, String field, int start, int end, String startTime, String endTime);

	int getLeadPoolCount(String value, String field, int start, int end, String startTime, String endTime);

	LeadsVO getOneLeadVO(long l);

	List<TbCrmLeadLog> getCCById(int id, int page, int rows);

	int getLeadLogCountById(int id);

	List<TbCrmLeadLog> getCCByIdAAAA(int id);

	int putPool(List<Integer> list);

	List<AddLeadVO> findLeadRB(String value, String field, int start, int end, String startTime, String endTime);

	int findLeadRBCount(String value, String field, int i, int rows, String startTime, String endTime);

	int reLeads(List<Integer> list);

	int actuallyDeleteOne(int id);

}
