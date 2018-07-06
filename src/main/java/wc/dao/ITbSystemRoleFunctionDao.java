package wc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import wc.entity.TbSystemRoleFunction;

public interface ITbSystemRoleFunctionDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_ROLE_FUNCTION
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(@Param("funcId") Long funcId, @Param("roleId") Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_ROLE_FUNCTION
     *
     * @mbg.generated
     */
    

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_SYSTEM_ROLE_FUNCTION
     *
     * @mbg.generated
     */
    List<TbSystemRoleFunction> selectAll();
    
    //��Ȩ
    public int insert(TbSystemRoleFunction record);
    public List<TbSystemRoleFunction> findByRoleId(Integer roleId);
    public int deleteByRoleId(Integer roleId);
    
}