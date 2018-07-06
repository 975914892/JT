package wc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmEmailRecordDao;
import wc.entity.TbCrmEmailRecord;
import wc.service.ITbCrmEmailRecordService;
@Service
public class ITbCrmEmailRecordServiceImpl implements ITbCrmEmailRecordService {
	@Resource
	public ITbCrmEmailRecordDao tbCrmEmailRecordDao;
	public int insert(TbCrmEmailRecord tcsr) {
		// TODO Auto-generated method stub
		return tbCrmEmailRecordDao.insert(tcsr);
	}

}
