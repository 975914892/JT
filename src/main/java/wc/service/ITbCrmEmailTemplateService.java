package wc.service;

import java.util.List;

import wc.entity.TbCrmEmailTemplate;
import wc.entity.TbCrmSmsTemplate;

public interface ITbCrmEmailTemplateService {

	List<TbCrmEmailTemplate> findByPage(int startNo, int endNo);

	int findTotal();

}
