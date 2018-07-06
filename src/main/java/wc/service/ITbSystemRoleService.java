package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbSystemRole;
import wc.entity.TbSystemUser;

public interface ITbSystemRoleService {
	
	public List<TbSystemRole> findRolesByIds(List<Integer> list);
	
	//��ҳ��ѯ
    public List<TbSystemRole> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //�޸�ǰ��ѯ
    public TbSystemRole listByid(Integer roleId);
    //�޸�
    public int updateByPrimaryKey(TbSystemRole role);
    
    //ɾ��
    public  boolean deleteById(Integer roleId);
    
    //���
    public int insert(TbSystemRole record);

}
