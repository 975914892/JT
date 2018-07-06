package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmMessageDao;
import wc.entity.TbCrmMessage;
import wc.service.ITbSystemMessageService;

@Service("messageService")
public class ITbSystemMessageServiceImpl implements ITbSystemMessageService {

	@Resource
	public ITbCrmMessageDao messageDao;
	
	
	public int insert(TbCrmMessage record) {
		// TODO Auto-generated method stub
		return messageDao.insert(record);
	}

	public int updateByPrimaryKey(TbCrmMessage record) {
		// TODO Auto-generated method stub
		return messageDao.updateByPrimaryKey(record);
	}

	public List<TbCrmMessage> selectBymyself(int userId, int page, int pagesize) {
		// TODO Auto-generated method stub
		return messageDao.selectBymyself(userId, page, pagesize);
	}



	public List<TbCrmMessage> selectByother(int userId, int page, int pagesize) {
		// TODO Auto-generated method stub
		return messageDao.selectByother(userId, page, pagesize);
	}



	public TbCrmMessage selectByid(Integer id) {
		// TODO Auto-generated method stub
		return messageDao.selectByid(id);
	}

	public boolean deleteById(Integer id) {
		// TODO Auto-generated method stub
		return messageDao.deleteById(id);
	}

	public int findBmTotal(int userId) {
		// TODO Auto-generated method stub
		return messageDao.findBmTotal(userId);
	}

	public int findBoTotal(int userId) {
		// TODO Auto-generated method stub
		return messageDao.findBoTotal(userId);
	}


}
