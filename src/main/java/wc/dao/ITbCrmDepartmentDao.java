package wc.dao;

import java.util.List;
import wc.entity.TbCrmDepartment;

public interface ITbCrmDepartmentDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_DEPARTMENT
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_DEPARTMENT
     *
     * @mbg.generated
     */
    int insert(TbCrmDepartment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_DEPARTMENT
     *
     * @mbg.generated
     */
    TbCrmDepartment selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_DEPARTMENT
     *
     * @mbg.generated
     */
    List<TbCrmDepartment> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_DEPARTMENT
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbCrmDepartment record);
}