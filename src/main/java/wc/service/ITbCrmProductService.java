package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmProduct;

public interface ITbCrmProductService {

    //添加
    public int insert(TbCrmProduct record);
    //修改
    public int updateByPrimaryKey(TbCrmProduct record);
    
    //修改前查询
    public TbCrmProduct listByid(Integer id);
    
    //分页查询
    public List<TbCrmProduct> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //删除
    public  boolean deleteById(Integer id);
	
    //分页查询(模糊查询)
    public List<TbCrmProduct> findByNamePage(@Param("page")int page,@Param("pagesize")int pagesize,@Param("name")String name);
    
    //分页查询total(模糊查询)
    public int findByNameTotal(String name);
}
