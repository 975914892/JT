package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbSystemUser;

public interface ITbSystemUserService {
	
	public TbSystemUser login(String username,String password);
	
	public int findUserId(String username);
	
    //添加
    public int insert(TbSystemUser record);
	
	//查询所有
    public List<TbSystemUser> selectAll();
    //分页查询
    public List<TbSystemUser> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    //修改前查询
    public TbSystemUser listByid(Integer userId);
    //修改
    public int updateByPrimaryKey(TbSystemUser user);
    //删除
    public  boolean deleteById(Integer userId);
}
