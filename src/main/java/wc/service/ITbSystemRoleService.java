package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbSystemRole;
import wc.entity.TbSystemUser;

public interface ITbSystemRoleService {
	
	public List<TbSystemRole> findRolesByIds(List<Integer> list);
	
	//分页查询
    public List<TbSystemRole> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //修改前查询
    public TbSystemRole listByid(Integer roleId);
    //修改
    public int updateByPrimaryKey(TbSystemRole role);
    
    //删除
    public  boolean deleteById(Integer roleId);
    
    //添加
    public int insert(TbSystemRole record);

}
