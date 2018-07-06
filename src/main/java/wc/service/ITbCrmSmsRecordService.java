package wc.service;

import wc.entity.TbCrmSmsRecord;

public interface ITbCrmSmsRecordService {

	Long getUserIdByName(String username);

	int insert(TbCrmSmsRecord tcsr);

	

}
