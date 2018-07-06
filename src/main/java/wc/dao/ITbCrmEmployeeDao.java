package wc.dao;

import java.util.List;
import wc.entity.TbCrmEmployee;

public interface ITbCrmEmployeeDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMPLOYEE
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMPLOYEE
     *
     * @mbg.generated
     */
    int insert(TbCrmEmployee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMPLOYEE
     *
     * @mbg.generated
     */
    TbCrmEmployee selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMPLOYEE
     *
     * @mbg.generated
     */
    List<TbCrmEmployee> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TB_CRM_EMPLOYEE
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbCrmEmployee record);
}