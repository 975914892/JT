package wc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import wc.entity.TbSystemFunction;
import wc.entity.TbSystemRole;

public interface ITbSystemFunctionDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_FUNCTION
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long funcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_FUNCTION
     *
     * @mbg.generated
     */
    //添加
    public int insert(TbSystemFunction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_FUNCTION
     *
     * @mbg.generated
     */
    TbSystemFunction selectByPrimaryKey(Long funcId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_FUNCTION
     *
     * @mbg.generated
     */
    List<TbSystemFunction> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_FUNCTION
     *
     * @mbg.generated
     */
    
    //修改前查询
    public TbSystemFunction listByid(Integer functionId);
    //修改
    public int updateByPrimaryKey(TbSystemFunction functionId);
    
    //分页查询
    public List<TbSystemFunction> findByPage(@Param("page")int page,@Param("pagesize")int pagesize);
    public int findTotal();
    
    //删除
    public  boolean deleteById(Integer functionId);
    
    
    public List<TbSystemFunction> findFunctionById(List<Integer> list);
}