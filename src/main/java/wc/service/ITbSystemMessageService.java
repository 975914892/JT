package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmMessage;

public interface ITbSystemMessageService {

    //���
    public int insert(TbCrmMessage record);
    //�鿴�����ʱ��
    public int updateByPrimaryKey(TbCrmMessage record);
    
    //��ѯ�Լ�д�����˵���
    public List<TbCrmMessage> selectBymyself(@Param("userId")int userId,@Param("page")int page,@Param("pagesize")int pagesize);
    public int findBmTotal(int userId);
    //��ѯ����д���Լ�����
    public List<TbCrmMessage> selectByother(@Param("userId")int userId,@Param("page")int page,@Param("pagesize")int pagesize);
    public int findBoTotal(int userId);
    //�鿴
    public TbCrmMessage selectByid(Integer id);
    //ɾ��
    public  boolean deleteById(Integer id);
	
}
