package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmEmailTemplateDao;
import wc.entity.TbCrmEmailTemplate;
import wc.entity.TbCrmSmsTemplate;
import wc.service.ITbCrmEmailTemplateService;
@Service
public class ITbCrmEmailTemplateServiceImpl implements ITbCrmEmailTemplateService {
	@Resource
	public ITbCrmEmailTemplateDao tbCrmEmailTemplateDao;
	public List<TbCrmEmailTemplate> findByPage(int startNo, int endNo) {
		// TODO Auto-generated method stub
		return tbCrmEmailTemplateDao.findByPage(startNo,endNo);
	}

	public int findTotal() {
		// TODO Auto-generated method stub
		return tbCrmEmailTemplateDao.findTotal();
	}

}
