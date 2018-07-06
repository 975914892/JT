package wc.service;

import java.util.List;

import wc.entity.TbSystemUserRole;

public interface ITbSystemUserRoleService {

	List<TbSystemUserRole> findRoleByUserId(int id);
	
    //ÊÚÈ¨
    public List<TbSystemUserRole> findByUserId(Integer userId);
    public int deleteByUserId(Integer userId);
    public int insert(TbSystemUserRole record);

}
