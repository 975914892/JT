package wc.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbCrmProduct;
import wc.entity.TbCrmProductCategory;

public interface ITbCrmProductCategoryService {

    //查询所有
    public List<TbCrmProductCategory> selectAll();
    //添加
    public int insert(TbCrmProductCategory record);
    //修改
    public int updateByPrimaryKey(TbCrmProductCategory record);
    
    //修改前查询
    public TbCrmProductCategory listByid(Integer id);
    
    //分页查询
    public List<TbCrmProductCategory> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //删除
    public  boolean deleteById(Integer id);
}
