package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbSystemUser;

public interface ITbSystemUserService {
	
	public TbSystemUser login(String username,String password);
	
	public int findUserId(String username);
	
    //���
    public int insert(TbSystemUser record);
	
	//��ѯ����
    public List<TbSystemUser> selectAll();
    //��ҳ��ѯ
    public List<TbSystemUser> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    //�޸�ǰ��ѯ
    public TbSystemUser listByid(Integer userId);
    //�޸�
    public int updateByPrimaryKey(TbSystemUser user);
    //ɾ��
    public  boolean deleteById(Integer userId);
}
