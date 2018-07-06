package wc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmSmsRecordDao;
import wc.dao.ITbSystemUserDao;
import wc.entity.TbCrmSmsRecord;
import wc.service.ITbCrmSmsRecordService;
@Service
public class ITbCrmSmsRecordServiceImpl implements ITbCrmSmsRecordService {
	@Resource
	public ITbCrmSmsRecordDao tbCrmSmsRecordDao;
	@Resource
	public ITbSystemUserDao tbSystemUserDao;
	
	public Long getUserIdByName(String username) {
		// TODO Auto-generated method stub
		return tbSystemUserDao.findByName(username);
	}

	public int insert(TbCrmSmsRecord tcsr) {
		// TODO Auto-generated method stub
		return tbCrmSmsRecordDao.insert(tcsr);
	}

}
