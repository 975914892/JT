package wc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmRBusinessCustomerDao;
import wc.entity.TbCrmRBusinessCustomer;
import wc.service.ITbCrmRBusinessCustomerService;
@Service
public class ITbCrmRBusinessCustomerServiceImpl implements ITbCrmRBusinessCustomerService {
	@Resource
	public ITbCrmRBusinessCustomerDao tbCrmRBusinessCustomerDao;
	public int insert(TbCrmRBusinessCustomer tcrbc) {
		// TODO Auto-generated method stub
		return tbCrmRBusinessCustomerDao.insert(tcrbc);
	}

}
