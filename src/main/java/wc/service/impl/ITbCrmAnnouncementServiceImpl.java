package wc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wc.dao.ITbCrmAnnouncementDao;
import wc.dao.ITbSystemUserDao;
import wc.entity.TbCrmAnnouncement;
import wc.service.ITbCrmAnnouncementService;
@Service
public class ITbCrmAnnouncementServiceImpl implements ITbCrmAnnouncementService {
	@Resource
	public ITbSystemUserDao tbSystemUserDao;
	@Resource
	public ITbCrmAnnouncementDao tbCrmAnnouncementDao;
	
	public List<TbCrmAnnouncement> findByPage(int startNo, int endNo) {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.findByPage(startNo,endNo);
	}
	
	public int findtotal() {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.findTotal();
	}

	public String findUsernameById(Long userId) {
		// TODO Auto-generated method stub
		return tbSystemUserDao.findUsernameById(userId);
	}

	public Long findByName(String username) {
		
		return tbSystemUserDao.findByName(username);
	}

	public int insert(TbCrmAnnouncement tca) {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.insert(tca);
	}

	public TbCrmAnnouncement findOneById(int id) {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.selectByPrimaryKey(Long.valueOf(id));
	}

	public int update(TbCrmAnnouncement tca) {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.updateByPrimaryKey(tca);
	}

	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.deleteByPrimaryKey(Long.valueOf(id));
	}

	public int findByTitleTotal(String title) {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.findByTitleTotal(title);
	}

	public List<TbCrmAnnouncement> findByTitlePage(int startNo, int endNo ,String title) {
		// TODO Auto-generated method stub
		return tbCrmAnnouncementDao.findByTitlePage(startNo,endNo,title);
	}

}
