package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmSmsTemplateDao;
import wc.entity.TbCrmSmsTemplate;
import wc.service.ITbCrmSmsTemplateService;
@Service
public class ITbCrmSmsTemplateServiceImpl implements ITbCrmSmsTemplateService {
	@Resource
	public ITbCrmSmsTemplateDao tbCrmSmsTemplateDao;
	
	public List<TbCrmSmsTemplate> findByPage(int startNo, int endNo) {
		// TODO Auto-generated method stub
		return tbCrmSmsTemplateDao.findByPage(startNo,endNo);
	}

	public int findTotal() {
		// TODO Auto-generated method stub
		return tbCrmSmsTemplateDao.findTotal();
	}

}
