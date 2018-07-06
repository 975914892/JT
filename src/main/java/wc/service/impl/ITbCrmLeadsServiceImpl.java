package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmLeadsDao;
import wc.entity.AddLeadVO;
import wc.entity.LeadsFieldVO;
import wc.entity.LeadsVO;
import wc.entity.TbCrmLeadLog;
import wc.entity.TbCrmLeads;
import wc.service.ITbCrmLeadsService;

@Service("LeadsService")
public class ITbCrmLeadsServiceImpl implements ITbCrmLeadsService {
	
	@Resource
	public ITbCrmLeadsDao leadsDao;
	
	public List<TbCrmLeads> findAllLeads() {
		return leadsDao.selectAll();
	}

	public int getCount(int userId,String value,String field,int page, int rows) {
		return leadsDao.getCount(userId,value,field,page,rows);
	}

	public List<LeadsVO> findLeadsAndUser(int userId,String value,String field,int page, int rows) {
		return leadsDao.findLeadsAndUser(userId,value,field,page,rows);
	}

	public List<LeadsFieldVO> getFields() {
		return leadsDao.getFields();
	}

	public int addLead(TbCrmLeads leads) {
		return leadsDao.insert(leads);
	}

	public int updateDelete(List<Integer> list) {
		return leadsDao.updateDelete(list);
	}


	public TbCrmLeads getOneById(long id) {
		return leadsDao.selectByPrimaryKey((Long)id);
	}

	public int updateOne(TbCrmLeads leads) {
		return leadsDao.updateByPrimaryKey(leads);
	}

	public List<AddLeadVO> findLeadPool(String value, String field, int start, int end, String startTime, String endTime) {
		return leadsDao.findLeadPool( value,  field,  start,  end,  startTime,  endTime);
	}

	public int getLeadPoolCount(String value, String field, int start, int end, String startTime, String endTime) {
		return leadsDao.getLeadPoolCount(value,  field,  start,  end,  startTime,  endTime);
	}

	public LeadsVO getOneLeadVO(long l) {
		return leadsDao.getOneLeadVO(l);
	}

	public List<TbCrmLeadLog> getCCById(int id,int page,int rows) {
		return leadsDao.getCCById(id,page,rows);
	}

	public int getLeadLogCountById(int id) {
		return leadsDao.getLeadLogCountById(id);
	}

	public List<TbCrmLeadLog> getCCByIdAAAA(int id) {
		return leadsDao.getCCByIdAAAA(id);
	}

	public int updateDeleteOne(int id) {
		return leadsDao.updateDeleteOne(id);
	}

	public int putPool(List<Integer> list) {
		return leadsDao.putPool(list);
	}

	public List<AddLeadVO> findLeadRB(String value, String field, int start, int end, String startTime,
			String endTime) {
		return leadsDao.findLeadRB(value,  field,  start,  end,  startTime,  endTime);
	}

	public int findLeadRBCount(String value, String field, int start, int end, String startTime, String endTime) {
		return leadsDao.findLeadRBCount(value,  field,  start,  end,  startTime,  endTime);
	}

	public int reLeads(List<Integer> list) {
		return leadsDao.reLeads(list);
	}

	public int actuallyDeleteOne(int id) {
		return leadsDao.deleteByPrimaryKey((long) id);
	}

}
