package wc.service;

import java.util.List;

import wc.entity.TbCrmAnnouncement;

public interface ITbCrmAnnouncementService {
	
	public List<TbCrmAnnouncement> findByPage(int startNo, int endNo);

	public int findtotal();

	public String findUsernameById(Long userId);

	public Long findByName(String username);

	public int insert(TbCrmAnnouncement tca);

	public TbCrmAnnouncement findOneById(int id);

	public int update(TbCrmAnnouncement tca);

	public int deleteById(Integer id);

	public int findByTitleTotal(String title);

	public List<TbCrmAnnouncement> findByTitlePage(int startNo, int endNo,String title);
}
