package wc.service;

import java.util.List;

import wc.entity.TbCrmSmsTemplate;

public interface ITbCrmSmsTemplateService {

	List<TbCrmSmsTemplate> findByPage(int startNo, int endNo);

	int findTotal();

}
