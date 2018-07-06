package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbSystemFunction;

public interface ITbSystemFunctionService {
	
	public List<TbSystemFunction> findFunctionById(List<Integer> list);
	
    //修改前查询
    public TbSystemFunction listByid(Integer functionId);
    //修改
    public int updateByPrimaryKey(TbSystemFunction functionId);
    
    //分页查询
    public List<TbSystemFunction> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //删除
    public  boolean deleteById(Integer functionId);
	
    //添加
    public int insert(TbSystemFunction record);
}
